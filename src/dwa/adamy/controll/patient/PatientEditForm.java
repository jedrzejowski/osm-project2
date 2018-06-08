package dwa.adamy.controll.patient;

import dwa.adamy.Loader;
import dwa.adamy.database.Patient;
import dwa.adamy.database.Pesel;
import dwa.adamy.ui.Dialog;
import dwa.adamy.ui.prop.TextProp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class PatientEditForm extends VBox {

    private Patient patient;

    @FXML
    private TextProp peselProp, imieProp, nazwiskoProp, unProp, kodPocztowyProp, ulicaProp, numerDomuProp, numerMieszkaniaProp, telefonProp, miastoProp;

    public PatientEditForm() {
        Loader.loadFX(this);

        peselProp.setInputInterface(TextProp.PeselInputI);
        kodPocztowyProp.setInputInterface(TextProp.NaturalInputI);
        telefonProp.setInputInterface(TextProp.NaturalInputI);
    }

    @FXML
    private void saveAction(ActionEvent event) {

        validateAndSavePatient();

        if (anInterface != null)
            anInterface.onSave(patient);
    }

    @FXML
    private void cancelAction(ActionEvent event) {

        if (anInterface != null)
            anInterface.onCancel();
    }

    private void validateAndSavePatient() {
        if (patient == null) patient = new Patient();

        Pesel pesel = (Pesel) peselProp.getValue();

        try {
            if (pesel.toString().length() > 0 && !pesel.isValid())
                throw new Exception("Błędny pesel");


        } catch (Exception e) {

            Dialog.showPatientError();

            return;
        }

        patient.setPesel(pesel);
        patient.setName1(imieProp.getValue().toString());
        patient.setName2(nazwiskoProp.getValue().toString());

        patient.setMiasto(miastoProp.getValue().toString());
        patient.setKodPocztowy(kodPocztowyProp.getValue().toString());
        patient.setNumerDomu(numerDomuProp.getValue().toString());
        patient.setNumerMieszkania(numerMieszkaniaProp.getValue().toString());
        patient.setUlica(ulicaProp.getValue().toString());
        patient.setTelefon(telefonProp.getValue().toString());
    }

    //region Interface

    Interface anInterface = null;

    public interface Interface {
        void onSave(Patient patient);

        void onCancel();
    }

    public void setInterface(Interface anInterface) {
        this.anInterface = anInterface;
    }

    //endregion

    //region Getter && Setter

    public void setPatient(Patient patient) {

        // Nowy pacjent
        if (patient == null) {
            patient = new Patient();
            peselProp.setDisable(false);
        }

        this.patient = patient;

        unProp.setValue(patient.getUniqueID());

        peselProp.setValue(patient.getPesel().toString());
        peselProp.setDisable(true);

        imieProp.setValue(patient.getName1());
        nazwiskoProp.setValue(patient.getName2());

        miastoProp.setValue(patient.getMiasto());
        kodPocztowyProp.setValue(patient.getKodPocztowy());
        ulicaProp.setValue(patient.getUlica());
        numerDomuProp.setValue(patient.getNumerDomu());
        numerMieszkaniaProp.setValue(patient.getNumerMieszkania());
        telefonProp.setValue(patient.getTelefon());
    }

    //endregion

}
