package dwa.adamy.modules.pacjenci;

import dwa.adamy.database.Patient;
import dwa.adamy.modules.Module;
import dwa.adamy.ui.prop.TextProp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class PatientEditForm extends VBox {

    Patient patient;

    @FXML
    TextProp peselProp, imieProp, nazwiskoProp, unProp;

    public PatientEditForm() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PatientEditForm.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();

            unProp = (TextProp) lookup("#un");
            peselProp = (TextProp) lookup("#pesel");
            imieProp = (TextProp) lookup("#imie");
            nazwiskoProp = (TextProp) lookup("#nazwisko");

        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    //region Interface

    Interface anInterface;

    interface Interface {
        void onSave(Patient patient);
    }

    public void setInterface(Interface anInterface) {
        this.anInterface = anInterface;

    }

    //endregion

    //region Getter && Setter

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;

        if (patient == null) {
            // Nowy pacjent
            peselProp.setDisable(false);
            return;
        }

        peselProp.setText(patient.getPesel().toString());
        peselProp.setDisable(true);
        imieProp.setText(patient.getName1());
        nazwiskoProp.setText(patient.getName2());
    }

    //endregion

}
