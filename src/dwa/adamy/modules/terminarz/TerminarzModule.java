package dwa.adamy.modules.terminarz;

import dwa.adamy.Loader;
import dwa.adamy.controll.patient.PatientFinder;
import dwa.adamy.database.Patient;
import dwa.adamy.modules.Module;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.time.LocalDate;

public class TerminarzModule extends Module {

    //region Singleton

    private static TerminarzModule ourInstance = null;

    public static TerminarzModule getInstance() {
        return ourInstance;
    }

    //endregion

    @FXML
    private HBox content;

    @FXML
    private DatePicker datePicker;

    @FXML
    private PatientFinder patientFinder;

    public TerminarzModule() {
        ourInstance = this;
        Loader.loadFX(this);

        datePicker.valueProperty().addListener((ov, oldValue, newValue) -> {
            setDate(newValue);
        });

        datePicker.valueProperty().setValue(LocalDate.now());
    }

    public void clearLists() {
        content.getChildren().clear();
    }

    void addList(VisitList list) {
        content.getChildren().add(list);
    }

    private LocalDate _tempDate = null;

    public void setDate(LocalDate date) {
        if (date.equals(_tempDate)) return;
        _tempDate = date;

        clearLists();

        addList(new VisitList(this, date, "001"));
        addList(new VisitList(this, date, "002"));
        addList(new VisitList(this, date, "003"));
        addList(new VisitList(this, date, "004"));
        addList(new VisitList(this, date, "005"));
    }

    public Patient getSelectedPatient() {
        return patientFinder.getPatient();
    }
}
