package dwa.adamy.controll.hospitalization;

import dwa.adamy.Loader;
import dwa.adamy.database.Hospitalization;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class HospitalizationDetails extends GridPane {

    @FXML
    Label patientLabel, fromLabel, toLabel;

    public HospitalizationDetails(Hospitalization h) {
        Loader.loadFX(this);

        patientLabel.setText(h.getPatient().toString());
        fromLabel.setText(h.getFromDate().toString() + " " + h.getFromTime().toString());
        toLabel.setText(h.getToDate().toString() + " " + h.getToTime().toString());
    }
}
