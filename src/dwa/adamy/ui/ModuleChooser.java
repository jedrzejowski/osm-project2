package dwa.adamy.ui;

import dwa.adamy.Loader;
import dwa.adamy.Main;
import dwa.adamy.database.Database;
import dwa.adamy.modules.badania.BadaniaModule;
import dwa.adamy.modules.hospitalizacje.HospitalizacjeModule;
import dwa.adamy.modules.pacjenci.PacjenciModule;
import dwa.adamy.modules.terminarz.TerminarzModule;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class ModuleChooser extends BorderPane {
    public ModuleChooser() {
        Loader.loadFX(this);
    }

    @FXML
    private void openPacjenci(ActionEvent event) {
        Main.getInstance().openModule(PacjenciModule.class);
    }

    @FXML
    private void openTerminarz(ActionEvent event) {
        Main.getInstance().openModule(TerminarzModule.class);
    }

    @FXML
    private void openBadania(ActionEvent event) {
        Main.getInstance().openModule(BadaniaModule.class);
    }

    @FXML
    private void openHospitalizacje(ActionEvent event) {
        Main.getInstance().openModule(HospitalizacjeModule.class);
    }

    @FXML
    private void saveDB(ActionEvent event) {
        Database.getInstance().save();
    }
}
