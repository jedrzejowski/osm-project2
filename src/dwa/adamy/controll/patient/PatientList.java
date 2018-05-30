package dwa.adamy.controll.patient;

import dwa.adamy.Loader;
import dwa.adamy.database.Database;
import dwa.adamy.database.Patient;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public class PatientList extends BorderPane {
    @FXML
    private TableView tableView;

    public PatientList() {
        Loader.loadFX(this);

        tableView.setRowFactory(tv -> {
            TableRow<Patient> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {

                    if (anInterface != null)
                        anInterface.onSelectPatient(row.getItem());
                }
            });
            return row;
        });
    }

    public void reloadPatients() {
        tableView.setItems(FXCollections.observableList(Database.getInstance().getPatientList()));
    }


    @FXML
    private void newPatient(ActionEvent event) {
        if (anInterface != null)
            anInterface.onNewPatient();
    }

    //region Interface

    public interface Interface {
        void onSelectPatient(Patient patient);

        void onNewPatient();
    }

    Interface anInterface = null;

    public void setInterface(Interface anInterface) {
        this.anInterface = anInterface;
    }

    //endregion

}
