package dwa.adamy;

import com.sun.org.apache.xpath.internal.operations.Mod;
import dwa.adamy.modules.Module;
import dwa.adamy.ui.ModuleChooser;
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

        launch(args);

    }

    Pane leftPane, rightPane;

    @Override
    public void start(Stage stage) throws Exception {
        ourInstance = this;

        Parent root = FXMLLoader.load(getClass().getResource("ui/MainWindow.fxml"));
        leftPane = (Pane) root.lookup("#leftPane");
        rightPane = (Pane) root.lookup("#rightPane");

        stage.setTitle("OSM - Projekt v2");
        stage.setScene(new Scene(root, 300, 275));
        stage.show();
    }

    Module currentModule = null;

    public void openModule(Class moduleClass) {

        try {

            if (currentModule != null) {

                if (currentModule.canClose()) {
                    rightPane.getChildren().removeAll();
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
