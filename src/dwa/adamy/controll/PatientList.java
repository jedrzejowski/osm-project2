package dwa.adamy.controll;

import dwa.adamy.database.Database;
import dwa.adamy.database.Patient;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class PatientList extends TableView {
    public PatientList() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PatientList.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();

        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        setRowFactory(tv -> {
            TableRow<Patient> row = new TableRow<>();
            row.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (event.getClickCount() == 2 && (!row.isEmpty())) {

                        if (anInterface != null)
                            anInterface.onSelectPatient(row.getItem());
                    }
                }
            });
            return row;
        });
    }

    public void reloadPatients() {
        setItems(FXCollections.observableList(Database.getInstance().getList()));
    }

    //region Interface

    public interface Interface {
        void onSelectPatient(Patient patient);
    }

    Interface anInterface = null;

    public void setInterface(Interface anInterface) {
        this.anInterface = anInterface;
    }

    //endregion

}
