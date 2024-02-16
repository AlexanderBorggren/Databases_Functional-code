package Repository;

import java.time.LocalDateTime;

public class Adress {

    protected int id;
    protected String adress;
    protected String ort;
    protected int postnummer;
    protected String lan;
    protected String land;
    protected LocalDateTime created;
    protected LocalDateTime lastUpdate;

    public Adress(int id, String adress, String ort, int postnummer, String lan, String land, LocalDateTime created, LocalDateTime lastUpdate) {
        this.id = id;
        this.adress = adress;
        this.ort = ort;
        this.postnummer = postnummer;
        this.lan = lan;
        this.land = land;
        this.created = created;
        this.lastUpdate = lastUpdate;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public int getPostnummer() {
        return postnummer;
    }

    public void setPostnummer(int postnummer) {
        this.postnummer = postnummer;
    }

    public String getLan() {
        return lan;
    }

    public void setLan(String lan) {
        this.lan = lan;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
