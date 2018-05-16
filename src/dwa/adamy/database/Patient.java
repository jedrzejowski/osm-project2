package dwa.adamy.database;

import org.json.JSONObject;

import java.util.*;

/**
 * Klasa obiektów reprezentująca pacjenta
 */
public class Patient {


    private String uniqueID = UUID.randomUUID().toString();
    private String name1 = "";
    private String name2 = "";
    private String miasto = "";
    private String kodPocztowy = "";
    private String numerDomu = "";
    private String numerMieszkania = "";
    private String ulica = "";
    private String telefon = "";
    private Pesel pesel = null;
    private Sex sex = Sex.FEMALE;
    private InsuranceType insuranceType = InsuranceType.NONE;

    public Patient() {

    }

    public Patient(JSONObject obj) {
        uniqueID = obj.getString("uniqueID");
        pesel = new Pesel(obj.getString("pesel"));
        name1 = obj.getString("name1");
        name2 = obj.getString("name2");
        miasto = obj.getString("miasto");
        kodPocztowy = obj.getString("kodPocztowy");
        numerDomu = obj.getString("numerDomu");
        numerMieszkania = obj.getString("numerMieszkania");
        ulica = obj.getString("ulica");
        telefon = obj.getString("telefon");
    }

    public JSONObject toJSON() {
        JSONObject obj = new JSONObject();
        obj.put("uniqueID", uniqueID);
        obj.put("pesel", pesel.toString());
        obj.put("name1", name1);
        obj.put("name2", name2);
        obj.put("miasto", miasto);
        obj.put("kodPocztowy", kodPocztowy);
        obj.put("numerDomu", numerDomu);
        obj.put("numerMieszkania", numerMieszkania);
        obj.put("ulica", ulica);
        obj.put("telefon", telefon);
        return obj;
    }


    public String getUniqueID() {
        return uniqueID;
    }

    /**
     * Pobiera imię pacjenta
     *
     * @return imię pacjenta
     */
    public String getName1() {
        return name1;
    }

    /**
     * Ustawia imię pacjenta
     *
     * @param name1 imię pacjenta
     */
    public void setName1(String name1) {
        this.name1 = name1;
    }

    /**
     * Pobiera nazwisko pacjenta
     *
     * @return nazwisko pacjenta
     */
    public String getName2() {
        return name2;
    }

    /**
     * Ustawia nazwisko pacjenta
     *
     * @param name2 nazwisko pacjenta
     */
    public void setName2(String name2) {
        this.name2 = name2;
    }

    /**
     * Pobiera imię i nazwisko pacjenta
     *
     * @return imię i nazwisko pacjenta ze spacją
     */
    public String getFullName() {
        return name1 + " " + name2;
    }

    /**
     * Pobiera pesel pacjenta
     *
     * @return pesel pacjenta
     */
    public Pesel getPesel() {
        return pesel;
    }

    /**
     * Ustawia pesel pacjenta
     *
     * @param pesel pesel pacjenta
     */
    public void setPesel(Pesel pesel) {
        this.pesel = pesel;
    }

    /**
     * Pobiera płeć pacjenta
     *
     * @return płeć pacjenta
     */
    public Sex getSex() {
        return sex;
    }

    /**
     * Ustawia płeć pacjenta
     *
     * @param sex nowa płeć pacjenta
     */
    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getKodPocztowy() {
        return kodPocztowy;
    }

    public void setKodPocztowy(String kodPocztowy) {
        this.kodPocztowy = kodPocztowy;
    }

    public String getNumerDomu() {
        return numerDomu;
    }

    public void setNumerDomu(String numerDomu) {
        this.numerDomu = numerDomu;
    }

    public String getNumerMieszkania() {
        return numerMieszkania;
    }

    public void setNumerMieszkania(String numerMieszkania) {
        this.numerMieszkania = numerMieszkania;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public String getAdress() {
        String out = "";
        out += "ul. " + getUlica() + " " + getNumerDomu();
        if (getNumerMieszkania().length() > 0)
            out += "/" + getNumerMieszkania();
        out += ", " + getKodPocztowy() + " " + getMiasto();
        return out;
    }

    /**
     * Pobiera typ ubezpieczenia
     *
     * @return typ ubezpieczenia
     */
    public InsuranceType getInsuranceType() {
        return insuranceType;
    }

    /**
     * Ustawia typ ubezpieczenia
     *
     * @param insuranceType typ ubezpieczenia
     */
    public void setInsuranceType(InsuranceType insuranceType) {
        this.insuranceType = insuranceType;
    }

    @Override
    public String toString() {
        return String.format("%s, %s %s", getPesel(), getName1(), getName2());
    }

    private static int DebugTestcounter = 0;

    /**
     * Tworzy pacjenta testowego
     *
     * @return pacjent testowy
     */
    public static Patient newTestowy() {
        DebugTestcounter++;

        Patient testowy = new Patient();
        testowy.setName1("Jan" + DebugTestcounter);
        testowy.setName2("Testowy");
        testowy.setSex(Sex.MALE);
        testowy.setPesel(new Pesel("22222222222"));
        return testowy;
    }

    /**
     * Typ wyliczeniowy reprezentujący płeć
     */
    public enum Sex {
        MALE, FEMALE
    }

    private static Map<Sex, String> sexMap = null;

    /**
     * Pobiera mapę płci z nazwami
     *
     * @return mapa płci z nazwami
     */
    public static Map<Sex, String> getSexStringMap() {
        if (sexMap != null) return sexMap;

        sexMap = new HashMap<>();

        sexMap.put(Sex.MALE, "Mężczyzna");
        sexMap.put(Sex.FEMALE, "Kobieta");

        return sexMap;
    }

    /**
     * Typ wyliczeniowy reprezentujący ubezpiecznie
     */
    public enum InsuranceType {
        NFZ, PRIVATE, NONE
    }

    private static Map<InsuranceType, String> insuranceTypeMap = null;

    /**
     * Pobiera mapę ubezpieczeń z nazwami
     *
     * @return mapa ubezpieczeń z nazwami
     */
    public static Map<InsuranceType, String> getInsuranceTypeStringMap() {
        if (insuranceTypeMap != null) return insuranceTypeMap;

        insuranceTypeMap = new HashMap<>();

        insuranceTypeMap.put(InsuranceType.NONE, "Brak");
        insuranceTypeMap.put(InsuranceType.NFZ, "NFZ");
        insuranceTypeMap.put(InsuranceType.PRIVATE, "Prywatne");

        return insuranceTypeMap;
    }

}

