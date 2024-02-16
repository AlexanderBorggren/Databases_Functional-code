package Repository;
public class Kategorier {
    protected int id;
    protected String namn;
    protected String beskrivning;

    public Kategorier(int id, String namn, String beskrivning) {
        this.id = id;
        this.namn = namn;
        this.beskrivning = beskrivning;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamn() {
        return namn;
    }

    public void setNamn(String namn) {
        this.namn = namn;
    }

    public String getBeskrivning() {
        return beskrivning;
    }

    public void setBeskrivning(String beskrivning) {
        this.beskrivning = beskrivning;
    }
}
