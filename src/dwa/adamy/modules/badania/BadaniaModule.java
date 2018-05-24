package dwa.adamy.modules.badania;

import dwa.adamy.Loader;
import dwa.adamy.controll.examination.ExaminationEditor;
import dwa.adamy.controll.examination.ExaminationList;
import dwa.adamy.database.Database;
import dwa.adamy.database.Examination;
import dwa.adamy.lib.DateUtils;
import dwa.adamy.modules.Module;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

import java.util.Date;

public class BadaniaModule extends Module {

    public enum State {
        LIST,
        NEWEXAMINATION,
        EDITEXAMINATION
    }

    @FXML
    private GridPane contentGrid;


    public BadaniaModule() {
        Loader.loadFX(this);

        setState(State.LIST);
    }

    public void setState(State state) {
        setState(state, null);
    }

    public void setState(State state, Object data) {
        getChildren().clear();

        switch (state) {

            case LIST:
                ExaminationList list = new ExaminationList(new ExaminationList.Interface() {
                    @Override
                    public void onAdd(Date time) {
                        Examination e = new Examination();
                        e.setDate(time);
                        setState(State.NEWEXAMINATION, e);
                    }

                    @Override
                    public void onEdit(Examination examination) {
                        setState(State.EDITEXAMINATION, examination);
                    }
                });
                getChildren().add(list);
                break;

            case NEWEXAMINATION:
                ExaminationEditor editor = new ExaminationEditor(new ExaminationEditor.Interface() {
                    @Override
                    public void onSave(Examination examination) {
                        Database.getInstance().addExamination(examination);
                        setState(State.LIST);
                    }

                    @Override
                    public void onCancel() {
                        setState(State.LIST);
                    }
                });
                editor.setExamination((Examination) data);
                getChildren().add(editor);
                break;

            case EDITEXAMINATION:
                break;
        }
    }


}
