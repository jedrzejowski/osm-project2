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
import java.util.ArrayList;
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

    //region Singleton

    private Calendar calendar = null;

    public Calendar getCalendar() {
        return calendar;
    }

    //endregion

    private Database() {

    }

    //region Save and Load

    public JSONObject toJSON() {
        JSONObject obj = new JSONObject();

        JSONArray patients = new JSONArray();
        for (Patient p : patientList)
            patients.put(p.toJSON());
        obj.put("patients", patients);

        JSONArray examinations = new JSONArray();
        for (Examination e : examinationList)
            examinations.put(e.toJSON());
        obj.put("examinations", examinations);

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

    public Patient getPatient(String uniqueID) {
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


    //region

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

    //endregion
}
