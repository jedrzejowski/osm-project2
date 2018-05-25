package dwa.adamy.modules.terminarz;

import dwa.adamy.Loader;
import dwa.adamy.controll.calendar.VisitList;
import dwa.adamy.controll.patient.PatientFinder;
import dwa.adamy.database.Database;
import dwa.adamy.database.Doctor;
import dwa.adamy.database.Patient;
import dwa.adamy.modules.Module;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.HBox;

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

    public void addList(VisitList list) {
        content.getChildren().add(list);
    }

    private LocalDate _tempDate = null;

    public void setDate(LocalDate date) {
        if (date.equals(_tempDate)) return;
        _tempDate = date;

        clearLists();

        for (Doctor doctor : Database.getInstance().getDoctors()) {
            addList(new VisitList(this, date, doctor));
        }
    }

    public Patient getSelectedPatient() {
        return patientFinder.getPatient();
    }
}
