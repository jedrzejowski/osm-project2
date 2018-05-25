package dwa.adamy.ui.prop;

import dwa.adamy.Loader;
import dwa.adamy.database.Database;
import dwa.adamy.database.Doctor;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import javax.print.Doc;
import java.time.LocalDate;

public class DoctorProp extends HBox {

    @FXML
    Label label;
    @FXML
    ComboBox<Doctor> comboBox;

    public DoctorProp() {
        Loader.loadFX(this);
        comboBox.setItems(FXCollections.observableArrayList(Database.getInstance().getDoctors()));
    }

    //region Props

    public String getLabel() {
        return label.getText();
    }

    public void setLabel(String value) {
        label.setText(value);
    }

    public Doctor getValue() {
        return comboBox.getValue();
    }

    public void setValue(Doctor value) {
        comboBox.setValue(value);
    }

    public void setValueByID(String value) {
        comboBox.setValue(Database.getInstance().getDoctorByID(value));
    }

    //endregion

}
