package dwa.adamy.controll;

import dwa.adamy.Loader;
import dwa.adamy.database.Database;
import dwa.adamy.database.Patient;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.List;

public class PatientFinder extends HBox {
    @FXML
    private ComboBox<Patient> combo;
    @FXML
    private Button button;

    private ObservableList<Patient> list = FXCollections.observableList(new ArrayList<>());

    public PatientFinder() {
        Loader.loadFX(this);

        combo.setCellFactory(new Callback<ListView<Patient>, ListCell<Patient>>() {
            @Override
            public ListCell<Patient> call(ListView<Patient> p) {

                return new ListCell<Patient>() {

                    @Override
                    protected void updateItem(Patient item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            setText(item.toString());
                        }
                    }
                };
            }
        });

        combo.getEditor().textProperty().addListener((observableValue, oldValue, newValue) -> {
            list.clear();

            if (newValue.length() <= 2) return;

            list.addAll(Database.getInstance().findPatientsBySelector(newValue));
        });

        combo.setItems(list);
    }
}
