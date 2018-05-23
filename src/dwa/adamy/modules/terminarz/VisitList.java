package dwa.adamy.modules.terminarz;

import dwa.adamy.Loader;
import dwa.adamy.database.PlanVisit;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class VisitList extends VBox {

    private TerminarzModule module;
    private String doctorId;
    private LocalDate date;

    @FXML
    private Pane content;
    @FXML
    private Label label1, label2;

    public VisitList(TerminarzModule module, LocalDate date, String doctorId) {
        Loader.loadFX(this);
        this.module = module;
        this.doctorId = doctorId;
        this.date = date;

        label1.setText(date.format(DateTimeFormatter.ofPattern("ccc, d LLL YYYY")).toLowerCase());

        //Generacja terminów
        for (int h = 8; h < 18; h++) {
            for (int m = 0; m < 60; m += 15) {
                PlanVisit visit = new PlanVisit();
                visit.setDay(date.getDayOfMonth());
                visit.setMonth(date.getMonthValue());
                visit.setYear(date.getYear());
                visit.setHour(h);
                visit.setMinute(m);
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

    public void setDoctor(String title) {

    }

    public void setVisitList(ArrayList<PlanVisit> list) {

    }

    public void addVisit(PlanVisit visit) {
        content.getChildren().add(new VisitRow(module, visit));
        updateUI();
    }

}
