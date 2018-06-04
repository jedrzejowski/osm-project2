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
import java.util.Arrays;
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
        obj.put("patients", patientList.stream().map(el -> el.toJSON()));
        obj.put("planVisits", planVisits.stream().map(el -> el.toJSON()));
        obj.put("hospitalizations", getHospitalizationList().stream().map(el -> el.toJSON()));
        obj.put("examinations", examinationList.stream().map(el -> el.toJSON()));

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

            if (obj.has("patients"))
                obj.getJSONArray("patients").forEach(el -> patientList.add(new Patient((JSONObject) el)));

            if (obj.has("examinations"))
                obj.getJSONArray("examinations").forEach(el -> examinationList.add(new Examination((JSONObject) el)));

            if (obj.has("planVisits"))
                obj.getJSONArray("planVisits").forEach(el -> planVisits.add(new PlanVisit((JSONObject) el)));

            if (obj.has("hospitalizations"))
                obj.getJSONArray("hospitalizations").forEach(el -> hospitalizationList.add(new Hospitalization((JSONObject) el)));


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
//        for (Patient patient : patientList) {
//            if (patient.getUniqueID().equals(uniqueID))
//                return patient;
//        }
//        return null;
        return (Patient) patientList.stream()
                .filter(patient -> patient.getUniqueID().equals(uniqueID))
                .limit(1);
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
//        for (Doctor doc : getDoctors()) {
//            if (doc.getId().equals(id))
//                return doc;
//        }
//        return null;
        return (Doctor) getDoctors().stream()
                .filter(doc -> doc.getId().equals(id))
                .limit(1);

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
//        for (HospitalizationUnit hUnit : getHospitalizationUnitList())
//            if (hUnit.getId().equals(value)) return hUnit;
//
//        return null;
        return (HospitalizationUnit) getHospitalizationUnitList().stream()
                .filter(el -> el.getId().equals(value))
                .limit(1);
    }

    private List<Hospitalization> hospitalizationList = new ArrayList<>();

    public List<Hospitalization> getHospitalizationList() {
        return hospitalizationList;
    }

    public void addHospitalization(Hospitalization hospitalization) throws Exception{
        //sprawdzić daty czy nie wchłania datami w całości i sprawdzić dokładniej w obrębie dnia
        List<Hospitalization> hospitalizationEndInBeginDateList = hospitalizationList.stream()
                .filter(h -> h.getToDate().equals(hospitalization.getFromDate()))
                .collect(Collectors.toList());
        List<Hospitalization> hospitalizationStartBEndDateList = hospitalizationList.stream()
                .filter(h -> h.getFromDate().equals(hospitalization.getToDate()))
                .collect(Collectors.toList());

        if (hospitalizationEndInBeginDateList.stream().anyMatch(h -> h.getToTime().compareTo( hospitalization.getFromTime()) >= 1)
                ||
                hospitalizationStartBEndDateList.stream().anyMatch(h -> h.getFromTime().compareTo( hospitalization.getToTime()) <= -1)
                ||
                hospitalizationList.stream()
                        .anyMatch(h -> h.getToDate().compareTo(hospitalization.getFromDate()) >= 1
                        && h.getFromDate().compareTo(hospitalization.getToDate()) <= -1)
                )
        {
            throw new Exception();
        }
        else
            hospitalizationList.add(hospitalization); // tu sprawdzanie
    }

    public List<Hospitalization> getHospitalizationListFromDate(LocalDate date, HospitalizationUnit unit) {
//        List<Hospitalization> list = new ArrayList<>();
//
//        for (Hospitalization h : getHospitalizationList()) {
//            if (h.getFromDate().isAfter(date)) continue;
//            if (h.getToDate().isBefore(date)) continue;
//            if (unit != null && !h.getUnitID().equals(unit.getId())) continue;
//            list.add(h);
//        }
//        return list;
        return   getHospitalizationList().stream()
                .filter(el -> !(el.getFromDate().isAfter(date)))
                .filter(el -> !(el.getFromDate().isBefore(date)))
                .filter(el -> !(unit != null && !el.getUnitID().equals(unit.getId())))
                .collect(Collectors.toList());
    }

    //endregion

    //region Calendar

    private List<PlanVisit> planVisits = new ArrayList<>();

    public void addPlanVisit(PlanVisit visit) {
        // Tu dodać sprawdzanie
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
