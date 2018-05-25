package dwa.adamy.controll.calendar;

import dwa.adamy.Loader;
import dwa.adamy.database.Doctor;
import dwa.adamy.database.PlanVisit;
import dwa.adamy.modules.terminarz.TerminarzModule;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class VisitList extends VBox {

    private TerminarzModule module;
    private Doctor doctor;
    private LocalDate date;

    @FXML
    private Pane content;
    @FXML
    private Label label1, label2;

    public VisitList(TerminarzModule module, LocalDate date, Doctor doctor) {
        Loader.loadFX(this);
        this.module = module;
        this.doctor = doctor;
        this.date = date;

        label1.setText(date.format(DateTimeFormatter.ofPattern("ccc, d LLL YYYY")).toLowerCase());
        label2.setText("dr " + doctor.getFullName());

        //Generacja terminów
        for (int h = 8; h < 18; h++) {
            for (int m = 0; m < 60; m += 15) {
                PlanVisit visit = new PlanVisit();
                visit.setDate(date);
                visit.setTime(LocalTime.of(h, m));
                visit.setDoctor(doctor);
                content.getChildren().add(new VisitRow(module, visit));
            }
        }

        updateUI();
    }

    private void updateUI() {
        boolean q = false;

        for (Node n : content.getChildren()) {
            if (q) {
                n.getStyleClass().add("odd");
                n.getStyleClass().remove("even");
            } else {
                n.getStyleClass().add("even");
                n.getStyleClass().remove("odd");
            }

            q = !q;
        }
    }

    public void setVisitList(ArrayList<PlanVisit> list) {

    }

    public void addVisit(PlanVisit visit) {
        content.getChildren().add(new VisitRow(module, visit));
        updateUI();
    }

}
