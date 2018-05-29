package dwa.adamy.database;

import org.json.JSONObject;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class Hospitalization {
    private String ID = UUID.randomUUID().toString();

    private String unitID, patientID;
    private LocalDate fromDate, toDate;
    private LocalTime fromTime, toTime;

    public Hospitalization() {
    }

    public Hospitalization(JSONObject obj) {
        ID = obj.getString("ID");
        unitID = obj.getString("unitID");
        patientID = obj.getString("patientID");
        fromDate = LocalDate.parse(obj.getString("fromDate"));
        fromTime = LocalTime.parse(obj.getString("fromTime"));
        toDate = LocalDate.parse(obj.getString("toDate"));
        toTime = LocalTime.parse(obj.getString("toTime"));
    }

    public JSONObject toJSON() {
        JSONObject obj = new JSONObject();
        obj.put("ID", ID);
        obj.put("unitID", unitID);
        obj.put("patientID", patientID);
        obj.put("fromDate", fromDate.toString());
        obj.put("toDate", toDate.toString());
        obj.put("fromTime", fromTime.toString());
        obj.put("toTime", toTime.toString());
        return obj;
    }

    public String getUnitID() {
        return unitID;
    }

    public HospitalizationUnit getUnit() {
        return Database.getInstance().getHospitalizationUnitByID(unitID);
    }

    public void setUnitID(String unitID) {
        this.unitID = unitID;
    }

    public void setUnit(HospitalizationUnit unit) {
        setUnitID(unit.getId());
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public Patient getPatient() {
        return Database.getInstance().getPatientByID(getPatientID());
    }

    public void setPatient(Patient patient) {
        setPatientID(patient.getUniqueID());
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public LocalTime getFromTime() {
        return fromTime;
    }

    public void setFromTime(LocalTime fromTime) {
        this.fromTime = fromTime;
    }

    public LocalTime getToTime() {
        return toTime;
    }

    public void setToTime(LocalTime toTime) {
        this.toTime = toTime;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setFrom(LocalDate fromDate, LocalTime fromTime) {
        setFromDate(fromDate);
        setFromTime(fromTime);
    }

    public void setTo(LocalDate fromTo, LocalTime toTime) {
        setToDate(fromTo);
        setToTime(toTime);
    }
}
