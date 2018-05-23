package dwa.adamy.controll.patient;

import dwa.adamy.Loader;
import dwa.adamy.database.Database;
import dwa.adamy.database.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class PatientFinder extends HBox {
    @FXML
    private ComboBox<Patient> combo;
    @FXML
    private Button button;

    private String lastVal = "";

    public PatientFinder() {
        Loader.loadFX(this);

        combo.setItems(FXCollections.observableArrayList(Database.getInstance().getPatientList()));

    }

    public Patient getPatient(){
        return combo.getSelectionModel().getSelectedItem();
    }
}
