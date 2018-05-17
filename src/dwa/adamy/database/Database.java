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
import java.util.ArrayList;
import java.util.List;

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

            JSONArray patients = obj.getJSONArray("patients");
            for (Object p : patients)
                patientList.add(new Patient((JSONObject) p));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //region Patient

    private List<Patient> patientList = new ArrayList<>();

    public List<Patient> getList() {
        return patientList;
    }

    public List<Patient> searchPatient(String selector) {

        return null;
    }

    public Patient getPatient(String uniqueID) {
        return null;
    }

    public List<Patient> findPatientsBySelector(String selector){
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

    //endregion
}
