package dwa.adamy.database;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONPointer;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Database {

    private Path path = Paths.get("database.json");

    //region Singleton

    private static Database ourInstance = new Database();

    public static Database getInstance() {
        return ourInstance;
    }

    //endregion

    private Database() {

    }

    //region Save and Load

    public JSONObject toJSON() {
        JSONObject obj = new JSONObject();

        {
            JSONArray patients = new JSONArray();
            for (Patient p : patientList)
                patients.put(p.toJSON());
            obj.put("patients", patients);
        }

        {
            JSONArray visits = new JSONArray();
            for (PlanVisit planVisit : planVisits)
                visits.put(planVisit.toJSON());
            obj.put("planVisits", visits);
        }

        {
            JSONArray examinations = new JSONArray();
            for (Examination e : examinationList)
                examinations.put(e.toJSON());
            obj.put("examinations", examinations);
        }

        return obj;
    }

    public void save() {
        try {
            Files.write(path, toJSON().toString().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void load() {
        try {
            JSONObject obj = new JSONObject(new String(Files.readAllBytes(path)));

            if (obj.has("patients")) {
                JSONArray patients = obj.getJSONArray("patients");
                for (Object p : patients)
                    patientList.add(new Patient((JSONObject) p));
            }

            if (obj.has("examinations")) {
                JSONArray examinations = obj.getJSONArray("examinations");
                for (Object e : examinations)
                    examinationList.add(new Examination((JSONObject) e));
            }

            if (obj.has("planVisits")) {
                JSONArray visits = obj.getJSONArray("planVisits");
                for (Object e : visits)
                    planVisits.add(new PlanVisit((JSONObject) e));
            }


        } catch (IOException e) {
            //throw new RuntimeException(e);
        }
    }

    //endregion

    //region Patient

    private List<Patient> patientList = new ArrayList<>();

    public List<Patient> getPatientList() {
        return patientList;
    }

    public List<Patient> searchPatient(String selector) {

        return null;
    }

    public Patient getPatientByID(String uniqueID) {
        for (Patient patient : patientList) {
            if (patient.getUniqueID().equals(uniqueID))
                return patient;
        }
        return null;
    }

    public List<Patient> findPatientsBySelector(String selector) {
        //TODO napisać tą funkcje
        return patientList;
    }

    public void addPatient(Patient patient) throws PatientAlreadyExistsException {
        patientList.add(patient);
    }

    public class PatientAlreadyExistsException extends Exception {
    }

    //endregion

    //region Examination

    private List<Examination> examinationList = new ArrayList<>();

    public List<Examination> getExaminationList() {
        return examinationList;
    }

    public void addExamination(Examination examination) {
        examinationList.add(examination);
    }

    public List<Examination> getExaminationFromDate(LocalDate date) {
        return examinationList
                .stream()
                .filter(e -> e.getDate().equals(date))
                .collect(Collectors.toList());
    }


    //endregion

    //region Doctors

    static List<Doctor> doctorList = null;

    public List<Doctor> getDoctors() {
        if (doctorList != null)
            return doctorList;

        doctorList = new ArrayList<>();

        doctorList.add(new Doctor("Tymon", "Rubel", "001"));
        doctorList.add(new Doctor("Jan", "Marzec", "002"));
        doctorList.add(new Doctor("Waldemar", "Smolik", "003"));
        doctorList.add(new Doctor("Andrzej", "Domański", "004"));
        doctorList.add(new Doctor("Tomasz", "Olszewski", "005"));

        return doctorList;
    }

    public Doctor getDoctorByID(String id) {
        for (Doctor doc : getDoctors()) {
            if (doc.getId().equals(id))
                return doc;
        }
        return null;
    }

    //endregion

    //region Calendar

    private List<PlanVisit> planVisits = new ArrayList<>();

    public void addPlanVisit(PlanVisit visit) {
        planVisits.add(visit);
    }

    public void removePlanVisit(PlanVisit visit) {
        planVisits.remove(visit);
    }

    public List<PlanVisit> getVisitsFromDay(LocalDate date, Doctor doctor) {
        List<PlanVisit> list = planVisits.stream()
                .filter(planVisit -> planVisit.getDate().equals(date) &&
                        planVisit.getDoctorId().equals(doctor.getId()))
                .collect(Collectors.toList());

        list.sort(Comparator.comparing(PlanVisit::getTime));

        int initSize = list.size();

        for (int h = 8, i = 0; h < 18; h++) {
            for (int m = 0; m < 60; m += 15) {

                LocalTime time = LocalTime.of(h, m);

                if (initSize > i && list.get(i).getTime().equals(time)) {
                    i++;
                    continue;
                }

                PlanVisit planVisit = new PlanVisit();
                planVisit.setTime(time);
                planVisit.setDate(date);
                planVisit.setDoctor(doctor);
                list.add(planVisit);
            }
        }

        list.sort(Comparator.comparing(PlanVisit::getTime));

        return list;
    }

    //endregion
}
