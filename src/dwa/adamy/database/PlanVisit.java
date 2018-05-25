package dwa.adamy.database;

import org.json.JSONObject;

import java.time.LocalDate;
import java.time.LocalTime;

public class PlanVisit {
    private String doctorId = "";
    private LocalDate date;
    private LocalTime time;
    private String patientID = "";

    public PlanVisit() {
    }


    public PlanVisit(JSONObject obj) {
        doctorId = obj.getString("doctorId");
        patientID = obj.getString("patientID");
        date = LocalDate.parse(obj.getString("date"));
        time = LocalTime.parse(obj.getString("time"));
    }

    public JSONObject toJSON() {
        JSONObject obj = new JSONObject();
        obj.put("doctorId", doctorId);
        obj.put("patientID", patientID);
        obj.put("date", date.toString());
        obj.put("time", time.toString());
        return obj;
    }

    //region Getters Setters

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

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;

        if (this.patientID.length() > 0)
            Database.getInstance().addPlanVisit(this);
        else
            Database.getInstance().removePlanVisit(this);

    }

    public Patient getPatient() {
        return Database.getInstance().getPatientByID(patientID);
    }

    public void setPatient(Patient patient) {

        if (patient == null)
            setPatientID("");
        else
            setPatientID(patient.getUniqueID());
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctor(Doctor doctor) {
        this.doctorId = doctor.getId();
    }

    public void setDoctorId(String docotrId) {
        this.doctorId = docotrId;
    }

    //endregion

}
