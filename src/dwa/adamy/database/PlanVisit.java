package dwa.adamy.database;

public class PlanVisit {
    private String docotrId;
    private int day, month, year;
    private int hour, minute, length;
    private String patientID;

    //region Getters Setters

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
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

    public String getDocotrId() {
        return docotrId;
    }

    public void setDocotrId(String docotrId) {
        this.docotrId = docotrId;
    }

    //endregion
}
