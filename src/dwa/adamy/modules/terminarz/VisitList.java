package dwa.adamy.modules.terminarz;

import dwa.adamy.Loader;
import dwa.adamy.database.PlanVisit;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

public class VisitList extends VBox {

    @FXML
    private Pane content;
    @FXML
    private Label label1, label2;

    public VisitList() {
        Loader.loadFX(this);

        //TODO usunąć to
        for (int h = 8; h < 18; h++) {
            for (int m = 0; m < 60; m += 15) {
                PlanVisit visit = new PlanVisit();
                visit.setHour(h);
                visit.setMinute(m);
                content.getChildren().add(new VisitRow(visit));
            }
        }

        updateUI();
    }

    private void updateUI() {
        boolean q = false;

        for (Node n : content.getChildren()) {
            if (q) {
                n.getStyleClass().add("odd");
                n.getStyleClass().remove("even");
            } else {
                n.getStyleClass().add("even");
                n.getStyleClass().remove("odd");
            }

            q = !q;
        }
    }

    public void setDoctor(String title) {

    }

    public void setVisitList(ArrayList<PlanVisit> list) {

    }

    public void addVisit(PlanVisit visit) {
        content.getChildren().add(new VisitRow(visit));
        updateUI();
    }

}
