package dwa.adamy.controll;

import dwa.adamy.database.Patient;
import dwa.adamy.database.Pesel;
import dwa.adamy.modules.Module;
import dwa.adamy.ui.prop.TextProp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class PatientEditForm extends VBox {

    private Patient patient;

    @FXML
    private TextProp peselProp, imieProp, nazwiskoProp, unProp, kodPocztowyProp, ulicaProp, numerDomuProp, numerMieszkaniaProp, telefonProp;

    public PatientEditForm() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PatientEditForm.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();

            unProp = (TextProp) lookup("#un");

            peselProp = (TextProp) lookup("#pesel");
            peselProp.setInputInterface(TextProp.PeselInputI);

            imieProp = (TextProp) lookup("#imie");
            nazwiskoProp = (TextProp) lookup("#nazwisko");

            kodPocztowyProp = nazwiskoProp = (TextProp) lookup("#kodPocztowy");
            kodPocztowyProp.setInputInterface(TextProp.NaturalInputI);

            ulicaProp = (TextProp) lookup("#ulica");
            numerDomuProp = (TextProp) lookup("#numerDomu");
            numerMieszkaniaProp = (TextProp) lookup("#numerMieszkania");

            telefonProp = (TextProp) lookup("#telefon");
            telefonProp.setInputInterface(TextProp.NaturalInputI);

        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    private void saveAction(ActionEvent event) {
        anInterface.onSave(getPatient());
    }

    @FXML
    private void cancelAction(ActionEvent event) {
        anInterface.onCancel();
    }

    //region Interface

    Interface anInterface = new Interface() {
        @Override
        public void onSave(Patient patient) {

        }

        @Override
        public void onCancel() {

        }
    };

    public interface Interface {
        void onSave(Patient patient);

        void onCancel();
    }

    public void setInterface(Interface anInterface) {
        this.anInterface = anInterface;

    }

    //endregion

    //region Getter && Setter

    public Patient getPatient() {
        patient.setPesel((Pesel) peselProp.getValue());
        patient.setName1(imieProp.getValue().toString());
        patient.setName2(nazwiskoProp.getValue().toString());

        return patient;
    }

    public void setPatient(Patient patient) {

        // Nowy pacjent
        if (patient == null) {
            this.patient = new Patient();
            peselProp.setDisable(false);
            return;
        }

        this.patient = new Patient(patient);

        unProp.setValue(patient.getUniqueID());

        peselProp.setValue(patient.getPesel().toString());
        peselProp.setDisable(true);

        imieProp.setValue(patient.getName1());
        nazwiskoProp.setValue(patient.getName2());
    }

    //endregion

}
