package dwa.adamy;

import dwa.adamy.database.Database;
import dwa.adamy.modules.Module;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    //region Singleton

    private static Main ourInstance = null;

    public static Main getInstance() {
        return ourInstance;
    }

    //endregion

    public static void main(String[] args) {
        Database.getInstance().load();

        launch(args);

        Database.getInstance().save();
    }

    private Pane leftPane, rightPane;

    @Override
    public void start(Stage stage) throws Exception {
        ourInstance = this;

        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        leftPane = (Pane) root.lookup("#leftPane");
        rightPane = (Pane) root.lookup("#rightPane");

        stage.setTitle("OSM - Projekt v2");
        stage.setScene(new Scene(root));
        stage.show();
    }

    Module currentModule = null;

    public void openModule(Class moduleClass) {

        try {

            if (currentModule != null) {

                if (currentModule.canClose()) {
                    rightPane.getChildren().clear();
                } else {
                    return;
                }
            }

            currentModule = (Module) moduleClass.newInstance();

            rightPane.getChildren().add(currentModule);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
