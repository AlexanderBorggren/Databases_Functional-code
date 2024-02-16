package Repository;

import java.time.LocalDateTime;

public class BeställningsKoppling {
    protected int id;
    protected Beställningar bestId;
    protected Skor skoId;
    protected LocalDateTime created;
    protected LocalDateTime lastUpdate;

    public BeställningsKoppling(int id, Beställningar beställningar, Skor skor, LocalDateTime created, LocalDateTime lastUpdate) {
        this.id = id;
        this.bestId = beställningar;
        this.skoId = skor;
        this.created = created;
        this.lastUpdate = lastUpdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Beställningar getBestId() {
        return this.bestId;
    }

    public Skor getSkoId() {
        return this.skoId;
    }
}
