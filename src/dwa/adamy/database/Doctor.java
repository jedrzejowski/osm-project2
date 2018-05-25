package dwa.adamy.database;

public class Doctor {
    private String name1, name2, id;

    public Doctor() {
    }

    public Doctor(String name1, String name2, String id) {
        this.name1 = name1;
        this.name2 = name2;
        this.id = id;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return getName1() + " " + getName2();
    }

    @Override
    public String toString() {
        return getFullName();
    }
}
