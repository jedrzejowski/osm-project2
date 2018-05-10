package dwa.adamy.modules.pacjenci;

import dwa.adamy.Main;
import dwa.adamy.database.Patient;
import dwa.adamy.modules.Module;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;

import java.io.IOException;

public class PacjenciModule extends Module {

    ScrollPane subPane = null;

    public PacjenciModule() {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PacjenciModule.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();

            subPane = (ScrollPane) lookup("#subPane");

        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    private void newPatient(ActionEvent event) {
        PatientEditForm editor = new PatientEditForm();
        editor.setInterface(new PatientEditForm.Interface() {
            @Override
            public void onSave(Patient patient) {

            }
        });
        subPane.setContent(editor);

    }
}
