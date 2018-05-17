package dwa.adamy.modules.pacjenci;

import dwa.adamy.Loader;
import dwa.adamy.controll.patient.PatientEditForm;
import dwa.adamy.controll.patient.PatientList;
import dwa.adamy.database.Database;
import dwa.adamy.database.Patient;
import dwa.adamy.modules.Module;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class PacjenciModule extends Module {

    public enum State {
        LIST,
        NEWPATIENT,
        EDITPATIENT
    }

    State myState;

    @FXML
    private Pane content;

    public PacjenciModule() {
        Loader.loadFX(this);

        setSetState(State.LIST);
    }

    private void setContent(Node node) {
        content.getChildren().clear();
        content.getChildren().add(node);
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
