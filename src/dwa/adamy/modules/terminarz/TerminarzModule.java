package dwa.adamy.modules.terminarz;

import dwa.adamy.modules.Module;
import dwa.adamy.modules.pacjenci.PacjenciModule;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class TerminarzModule extends Module {

    Pane content = null;

    public TerminarzModule() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PacjenciModule.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();

            content = (Pane) lookup("#content");

        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    void clearLists(){
        content.getChildren().clear();
    }

    void addList(VisitList list){
        content.getChildren().add(list);
    }
}
