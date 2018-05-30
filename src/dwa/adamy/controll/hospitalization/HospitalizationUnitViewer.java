package dwa.adamy.controll.hospitalization;

import dwa.adamy.Loader;
import dwa.adamy.database.Database;
import dwa.adamy.database.Hospitalization;
import dwa.adamy.database.HospitalizationUnit;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class HospitalizationUnitViewer extends VBox {
    @FXML
    private GridPane gridPane;
    @FXML
    private Label label1, label2;

    private HospitalizationUnit hUnit;
    private LocalDate date;

    public HospitalizationUnitViewer(HospitalizationUnit hUnit, LocalDate date, Interface anInterface) {
        Loader.loadFX(this);

        this.hUnit = hUnit;
        label1.setText(hUnit.getName());

        this.date = date;
        label2.setText(date.format(DateTimeFormatter.ofPattern("ccc, d LLL YYYY")).toLowerCase());

        this.anInterface = anInterface;

        initGrid();

        for (Hospitalization h : Database.getInstance().getHospitalizationListFromDate(date, hUnit)) {
            insertHospitalization(h);
        }
    }

    private List<Label> timeLabelList = new ArrayList<>();
    private List<BorderPane> contentList = new ArrayList<>();
    private List<Button> actionList = new ArrayList<>();


    private void initGrid() {

        boolean odd = false;
        BorderPane pane;

        for (int h = 0, i = 1; h < 24; h++) {
            for (int m = 0; m < 60; m += 30, i++) {
                gridPane.getRowConstraints().add(new RowConstraints());
                final LocalTime time = LocalTime.of(h, m, 0, 0);

                {
                    pane = new BorderPane();
                    pane.getStyleClass().add(odd ? "odd" : "even");

                    Label timeLabel = new Label();
                    timeLabel.getStyleClass().add("timeLabel");
                    timeLabel.setText(String.format("%02d:%02d", h, m));

                    timeLabelList.add(timeLabel);

                    pane.setCenter(timeLabel);
                    gridPane.add(pane, 0, i);
                }

                {
                    pane = new BorderPane();
                    pane.getStyleClass().add(odd ? "odd" : "even");
                    pane.getStyleClass().add("contentPane");

                    contentList.add(pane);
                    gridPane.add(pane, 1, i);
                }

                {
                    pane = new BorderPane();
                    pane.getStyleClass().add(odd ? "odd" : "even");

                    Button action = new Button();
                    action.setText("Dodaj");
                    action.setOnAction(actionEvent -> {
                        anInterface.onNew(date, time);
                    });
                    actionList.add(action);

                    pane.setCenter(action);
                    gridPane.add(pane, 2, i);

                }
                odd = !odd;
            }
        }
    }

    private void insertHospitalization(Hospitalization h) {

        int indexStart = h.getFromDate().isBefore(date) ? 1 :
                (1 + h.getFromTime().getHour() * 2 + (h.getFromTime().getMinute() < 30 ? 0 : 1));

        int indexEnd = h.getToDate().isAfter(date) ? gridPane.getRowConstraints().size() - 1 :
                (h.getToTime().getHour() * 2 + (h.getToTime().getMinute() == 0 ? 0 : (h.getToTime().getMinute() < 30 ? 1 : 2)));

        int span = indexEnd - indexStart + 1;

        Label timeLabel = timeLabelList.get(indexStart - 1);
        BorderPane borderPane = contentList.get(indexStart - 1);
        Button action = actionList.get(indexStart - 1);

        timeLabel.getParent().getStyleClass().add("busy");

        HospitalizationDetails details = new HospitalizationDetails(h);
        details.getStyleClass().add(span % 2 == 1 ? "odd" : "even");
        borderPane.setCenter(details);
        GridPane.setRowSpan(borderPane, span);
        borderPane.getStyleClass().add("busy");

        action.setText("Edytuj");
        action.setOnAction(actionEvent -> {
            anInterface.onEdit(h);
        });
        GridPane.setRowSpan(action.getParent(), span);
        action.getParent().getStyleClass().add("busy");

        for (int i = indexStart; i < indexEnd; i++) {
            timeLabelList.get(i).getParent().getStyleClass().add("busy");
            contentList.get(i).setVisible(false);
            actionList.get(i).getParent().setVisible(false);
        }

    }

    private Interface anInterface;

    public interface Interface {
        void onNew(LocalDate date, LocalTime time);

        void onEdit(Hospitalization h);
    }
}
