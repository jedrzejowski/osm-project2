package dwa.adamy.modules.pacjenci;

import dwa.adamy.Main;
import dwa.adamy.controll.PatientEditForm;
import dwa.adamy.controll.PatientList;
import dwa.adamy.database.Database;
import dwa.adamy.database.Patient;
import dwa.adamy.modules.Module;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class PacjenciModule extends Module {

    public enum State {
        LIST,
        NEWPATIENT,
        EDITPATIENT
    }

    State myState;

    Pane subPane = null;

    public PacjenciModule() {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PacjenciModule.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();

            subPane = (Pane) lookup("#subPane");

            setSetState(State.LIST);

        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private void setContent(Node node){

    }

    public void setSetState(State state) {
        setSetState(state, null);
    }

    public void setSetState(State state, Object data) {
        switch (state) {

            case LIST:

                PatientList list = new PatientList();
                list.setInterface(new PatientList.Interface() {
                    @Override
                    public void onSelectPatient(Patient patient) {
                        setSetState(State.EDITPATIENT, patient);
                    }
                });

                list.reloadPatients();
                setContent(list);

                break;

            case NEWPATIENT: {

                PatientEditForm editor = new PatientEditForm();
                editor.setInterface(new PatientEditForm.Interface() {
                    @Override
                    public void onSave(Patient patient) {

                        try {
                            Database.getInstance().addPatient(patient);
                        } catch (Database.PatientAlreadyExistsException e) {
                            //TODO Komunikat o istniejąceym pacjencie
                        }

                        setSetState(State.LIST);
                    }

                    @Override
                    public void onCancel() {
                        setSetState(State.LIST);
                    }
                });

                setContent(editor);
                break;
            }


            case EDITPATIENT: {

                PatientEditForm editor = new PatientEditForm();
                editor.setPatient((Patient) data);
                editor.setInterface(new PatientEditForm.Interface() {
                    @Override
                    public void onSave(Patient patient) {
                        setSetState(State.LIST);
                    }

                    @Override
                    public void onCancel() {
                        setSetState(State.LIST);
                    }
                });

                setContent(editor);

                break;
            }

        }

        myState = state;
    }

    @FXML
    private void newPatient(ActionEvent event) {
        setSetState(State.NEWPATIENT);
    }
}
