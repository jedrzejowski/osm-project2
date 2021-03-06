package dwa.adamy.controll.hospitalization;

import dwa.adamy.Loader;
import dwa.adamy.controll.patient.PatientFinder;
import dwa.adamy.database.Database;
import dwa.adamy.database.Hospitalization;
import dwa.adamy.ui.Dialog;
import dwa.adamy.ui.prop.DateProp;
import dwa.adamy.ui.prop.HospitalizationUnitProp;
import dwa.adamy.ui.prop.TimeProp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class HospitalizationEditor extends VBox {

    @FXML
    private HospitalizationUnitProp hospitalizationUnitProp;
    @FXML
    private DateProp fromDateProp, toDateProp;
    @FXML
    private TimeProp fromTimeProp, toTimeProp;
    @FXML
    private PatientFinder patientFinder;

    public Hospitalization hospitalization;

    public HospitalizationEditor(Hospitalization hospitalization, Interface anInterface) {
        Loader.loadFX(this);

        setHospitalization(hospitalization);
        this.anInterface = anInterface;
    }

    public Hospitalization getHospitalization() {

        hospitalization.setUnit(hospitalizationUnitProp.getValue());

        hospitalization.setFromDate(fromDateProp.getValue());
        hospitalization.setFromTime(fromTimeProp.getValue());

        hospitalization.setToDate(toDateProp.getValue());
        hospitalization.setToTime(toTimeProp.getValue());

        hospitalization.setPatient(patientFinder.getPatient());

        return hospitalization;
    }

    public void setHospitalization(Hospitalization hospitalization) {
        this.hospitalization = hospitalization;

        hospitalizationUnitProp.setValue(hospitalization.getUnit());

        fromDateProp.setValue(hospitalization.getFromDate());
        fromTimeProp.setValue(hospitalization.getFromTime());

        toDateProp.setValue(hospitalization.getToDate());
        toTimeProp.setValue(hospitalization.getToTime());

        patientFinder.setPatient(hospitalization.getPatient());
    }

    @FXML
    private void saveAction(ActionEvent event) {
        if (anInterface != null) {

            Hospitalization temp = new Hospitalization();

            temp.setID(hospitalization.getID());
            temp.setUnit(hospitalizationUnitProp.getValue());
            temp.setFromDate(fromDateProp.getValue());
            temp.setFromTime(fromTimeProp.getValue());
            temp.setToDate(toDateProp.getValue());
            temp.setToTime(toTimeProp.getValue());

            if (!Database.getInstance().isHospitalizationTimeGood(temp)) {
                Dialog.showHospitalizationError();
                return;
            }

            anInterface.onSave(getHospitalization());
        }
    }

    @FXML
    private void cancelAction(ActionEvent event) {

        if (anInterface != null)
            anInterface.onCancel();
    }

    Interface anInterface;

    public interface Interface {
        void onSave(Hospitalization hospitalization);

        void onCancel();
    }
}
