package dwa.adamy.ui;

import javafx.scene.control.Alert;

public class Dialog {
    public static void showPatientError() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Błąd");
        alert.setHeaderText(null);
        alert.setContentText("Błąd w danych pacjenta");

        alert.showAndWait();
    }

    public static void showHospitalizationError() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Błąd");
        alert.setHeaderText(null);
        alert.setContentText("Błędny zakres czasowy hospitalizacji");

        alert.showAndWait();
    }
}
