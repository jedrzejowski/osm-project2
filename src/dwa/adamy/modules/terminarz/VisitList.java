package dwa.adamy.modules.terminarz;

import dwa.adamy.Loader;
import dwa.adamy.database.PlanVisit;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

public class VisitList extends VBox {

    @FXML
    private Pane content;
    @FXML
    private Label label1, label2, label3;

    public VisitList() {
        Loader.loadFX(this);


        for (int i = 0; i < 20; i++) {
            addVisit(new PlanVisit());
        }
    }

    public void setDoctor(String title) {

    }

    public void setVisitList(ArrayList<PlanVisit> list) {

    }

    public void addVisit(PlanVisit visit) {
        content.getChildren().add(new VisitRow(visit));
    }

}
