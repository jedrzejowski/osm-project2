package dwa.adamy.modules.hospitalizacje;

import dwa.adamy.Loader;
import dwa.adamy.controll.hospitalization.HospitalizationEditor;
import dwa.adamy.controll.hospitalization.HospitalizationList;
import dwa.adamy.database.Database;
import dwa.adamy.database.Hospitalization;
import dwa.adamy.database.HospitalizationUnit;
import dwa.adamy.modules.Module;
import javafx.scene.Node;

import java.time.LocalDate;
import java.time.LocalTime;

public class HospitalizacjeModule extends Module {

    public enum State {
        LIST,
        NEW,
        EDIT
    }

    public HospitalizacjeModule() {
        Loader.loadFX(this);
        setState(State.LIST);
    }

    private void setContent(Node n) {
        getChildren().clear();
        getChildren().add(n);
    }

    public void setState(State state) {
        setState(state, null);
    }

    public void setState(State state, Object data) {
        getChildren().clear();

        switch (state) {

            case LIST: {

                HospitalizationList list = new HospitalizationList(
                        (LocalDate) data,
                        new HospitalizationList.Interface() {
                            @Override
                            public void onNew(HospitalizationUnit hUint, LocalDate date, LocalTime time) {
                                Hospitalization h = new Hospitalization();
                                h.setUnit(hUint);
                                h.setFromDate(date);
                                h.setFromTime(time);
                                setState(State.NEW, h);
                            }

                            @Override
                            public void onEdit(Hospitalization h) {
                                setState(State.EDIT, h);
                            }
                        });

                setContent(list);

                break;
            }

            case NEW: {

                HospitalizationEditor editor = new HospitalizationEditor(
                        (Hospitalization) data,
                        new HospitalizationEditor.Interface() {
                            @Override
                            public void onSave(Hospitalization hospitalization) throws Exception{
                                Database.getInstance().addHospitalization(hospitalization);
                                setState(State.LIST, hospitalization.getFromDate());
                            }

                            @Override
                            public void onCancel() {
                                setState(State.LIST);
                            }
                        }
                );
                setContent(editor);

                break;
            }

            case EDIT: {

                HospitalizationEditor editor = new HospitalizationEditor(
                        (Hospitalization) data,
                        new HospitalizationEditor.Interface() {
                            @Override
                            public void onSave(Hospitalization hospitalization){
                                setState(State.LIST, hospitalization.getFromDate());
                            }

                            @Override
                            public void onCancel() {
                                setState(State.LIST);
                            }
                        }
                );
                setContent(editor);

                break;
            }
        }
    }

}
