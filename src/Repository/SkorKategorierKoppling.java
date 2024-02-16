package Repository;

import java.time.LocalDateTime;

public class SkorKategorierKoppling {
    protected int id;
    protected Skor skoId;
    protected Kategorier kategorierId;
    protected LocalDateTime created;
    protected LocalDateTime lastUpdate;

    public SkorKategorierKoppling(int id, Skor skoId, Kategorier kategorierId, LocalDateTime created, LocalDateTime lastUpdate) {
        this.id = id;
        this.skoId = skoId;
        this.kategorierId = kategorierId;
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
}
