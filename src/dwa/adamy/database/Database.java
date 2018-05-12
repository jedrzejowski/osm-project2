package dwa.adamy.database;


import java.util.ArrayList;
import java.util.List;

public class Database {

    //region Singleton

    private static Database ourInstance = new Database();

    public static Database getInstance() {
        return ourInstance;
    }

    //endregion

    //region Singleton

    private Calendar calendar = new Calendar();

    public Calendar getCalendar() {
        return calendar;
    }

    //endregion

    private Database() {

    }

    public void save() {
    }

    public void load() {
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

    public void addPatient(Patient patient) throws PatientAlreadyExistsException {
        patientList.add(patient);
    }

    public class PatientAlreadyExistsException extends Exception {
    }

    //endregion
}
