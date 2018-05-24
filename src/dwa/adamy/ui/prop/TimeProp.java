package dwa.adamy.ui.prop;

import com.jfoenix.controls.JFXTimePicker;
import dwa.adamy.Loader;
import dwa.adamy.lib.DateUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.time.LocalTime;

public class TimeProp extends HBox {

    @FXML
    Label label;
    @FXML
    JFXTimePicker timePicker;

    public TimeProp() {
        Loader.loadFX(this);
        timePicker.setIs24HourView(true);
    }

    //region Props

    public String getLabel() {
        return label.getText();
    }

    public void setLabel(String value) {
        label.setText(value);
    }

    public LocalTime getValue() {
        return timePicker.getValue();
    }

    public void setValue(LocalTime value) {
        timePicker.setValue(value);
    }

    //endregion

}
