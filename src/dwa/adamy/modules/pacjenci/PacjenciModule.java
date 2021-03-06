package dwa.adamy.modules.pacjenci;

import dwa.adamy.Loader;
import dwa.adamy.controll.patient.PatientEditForm;
import dwa.adamy.controll.patient.PatientList;
import dwa.adamy.database.Database;
import dwa.adamy.database.Patient;
import dwa.adamy.modules.Module;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;

public class PacjenciModule extends Module {

    public enum State {
        LIST,
        NEW,
        EDIT
    }

    State myState;

    @FXML
    private BorderPane content;

    public PacjenciModule() {
        Loader.loadFX(this);

        setState(State.LIST);
    }

    private void setContent(Node node) {
        content.setCenter(node);
    }

    public void setState(State state) {
        setState(state, null);
    }

    public void setState(State state, Object data) {
        switch (state) {

            case LIST:

                PatientList list = new PatientList();
                list.setInterface(new PatientList.Interface() {
                    @Override
                    public void onSelectPatient(Patient patient) {
                        setState(State.EDIT, patient);
                    }

                    @Override
                    public void onNewPatient() {
                        setState(State.NEW);
                    }
                });

                list.reloadPatients();
                setContent(list);

                break;

            case NEW: {

                PatientEditForm editor = new PatientEditForm();
                editor.setInterface(new PatientEditForm.Interface() {
                    @Override
                    public void onSave(Patient patient) {

                        try {
                            Database.getInstance().addPatient(patient);
                            setState(State.LIST);
                        } catch (Database.PatientAlreadyExistsException e) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Błąd bazydanych");
                            alert.setHeaderText(null);
                            alert.setContentText("Pacjent o takim peselu istnieje");

                            alert.showAndWait();
                        }

                    }

                    @Override
                    public void onCancel() {
                        setState(State.LIST);
                    }
                });

                setContent(editor);
                break;
            }


            case EDIT: {

                PatientEditForm editor = new PatientEditForm();
                editor.setPatient((Patient) data);
                editor.setInterface(new PatientEditForm.Interface() {
                    @Override
                    public void onSave(Patient patient) {
                        setState(State.LIST);
                    }

                    @Override
                    public void onCancel() {
                        setState(State.LIST);
                    }
                });

                setContent(editor);

                break;
            }

        }

        myState = state;
    }

}
