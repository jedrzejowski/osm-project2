package dwa.adamy.modules.terminarz;

import dwa.adamy.modules.Module;
import dwa.adamy.modules.pacjenci.PacjenciModule;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class TerminarzModule extends Module {
    public TerminarzModule() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PacjenciModule.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();


        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
