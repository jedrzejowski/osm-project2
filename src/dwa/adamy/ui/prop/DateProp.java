package dwa.adamy.ui.prop;

import dwa.adamy.Loader;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.time.LocalDate;

public class DateProp extends HBox {

    @FXML
    Label label;
    @FXML
    DatePicker datePicker;

    public DateProp() {
        Loader.loadFX(this);
    }

    //region Props

    public String getLabel() {
        return label.getText();
    }

    public void setLabel(String value) {
        label.setText(value);
    }

    public LocalDate getValue() {
        return datePicker.getValue();
    }

    public void setValue(LocalDate value) {
        datePicker.setValue(value);
    }

    //endregion

}
