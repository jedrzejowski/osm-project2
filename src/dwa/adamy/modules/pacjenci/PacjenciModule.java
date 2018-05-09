package dwa.adamy.modules.pacjenci;

import dwa.adamy.modules.Module;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class PacjenciModule extends Module {
    public PacjenciModule() {
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
