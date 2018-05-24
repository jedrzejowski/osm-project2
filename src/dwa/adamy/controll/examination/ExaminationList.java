package dwa.adamy.controll.examination;

import dwa.adamy.Loader;
import dwa.adamy.database.Examination;
import dwa.adamy.lib.DateUtils;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class ExaminationList extends BorderPane {

    @FXML
    private GridPane contentGrid;

    @FXML
    private DatePicker datePicker;

    public ExaminationList(Interface anInterface) {
        Loader.loadFX(this);
        this.anInterface = anInterface;

        datePicker.setValue(LocalDate.now());

        int lastI = contentGrid.getColumnConstraints().size();

        for (int h = 8, i = 1; h < 18; h++) {
            for (int m = 0; m < 60; m += 15, i++) {
                contentGrid.getRowConstraints().add(new RowConstraints());

                Label timeLabel = new Label();
                timeLabel.setText(String.format("%02d:%02d", h, m));
                contentGrid.add(timeLabel, 0, i);

                final int M = m, H = h;

                Button action = new Button();
                action.setText(String.format("Dodaj", h, m));
                action.setOnAction(actionEvent -> {
                    Date date = DateUtils.asDate(datePicker.getValue());
                    date.setHours(H);
                    date.setMinutes(M);
                    anInterface.onAdd(date);
                });
                contentGrid.add(action, lastI, i);
            }
        }
    }

    public void showExamination(Examination examination) {

    }

    void clearGrid() {
        for (Node node : contentGrid.getChildren()) {
            int colI = GridPane.getColumnIndex(node);
            if (GridPane.getRowIndex(node) != 0 && colI != 0 &&
                    colI != contentGrid.getColumnConstraints().size() - 1) {
                contentGrid.getChildren().remove(node);
            }
        }
    }

    Interface anInterface;

    public interface Interface {
        void onAdd(Date date);
        void onEdit(Examination examination);
    }
}
