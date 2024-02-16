package Repository;

import java.time.LocalDateTime;
import java.util.Objects;

public class Kunder {
    protected int id;
    protected String namn;
    protected String epost;
    protected String telefonnummer;
    protected Adress adressId;
    protected LocalDateTime created;
    protected LocalDateTime lastUpdate;
    protected String password;

    public Kunder(int id, String namn, String epost, String telefonnummer, Adress adressId, LocalDateTime created, LocalDateTime lastUpdate, String password) {
        this.id = id;
        this.namn = namn;
        this.epost = epost;
        this.telefonnummer = telefonnummer;
        this.adressId = adressId;
        this.created = created;
        this.lastUpdate = lastUpdate;
        this.password = password;
    }

    @Override
    //Kollar om två objekt har samma id, endast då är de likadana
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kunder kund = (Kunder) o;
        return id == kund.id;
    }

    @Override
    // Genererar unik hashkod för unikt id
    public int hashCode() {
        return Objects.hash(id);
    }

    public Adress getAdressId() {
        return adressId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getEpost() {
        return epost;
    }

    public void setEpost(String epost) {
        this.epost = epost;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
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
