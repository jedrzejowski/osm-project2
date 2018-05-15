package dwa.adamy;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.URL;

public class Loader {
    public static void loadFX(Parent t) {
        Class c = t.getClass();
        String simple = c.getSimpleName();

        FXMLLoader fxmlLoader = new FXMLLoader(c.getResource(simple+".fxml"));
        fxmlLoader.setRoot(t);
        fxmlLoader.setController(t);

        URL url = c.getResource(simple + ".css");
        if (url != null) t.getStylesheets().add(url.toExternalForm());

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
