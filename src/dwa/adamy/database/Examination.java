package dwa.adamy.database;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

/**
 * Klasa obiektów reprezentujących dane badania krwi
 * <p>
 * Dlaczego wartości parametrów są string'ami a nie int'ami?
 * Otóż dokumentacja medyczna ma być tworzona prze lekarzy, lub im podobnych. Aplikacja nie powinna ograniczać
 * możliwości tworzenia i edycji danych medycznych nie będących danymi osobowymi. To lekarz decyduje co wpisać w rubrykę
 * i to lekarz będzie te dane interpretował, a nie aplikacja
 */
public class Examination {

    private LocalDate date;
    private LocalTime time;
    private String result = "";
    private String name = "";
    private String range = "";
    private String doctor = "";

    public Examination() {
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
