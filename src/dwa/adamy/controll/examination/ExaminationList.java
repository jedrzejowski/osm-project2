package dwa.adamy.controll.examination;

import dwa.adamy.Loader;
import dwa.adamy.database.Database;
import dwa.adamy.database.Examination;
import dwa.adamy.lib.DateUtils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import java.util.*;

public class ExaminationList extends BorderPane {

    @FXML
    private GridPane contentGrid;
    @FXML
    private DatePicker datePicker;

    private List<Button> actions = new ArrayList<>();
    private List<Label> labels = new ArrayList<>();

    public ExaminationList(Interface anInterface, LocalDate date) {
        Loader.loadFX(this);
        this.anInterface = anInterface;

        int lastI = contentGrid.getColumnConstraints().size() - 1;

        for (int h = 8, i = 1; h < 18; h++) {
            for (int m = 0; m < 60; m += 15, i++) {
                contentGrid.getRowConstraints().add(new RowConstraints());
                final LocalTime time = LocalTime.of(h, m, 0, 0);

                Label timeLabel = new Label();
                timeLabel.setText(String.format("%02d:%02d", h, m));
                contentGrid.add(timeLabel, 0, i);

                Button action = new Button();
                action.getProperties().put("_time", time);
                actions.add(action);
                contentGrid.add(action, lastI, i);
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

    public void showExamination(Examination exam) {
        int rowI = (exam.getTime().getHour() - 8) * 4 + exam.getTime().getMinute() / 15 + 1;

        Label label = new Label(exam.getName());
        contentGrid.add(label, 1, rowI);
        labels.add(label);

        label = new Label(exam.getRange());
        contentGrid.add(label, 2, rowI);
        labels.add(label);

        label = new Label(exam.getResult());
        contentGrid.add(label, 3, rowI);
        labels.add(label);

        label = new Label(exam.getDoctor());
        contentGrid.add(label, 4, rowI);
        labels.add(label);

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

        for (Label label : labels)
            contentGrid.getChildren().remove(label);

        labels.clear();
    }

    Interface anInterface;

    public interface Interface {
        void onAdd(LocalDate date, LocalTime time);

        void onEdit(Examination examination);
    }
}
