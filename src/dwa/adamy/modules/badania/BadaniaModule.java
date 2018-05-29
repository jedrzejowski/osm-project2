package dwa.adamy.modules.badania;

import dwa.adamy.Loader;
import dwa.adamy.controll.examination.ExaminationEditor;
import dwa.adamy.controll.examination.ExaminationList;
import dwa.adamy.database.Database;
import dwa.adamy.database.Examination;
import dwa.adamy.modules.Module;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

import java.time.LocalDate;
import java.time.LocalTime;

public class BadaniaModule extends Module {

    public enum State {
        LIST,
        NEW,
        EDIT
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

            case LIST: {
                LocalDate date = LocalDate.now();
                if (data != null) date = (LocalDate) data;

                ExaminationList list = new ExaminationList(new ExaminationList.Interface() {
                    @Override
                    public void onAdd(LocalDate date, LocalTime time) {
                        Examination e = new Examination();
                        e.setDate(date);
                        e.setTime(time);
                        setState(State.NEW, e);
                    }

                    @Override
                    public void onEdit(Examination examination) {
                        setState(State.EDIT, examination);
                    }
                }, date);
                getChildren().clear();
                getChildren().add(list);
                break;
            }

            case NEW: {
                LocalDate date = ((Examination) data).getDate();

                ExaminationEditor editor = new ExaminationEditor(new ExaminationEditor.Interface() {
                    @Override
                    public void onSave(Examination examination) {
                        Database.getInstance().addExamination(examination);
                        setState(State.LIST, examination.getDate());
                    }

                    @Override
                    public void onCancel() {
                        setState(State.LIST, date);
                    }
                });

                editor.setExamination((Examination) data);
                getChildren().clear();
                getChildren().add(editor);
                break;
            }

            case EDIT: {
                LocalDate date = ((Examination) data).getDate();

                ExaminationEditor editor = new ExaminationEditor(new ExaminationEditor.Interface() {
                    @Override
                    public void onSave(Examination examination) {
                        setState(State.LIST, date);
                    }

                    @Override
                    public void onCancel() {
                        setState(State.LIST, date);
                    }
                });
                editor.setExamination((Examination) data);
                getChildren().clear();
                getChildren().add(editor);
                break;
            }
        }
    }


}
