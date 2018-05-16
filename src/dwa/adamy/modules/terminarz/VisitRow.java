package dwa.adamy.modules.terminarz;

import dwa.adamy.Loader;
import dwa.adamy.database.PlanVisit;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;


public class VisitRow extends HBox {
    private PlanVisit visit;
    @FXML
    private Label hourLabel, patientLabel;

    public VisitRow(PlanVisit visit) {
        Loader.loadFX(this);
        this.visit = visit;
        updateUI();
    }

    private void updateUI() {
        hourLabel.setText(String.format("%02d:%02d", visit.getHour(), visit.getMinute()));
    }

    public PlanVisit getVisit() {
        return visit;
    }

    @FXML
    private void mouseClicked(MouseEvent event){
        System.out.println("click");
    }
}
