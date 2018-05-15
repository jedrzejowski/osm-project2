package dwa.adamy.modules.terminarz;

import dwa.adamy.Loader;
import dwa.adamy.database.PlanVisit;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;


public class VisitRow extends HBox {
    private PlanVisit visit;

    public VisitRow(PlanVisit visit) {
        Loader.loadFX(this);
        this.visit = visit;
    }

    public PlanVisit getVisit() {
        return visit;
    }

    @FXML
    private void mouseClicked(MouseEvent event){
        System.out.println("click");
    }
}
