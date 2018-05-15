package dwa.adamy.modules.terminarz;

import dwa.adamy.Loader;
import dwa.adamy.database.Patient;
import dwa.adamy.modules.Module;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class TerminarzModule extends Module {

    //region Singleton

    private static TerminarzModule ourInstance = null;

    public static TerminarzModule getInstance() {
        return ourInstance;
    }

    //endregion

    @FXML
    private Pane content;

    public TerminarzModule() {
        ourInstance = this;
        Loader.loadFX(this);

        addList(new VisitList());
    }

    void clearLists() {
        content.getChildren().clear();
    }

    void addList(VisitList list) {
        content.getChildren().add(list);
    }

    public Patient getSelectedPatient(){
        return new Patient();
    }
}
