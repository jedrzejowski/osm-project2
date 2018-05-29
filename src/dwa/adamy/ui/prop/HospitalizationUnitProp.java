package dwa.adamy.ui.prop;

import dwa.adamy.Loader;
import dwa.adamy.database.Database;
import dwa.adamy.database.HospitalizationUnit;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class HospitalizationUnitProp extends HBox {

    @FXML
    Label label;
    @FXML
    ComboBox<HospitalizationUnit> comboBox;

    public HospitalizationUnitProp() {
        Loader.loadFX(this);
        comboBox.setItems(FXCollections.observableArrayList(Database.getInstance().getHospitalizationUnitList()));
    }

    //region Props

    public String getLabel() {
        return label.getText();
    }

    public void setLabel(String value) {
        label.setText(value);
    }

    public HospitalizationUnit getValue() {
        return comboBox.getValue();
    }

    public void setValue(HospitalizationUnit value) {
        comboBox.setValue(value);
    }

    public void setValueByID(String value) {
        comboBox.setValue(Database.getInstance().getHospitalizationUnitByID(value));
    }

    //endregion

}