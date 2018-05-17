package dwa.adamy.database;

import javafx.beans.property.StringProperty;
import org.json.JSONObject;

public class ExaminationType {
    private StringProperty name, id, code;

    public ExaminationType(JSONObject obj) {
        setCode(obj.getString("code"));
        setId(obj.getString("id"));
        setName(obj.getString("name"));
    }

    public JSONObject toJSON() {
        JSONObject obj = new JSONObject();
        obj.put("name", getName());
        obj.put("id", getId());
        obj.put("code", getCode());
        return obj;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getCode() {
        return code.get();
    }

    public StringProperty codeProperty() {
        return code;
    }

    public void setCode(String code) {
        this.code.set(code);
    }
}
