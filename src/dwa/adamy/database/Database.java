package dwa.adamy.database;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
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

    private JSONObject toJSON() {
        JSONObject obj = new JSONObject();

        {
            JSONArray list = new JSONArray();
            for (Patient p : patientList)
                list.put(p.toJSON());
            obj.put("patients", list);
        }

        {
            JSONArray list = new JSONArray();
            for (PlanVisit planVisit : planVisits)
                list.put(planVisit.toJSON());
            obj.put("planVisits", list);
        }

        {
            JSONArray list = new JSONArray();
            for (Hospitalization h : getHospitalizationList())
                list.put(h.toJSON());
            obj.put("hospitalizations", list);
        }

        {
            JSONArray list = new JSONArray();
            for (Examination e : examinationList)
                list.put(e.toJSON());
            obj.put("examinations", list);
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

            if (obj.has("hospitalizations")) {
                JSONArray visits = obj.getJSONArray("hospitalizations");
                for (Object e : visits)
                    hospitalizationList.add(new Hospitalization((JSONObject) e));
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

    //region Hospitalization

    private List<HospitalizationUnit> hospitalizationUnitList = null;

    public List<HospitalizationUnit> getHospitalizationUnitList() {
        if (hospitalizationUnitList != null)
            return hospitalizationUnitList;

        hospitalizationUnitList = new ArrayList<>();

        hospitalizationUnitList.add(new HospitalizationUnit("Sala 1", "001"));
        hospitalizationUnitList.add(new HospitalizationUnit("Sala 2", "002"));
        hospitalizationUnitList.add(new HospitalizationUnit("Sala 3", "003"));
        hospitalizationUnitList.add(new HospitalizationUnit("Sala 4", "004"));
        hospitalizationUnitList.add(new HospitalizationUnit("Sala 5", "005"));
        hospitalizationUnitList.add(new HospitalizationUnit("Sala 6", "006"));

        return hospitalizationUnitList;
    }

    public HospitalizationUnit getHospitalizationUnitByID(String value) {
        for (HospitalizationUnit hUnit : getHospitalizationUnitList())
            if (hUnit.getId().equals(value)) return hUnit;

        return null;
    }

    private List<Hospitalization> hospitalizationList = new ArrayList<>();

    public List<Hospitalization> getHospitalizationList() {
        return hospitalizationList;
    }

    public void addHospitalization(Hospitalization hospitalization) {
        hospitalizationList.add(hospitalization);
    }

    public List<Hospitalization> getHospitalizationListFromDate(LocalDate date, HospitalizationUnit unit) {
        List<Hospitalization> list = new ArrayList<>();

        for (Hospitalization h : getHospitalizationList()) {
            if (h.getFromDate().isAfter(date)) continue;
            if (h.getToDate().isBefore(date)) continue;
            if (unit != null && !h.getUnitID().equals(unit.getId())) continue;
            list.add(h);
        }

        return list;
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
