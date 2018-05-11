package dwa.adamy.database;


import java.util.List;

public class Database {

    //region Singleton

    private static Database ourInstance = new Database();

    public static Database getInstance() {
        return ourInstance;
    }

    //endregion

    private Database() {

    }

    public void save() {
    }

    public void load() {
    }

    //region Patient

    public List<Patient> searchPatient(String selector) {

        return null;
    }

    public Patient getPatient(String uniqueID) {
        return null;
    }

    public void addPatient(Patient patient) {

    }

    //endregion
}
