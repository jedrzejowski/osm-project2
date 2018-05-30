package dwa.adamy.controll.examination;

import dwa.adamy.Loader;
import dwa.adamy.database.Database;
import dwa.adamy.database.Examination;
import dwa.adamy.lib.BiHashMap;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class ExaminationList extends BorderPane {

    @FXML
    private GridPane contentGrid;
    @FXML
    private DatePicker datePicker;

    private List<Button> actions = new ArrayList<>();

    public ExaminationList(Interface anInterface, LocalDate date) {
        Loader.loadFX(this);
        this.anInterface = anInterface;

        boolean odd = false;
        BorderPane pane;

        for (int h = 8, i = 1; h < 18; h++) {
            for (int m = 0; m < 60; m += 15, i++) {
                contentGrid.getRowConstraints().add(new RowConstraints());
                final LocalTime time = LocalTime.of(h, m, 0, 0);

                {
                    pane = new BorderPane();
                    pane.getStyleClass().add(odd ? "odd" : "even");

                    Label timeLabel = new Label();
                    timeLabel.setText(String.format("%02d:%02d", h, m));
                    timeLabel.getStyleClass().add("timeLabel");

                    pane.setCenter(timeLabel);
                    contentGrid.add(pane, 0, i);
                }

                {
                    for (int c = 1; c < 5; c++)
                        setLabel("", c, i);
                }

                {
                    pane = new BorderPane();
                    pane.getStyleClass().add(odd ? "odd" : "even");

                    Button action = new Button();
                    action.getProperties().put("_time", time);
                    actions.add(action);

                    pane.setCenter(action);
                    contentGrid.add(pane, 5, i);
                }

                odd = !odd;
            }
        }

        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            setDate(newValue);
        });

        datePicker.setValue(date);
    }

    private LocalDate _tempDate;

    private void setDate(LocalDate date) {
        if (date.equals(_tempDate)) return;
        _tempDate = date;

        clearGrid();

        for (Examination e : Database.getInstance().getExaminationFromDate(date)) {
            showExamination(e);
        }
    }

    private BiHashMap<Integer, Integer, Label> labels = new BiHashMap<>();

    private void setLabel(String txt, int col, int row) {
        if (labels.containsKeys(col, row)) {

            labels.get(col, row).setText(txt);
        } else {

            Label label = new Label(txt);
            labels.put(col, row, label);

            BorderPane pane = new BorderPane();
            pane.getStyleClass().add(row % 2 == 0 ? "odd" : "even");
            pane.setCenter(label);
            contentGrid.add(pane, col, row);
        }

    }

    public void showExamination(Examination exam) {
        int rowI = (exam.getTime().getHour() - 8) * 4 + exam.getTime().getMinute() / 15 + 1;

        setLabel(exam.getName(), 1, rowI);
        setLabel(exam.getRange(), 2, rowI);
        setLabel(exam.getResult(), 3, rowI);
        setLabel(exam.getDoctor().getFullName(), 4, rowI);

        Button action = actions.get(rowI - 1);
        action.setText("Edytuj");
        action.setOnAction(actionEvent -> {
            anInterface.onEdit(exam);
        });
    }

    private void clearGrid() {
        for (Button action : actions) {
            action.setText("Dodaj");
            action.setOnAction(actionEvent -> {
                anInterface.onAdd(
                        datePicker.getValue(),
                        (LocalTime) action.getProperties().get("_time"));
            });
        }

        for (BiHashMap.Entry<Integer, Integer, Label> entry : labels.entrySet()) {
            entry.getValue().setText("");
        }

    }

    Interface anInterface;

    public interface Interface {
        void onAdd(LocalDate date, LocalTime time);

        void onEdit(Examination examination);
    }
}
