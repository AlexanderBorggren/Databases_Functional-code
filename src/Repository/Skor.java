package Repository;

import java.time.LocalDateTime;
import java.util.*;

public class Skor {
    protected int id;
    protected String namn;
    protected String märke;
    protected String color;
    protected int storlek;
    protected int pris;
    protected int lagersaldo;
    protected LocalDateTime created;
    protected LocalDateTime lastUpdate;

    public Skor(int id, String namn, String märke, String color, int storlek, int pris, int lagersaldo, LocalDateTime created, LocalDateTime lastUpdate) {
        this.id = id;
        this.namn = namn;
        this.märke = märke;
        this.color = color;
        this.storlek = storlek;
        this.pris = pris;
        this.lagersaldo = lagersaldo;
        this.created = created;
        this.lastUpdate = lastUpdate;
    }

    //Jämför om två olika Skor är lika, dvs. har samma minnesplats
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Skor skor = (Skor) obj;
        return getId() == skor.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    public String getMärke() {
        return märke;
    }

    public void setMärke(String märke) {
        this.märke = märke;
    }

    public int getStorlek() {
        return storlek;
    }

    public void setStorlek(int storlek) {
        this.storlek = storlek;
    }

    public int getPris() {
        return pris;
    }

    public void setPris(int pris) {
        this.pris = pris;
    }

    public int getLagersaldo() {
        return lagersaldo;
    }

    public void setLagersaldo(int lagersaldo) {
        this.lagersaldo = lagersaldo;
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
