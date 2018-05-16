package dwa.adamy.database;

import org.json.JSONObject;

import java.util.ArrayList;

public class Calendar {

    public Calendar(JSONObject obj) {

    }

    public JSONObject toJSON(){
        return null;
    }

    private ArrayList<PlanVisit> calendarArray = new ArrayList<>();

    public void addVisit(PlanVisit visit){
        calendarArray.add(visit);
    }

    public ArrayList<PlanVisit> getVisitsFromFay(int year, int month, int day){
        return null;
    }
}
