package Repository;

import java.time.LocalDateTime;

public class Beställningar {
    protected int id;
    protected Kunder kundId;
    protected LocalDateTime created;
    protected LocalDateTime lastUpdate;

    public Beställningar(int id, Kunder kundId, LocalDateTime created, LocalDateTime lastUpdate) {
        this.id = id;
        this.kundId = kundId;
        this.created = created;
        this.lastUpdate = lastUpdate;
    }

    @Override
    //Kollar om två beställningar är lika eller har samma id
    public boolean equals(Object o) {
        if (this == o) return true; //Om "this" objektet har samma minnesadress som "o" objektet, returneras true.
        if (o == null || getClass() != o.getClass()) return false; //Om "o" objektet är null, eller om klassen för "this" inte är samma som "o", returneras false
        Beställningar best = (Beställningar) o; //typomvandla "o" till "Beställningar"
        return id == best.id; //Om objekten har samma värde för "id" returneras true, annars false.
    }

    public Kunder getKundId() {
        return kundId;
    }

    public void setKundId(Kunder kundId) {
        this.kundId = kundId;
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
