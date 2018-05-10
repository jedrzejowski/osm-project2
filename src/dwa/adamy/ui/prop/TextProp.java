package dwa.adamy.ui.prop;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class TextProp extends HBox {

    Label label;
    TextField textField;

    public TextProp() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TextProp.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();

            label = (Label) lookup("#label");
            textField = (TextField) lookup("#textField");
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    //region Props

    public String getLabel() {
        return label.getText();
    }

    public void setLabel(String value) {
        label.setText(value);
    }

    public String getText() {
        return textField.getText();
    }

    public void setText(String value) {
        textField.setText(value);
    }

    //endRegion
}
