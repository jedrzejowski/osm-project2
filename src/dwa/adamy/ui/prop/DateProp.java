package dwa.adamy.ui.prop;

import dwa.adamy.Loader;
import dwa.adamy.database.Pesel;
import dwa.adamy.lib.DateUtils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

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
