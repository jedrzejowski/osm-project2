package dwa.adamy.ui;

import dwa.adamy.Main;
import dwa.adamy.modules.pacjenci.PacjenciModule;
import dwa.adamy.modules.terminarz.TerminarzModule;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ModuleChooser extends VBox {
    public ModuleChooser() {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ModuleChooser.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    private void openPacjenci(ActionEvent event) {
        Main.getInstance().openModule(PacjenciModule.class);
    }

    @FXML
    private void openTerminarz(ActionEvent event) {
        Main.getInstance().openModule(TerminarzModule.class);
    }
}
