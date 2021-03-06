package dwa.adamy.database;

import org.json.JSONObject;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Klasa obiektów reprezentujących dane badania krwi
 * <p>
 * Dlaczego wartości parametrów są string'ami a nie int'ami?
 * Otóż dokumentacja medyczna ma być tworzona prze lekarzy, lub im podobnych. Aplikacja nie powinna ograniczać
 * możliwości tworzenia i edycji danych medycznych nie będących danymi osobowymi. To lekarz decyduje co wpisać w rubrykę
 * i to lekarz będzie te dane interpretował, a nie aplikacja
 */
public class Examination {

    private LocalDate date;
    private LocalTime time;
    private String patientID = "";
    private String result = "";
    private String name = "";
    private String range = "";
    private String doctorID = "";

    public Examination() {
    }

    public Examination(JSONObject object) {
        date = LocalDate.parse(object.getString("date"));
        time = LocalTime.parse(object.getString("time"));
        result = object.getString("result");
        name = object.getString("name");
        range = object.getString("range");
        doctorID = object.getString("doctorID");
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }
    public void setDoctor(Doctor doctor) {
        this.doctorID = doctor.getId();
    }
    public Doctor getDoctor(){
        return Database.getInstance().getDoctorByID(doctorID);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public JSONObject toJSON() {
        JSONObject obj = new JSONObject();
        obj.put("date", date.toString());
        obj.put("time", time.toString());
        obj.put("doctorID", doctorID);
        obj.put("range", range);
        obj.put("name", name);
        obj.put("result", result);
        return obj;
    }

}
