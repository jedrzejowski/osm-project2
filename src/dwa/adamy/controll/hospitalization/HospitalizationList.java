package dwa.adamy.controll.hospitalization;

import dwa.adamy.Loader;
import dwa.adamy.database.Database;
import dwa.adamy.database.Hospitalization;
import dwa.adamy.database.HospitalizationUnit;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.time.LocalDate;
import java.time.LocalTime;

public class HospitalizationList extends BorderPane {

    @FXML
    private HBox content;
    @FXML
    private DatePicker datePicker;

    public HospitalizationList( LocalDate date, Interface anInterface) {
        Loader.loadFX(this);
        this.anInterface = anInterface;

        if (date == null) date = LocalDate.now();

        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            setDate(newValue);
        });

        datePicker.setValue(date);
    }

    public HospitalizationList(Interface anInterface) {
        this(null, anInterface);
    }

    public void setDate(LocalDate date) {

        content.getChildren().clear();

        for (HospitalizationUnit h : Database.getInstance().getHospitalizationUnitList()) {

            HospitalizationUnitViewer viewer = new HospitalizationUnitViewer(
                    h, date,
                    new HospitalizationUnitViewer.Interface() {
                        @Override
                        public void onNew(LocalDate date, LocalTime time) {
                            anInterface.onNew(h, date, time);
                        }

                        @Override
                        public void onEdit(Hospitalization h) {
                            anInterface.onEdit(h);
                        }
                    }
            );

            content.getChildren().add(viewer);
        }
    }

    private Interface anInterface;

    public interface Interface {
        void onNew(HospitalizationUnit hUint, LocalDate date, LocalTime time);

        void onEdit(Hospitalization h);
    }
}
