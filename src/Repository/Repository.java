package Repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class Repository {
    private Connection getConnection() throws IOException, SQLException {
        Properties p = new Properties();
        p.load(new FileInputStream("src/Repository/settings.properties"));

        return DriverManager.getConnection(
                p.getProperty("url"),
                p.getProperty("username"),
                p.getProperty("password"));
    }

    public List<Beställningar> getBeställningar() throws IOException, SQLException {
        List<Beställningar> beställningar = new ArrayList<>();
        String query = "SELECT * from Beställningar;";

        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Kunder kund = getKundById(rs.getInt("KundId"));
                Beställningar beställning = new Beställningar(
                        rs.getInt("Id"),
                        kund,
                        rs.getTimestamp("created").toLocalDateTime(),
                        rs.getTimestamp("lastUpdate").toLocalDateTime()
                );
                beställningar.add(beställning);
            }
        }
        return beställningar;
    }


    public List<Skor> getSkor() throws IOException, SQLException {
        List<Skor> skor = new ArrayList<>();
        String query = "SELECT * from Skor WHERE Lagersaldo > 0;";

        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery(query)) {

            while (rs.next()) {
                Skor s = new Skor(
                        rs.getInt("Id"),
                        rs.getString("Namn"),
                        rs.getString("Märke"),
                        rs.getString("Färg"),
                        rs.getInt("Storlek"),
                        rs.getInt("Pris"),
                        rs.getInt("Lagersaldo"),
                        rs.getTimestamp("created").toLocalDateTime(),
                        rs.getTimestamp("lastUpdate").toLocalDateTime());
                skor.add(s);
            }
        }
        return skor;
    }

    public List<Kunder> getKunder() throws IOException, SQLException {
        List<Kunder> kunder = new ArrayList<>();
        String query = "SELECT * from Kunder;";

        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery(query)) {

            while (rs.next()) {
                Adress adress = getAdress(rs.getString("AdressId"));
                Kunder k = new Kunder(
                        rs.getInt("Id"),
                        rs.getString("Namn"),
                        rs.getString("Epost"),
                        rs.getString("Telefonnummer"),
                        adress,
                        rs.getTimestamp("created").toLocalDateTime(),
                        rs.getTimestamp("lastUpdate").toLocalDateTime(),
                        rs.getString("Password")
                );
                kunder.add(k);
            }
        }
        return kunder;
    }

    public Adress getAdress(String adressId) throws IOException, SQLException {
        Adress adress = null;
        String query = "SELECT * from Adress WHERE Id = ?;";

        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setString(1, adressId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    adress = new Adress(
                            rs.getInt("Id"),
                            rs.getString("Gatuadress"),
                            rs.getString("Ort"),
                            rs.getInt("Postnummer"),
                            rs.getString("Län"),
                            rs.getString("Land"),
                            rs.getTimestamp("created").toLocalDateTime(),
                            rs.getTimestamp("lastUpdate").toLocalDateTime()
                    );
                }
            }
        }
        return adress;
    }

    public List<BeställningsKoppling> getBeställningsKopplingar() throws IOException, SQLException {
        List<BeställningsKoppling> beställningsKopplingar = new ArrayList<>();
        String query = "SELECT * FROM BeställningsKoppling;";

        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery(query)) {

            while (rs.next()) {
                Beställningar beställningar = getBeställningById(rs.getInt("BeställningsId"));
                Skor skor = getSkoById(rs.getInt("SkoId"));
                BeställningsKoppling bK = new BeställningsKoppling(
                        rs.getInt("Id"), beställningar, skor,
                        rs.getTimestamp("created").toLocalDateTime(),
                        rs.getTimestamp("lastUpdate").toLocalDateTime()
                );
                beställningsKopplingar.add(bK);

            }
        }
        return beställningsKopplingar;
    }


    public Kunder getKundById(int kundId) throws IOException, SQLException {
        Kunder kund = null;
        String query = "SELECT * from Kunder WHERE Id = ?;";

        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, kundId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Adress adress = getAdress(rs.getString("AdressId"));
                    kund = new Kunder(
                            rs.getInt("Id"),
                            rs.getString("Namn"),
                            rs.getString("Epost"),
                            rs.getString("Telefonnummer"),
                            adress,
                            rs.getTimestamp("created").toLocalDateTime(),
                            rs.getTimestamp("lastUpdate").toLocalDateTime(),
                            rs.getString("Password")
                    );
                }
            }
        }
        return kund;
    }

    public Beställningar getBeställningById(int beställningId) throws IOException, SQLException {
        Beställningar beställning = null;
        String query = "SELECT * from Beställningar WHERE Id = ?;";

        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, beställningId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Kunder kund = getKundById(rs.getInt("KundId"));
                    beställning = new Beställningar(
                            rs.getInt("Id"),
                            kund,
                            rs.getTimestamp("created").toLocalDateTime(),
                            rs.getTimestamp("lastUpdate").toLocalDateTime()
                    );
                }
            }
        }
        return beställning;
    }

    public Skor getSkoById(int skoId) throws IOException, SQLException {
        Skor sko = null;
        String query = "SELECT * from Skor WHERE Id = ?;";

        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, skoId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    sko = new Skor(
                            rs.getInt("Id"),
                            rs.getString("Namn"),
                            rs.getString("Märke"),
                            rs.getString("Färg"),
                            rs.getInt("Storlek"),
                            rs.getInt("Pris"),
                            rs.getInt("Lagersaldo"),
                            rs.getTimestamp("created").toLocalDateTime(),
                            rs.getTimestamp("lastUpdate").toLocalDateTime()
                    );
                }
            }
        }
        return sko;
    }

    public List<BeställningsKoppling> getBeställningsKopplingLastOrder() throws IOException, SQLException {
        List<BeställningsKoppling> beställningsKoppling = new ArrayList<>();
        //Hämtar det senaste beställningsId
        String query = "SELECT * FROM BeställningsKoppling WHERE BeställningsId = (SELECT MAX(BeställningsId) FROM BeställningsKoppling);";

        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery(query)) {

            while (rs.next()) {
                Beställningar beställningar = getBeställningById(rs.getInt("BeställningsId"));
                Skor skor = getSkoById(rs.getInt("SkoId"));
                BeställningsKoppling bK = new BeställningsKoppling(
                        rs.getInt("Id"), beställningar, skor,
                        rs.getTimestamp("created").toLocalDateTime(),
                        rs.getTimestamp("lastUpdate").toLocalDateTime()
                );
                beställningsKoppling.add(bK);
            }
        }
        return beställningsKoppling;
    }


    public int addToCart(int kundId, int beställningsId, int skoId) {
        String sql = "{CALL AddToCart(?, ?, ?, ?, ?, ?)}";
        int bestId = 0;
        try (Connection con = getConnection();
             CallableStatement stmt = con.prepareCall(sql)) {
            // IN
            stmt.setInt(1, kundId);
            stmt.setInt(2, beställningsId);
            stmt.setInt(3, skoId);
            // OUT
            stmt.registerOutParameter(4, Types.BOOLEAN);
            stmt.registerOutParameter(5, Types.INTEGER);
            stmt.registerOutParameter(6, Types.INTEGER);

            stmt.execute();

            // Get OUT
            boolean orderSuccess = stmt.getBoolean(4);
            bestId = stmt.getInt(5);
            int selectedShoeId = stmt.getInt(6);

        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
        return bestId;
    }

    public List<Skor> getBeställningSkor(int bestId, Map<Skor, Integer> produktAntal) throws IOException, SQLException {
        final List<Skor> skorList = getSkor();
        final List<BeställningsKoppling> beställningsKopplingList = getBeställningsKopplingLastOrder();

        // Filtrerar skorList att inkludera skor med matchande värden i beställningsKoppling, lagar i ny List
        List<Skor> beställningSkor = skorList.stream()
                .filter(skor -> beställningsKopplingList.stream()
                        .anyMatch(bk -> bk.getBestId().getId() == bestId && bk.getSkoId().getId() == skor.getId()))
                .collect(Collectors.toList());

        produktAntal.forEach((sko, antal) -> System.out.println(antal + "x " +
                "Namn: " + sko.getNamn() + " | " +
                "Märke: " + sko.getMärke() + " | " +
                "Färg: " + sko.getColor() + " | " +
                "Storlek: " + sko.getStorlek() + " | " +
                "Pris: " + sko.getPris() + "kr"));

        //För varje antal skor, hämta priset per sko och summera till en int totalPris
        int totalPris = produktAntal.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPris() * entry.getValue())// Konverterar till int
                .sum();
        System.out.println("Totalpriset för beställningen är: " + totalPris + "kr");

        return beställningSkor;
    }

}
