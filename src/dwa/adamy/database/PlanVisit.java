package dwa.adamy.database;

import java.time.LocalDate;
import java.time.LocalTime;

public class PlanVisit {
    private String doctorId;
    private LocalDate date;
    private LocalTime time;
    private int length;
    private String patientID;

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

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;

        //TODO oskryptować dodanie do bazy daanych
        if (this.patientID.length() > 0) {

        } else {

        }
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
