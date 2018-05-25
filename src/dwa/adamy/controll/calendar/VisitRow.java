package dwa.adamy.controll.calendar;

import dwa.adamy.Loader;
import dwa.adamy.database.Patient;
import dwa.adamy.database.PlanVisit;
import dwa.adamy.modules.terminarz.TerminarzModule;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;


public class VisitRow extends HBox {
    private TerminarzModule module;
    private PlanVisit visit;
    @FXML
    private Label hourLabel, patientLabel;

    public VisitRow(TerminarzModule module, PlanVisit visit) {
        Loader.loadFX(this);
        this.module = module;
        this.visit = visit;
        updateUI();
    }

    private void updateUI() {
        hourLabel.setText(visit.getTime().toString());
    }

    public PlanVisit getVisit() {
        return visit;
    }

    @FXML
    private void mouseClicked(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {

            Patient patient = module.getSelectedPatient();

            if (patient == null) return;

            visit.setPatientID(patient.getUniqueID());
            patientLabel.setText(patient.toString());
        }

        if (event.getButton() == MouseButton.SECONDARY) {
            visit.setPatientID("");
            patientLabel.setText("");
        }
    }
}
