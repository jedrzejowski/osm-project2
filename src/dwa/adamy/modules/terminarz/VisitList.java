package dwa.adamy.modules.terminarz;

import dwa.adamy.database.PlanVisit;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ArrayList;

public class VisitList extends Pane {

    public VisitList() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("VisitList.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();

            content = (Pane) lookup("#content");

        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void setTitle(String title) {

    }

    public void setVisitList(ArrayList<PlanVisit> list) {

    }
}
