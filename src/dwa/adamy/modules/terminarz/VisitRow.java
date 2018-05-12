package dwa.adamy.modules.terminarz;

import dwa.adamy.database.PlanVisit;
import javafx.scene.layout.Pane;

public class VisitRow extends Pane {
    private PlanVisit visit;

    public PlanVisit getVisit() {
        return visit;
    }

    public void setVisit(PlanVisit visit) {
        this.visit = visit;
    }
}
