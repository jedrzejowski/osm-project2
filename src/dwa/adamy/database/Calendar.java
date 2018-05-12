package dwa.adamy.database;

import java.util.ArrayList;

public class Calendar {
    private ArrayList<PlanVisit> calendarArray = new ArrayList<>();

    public void addVisit(PlanVisit visit){
        calendarArray.add(visit);
    }

    public ArrayList<PlanVisit> getVisitsFromFay(int year, int month, int day){
        return null;
    }
}
