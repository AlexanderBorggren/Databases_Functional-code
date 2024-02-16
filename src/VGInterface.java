import Repository.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class VGInterface implements UI {

    @Override
    public void start() throws SQLException, IOException {
        final List <String> valList = List.of("2. Lista alla kunder och hur många ordrar varje kund har lagt.",
                "3. Lista alla kunder och hur mycket pengar varje kund sammanlagt har beställt för.",
                "4. Lista beställningsvärde per ort.", "5. Avsluta");
        System.out.println("VG program initieras...");
        while (true) {
            System.out.println("Välj vilken rapport du vill läsa: ");
            valList.forEach(val -> System.out.println(val));

            Scanner scan = new Scanner(System.in);
            String input = scan.nextLine();

            if (input.equals("2")) {
                VGCommand2();
            } else if (input.equals("3")) {
                VGCommand3();
            } else if (input.equals("4")) {
                VGCommand4();
            } else if (input.equals("5")) {
                return;
            }
            else {
                System.out.println("Ogiltig val");
            }
        }
    }

    public void VGCommand2() throws SQLException, IOException {
        Repository repo = new Repository();
        final List<Beställningar> beställningar = repo.getBeställningar();
        // List -> Stream -> Map -> Grupperar streamen efter Beställningars kundId & räknar elementen, lagrar i map
        Map<Kunder, Long> kundOrderCount = beställningar.stream()
                .collect(Collectors.groupingBy(Beställningar::getKundId, Collectors.counting()));
        // Itererar igenom map och printar ut kundens namn och antal ordrar
        kundOrderCount.forEach((kund, count) -> System.out.println("Kund: " + kund.getNamn() + ", Antal ordrar: " + count));
    }
    public void VGCommand3() throws SQLException, IOException {
        Repository repo = new Repository();
        final List<Beställningar> beställningar = repo.getBeställningar();
        final List<BeställningsKoppling> beställningsKopplingar = repo.getBeställningsKopplingar();

        Map<Kunder, Integer> kundTotalPris = beställningar.stream()
                .collect(Collectors.toMap(
                        beställning -> beställning.getKundId(), // lagrar kundId till key value i map
                        //Högre ordningens funktion
                        beställning -> calculateTotalPris(beställning, beställningsKopplingar), //lagrar totalpris till value i map
                        Integer::sum // summerar samtliga värden med samma key
                ));

        kundTotalPris.forEach((kund, totalPris) -> System.out.println("Kund: " + kund.getNamn() + ", Totalt beställt för: " + totalPris + " kr"));
    }

    public void VGCommand4() throws SQLException, IOException {
        Repository repo = new Repository();
        final List<Beställningar> beställningar = repo.getBeställningar();
        final List<BeställningsKoppling> beställningsKopplingar = repo.getBeställningsKopplingar();

        Map<String, Integer> ortTotalPris = beställningar.stream()
                .collect(Collectors.toMap(
                        beställning -> beställning.getKundId().getAdressId().getOrt(), // Key
                        beställning -> calculateTotalPris(beställning, beställningsKopplingar), // Value
                        Integer::sum
                ));

        ortTotalPris.forEach((ort, totalPris) -> System.out.println("Ort: " + ort + ", Totalt beställt för: " + totalPris + " kr"));
    }

    public int calculateTotalPris(Beställningar beställning, List<BeställningsKoppling> beställningsKopplingar) {
        return beställningsKopplingar.stream()
                .filter(koppling -> koppling.getBestId().equals(beställning)) // Filtrerar kopplingar som tillhör den här beställningen
                .mapToInt(koppling -> koppling.getSkoId().getPris()) // Mappar varje koppling till priset av den tillhörande skon
                .sum(); // Summerar alla priser
    }

}