package dwa.adamy.controll.examination;

import dwa.adamy.Loader;
import dwa.adamy.database.Examination;
import dwa.adamy.ui.prop.DateProp;
import dwa.adamy.ui.prop.TextProp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class ExaminationEditor extends VBox {
    @FXML
    DateProp dateProp;
    @FXML
    TextProp nameProp, rangeProp, doctorProp, resultProp;

    private Examination examination;

    public ExaminationEditor(Interface anInterface) {
        Loader.loadFX(this);
        this.anInterface = anInterface;
    }

    public Examination getExamination() {

        examination.setDate(dateProp.getValue());
        examination.setName(nameProp.getValue().toString());
        examination.setRange(rangeProp.getValue().toString());
        examination.setDoctor(doctorProp.getValue().toString());
        examination.setResult(resultProp.getValue().toString());

        return examination;
    }

    public void setExamination(Examination examination) {
        this.examination = examination;

        dateProp.setValue(examination.getDate());
        nameProp.setValue(examination.getName());
        rangeProp.setValue(examination.getRange());
        doctorProp.setValue(examination.getDoctor());
        resultProp.setValue(examination.getResult());
    }

    @FXML
    private void saveAction(ActionEvent event) {
        if (anInterface != null)
            anInterface.onSave(getExamination());
    }

    @FXML
    private void cancelAction(ActionEvent event) {

        if (anInterface != null)
            anInterface.onCancel();
    }

    Interface anInterface;
    public interface Interface{
        void onSave(Examination examination);
        void onCancel();
    }
}
