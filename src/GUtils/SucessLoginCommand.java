package GUtils;

import Repository.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class SucessLoginCommand implements Command {
    @Override
    public void execute(Optional<Kunder> match) throws SQLException, IOException {
        Repository r = new Repository();
        int kundId = match.get().getId();
        int bestId = -1;

        Map<Skor, Integer> produktAntal = new HashMap<>();

        while (true) {
            System.out.println("Välj ett alternativ att lägga i kundkorgen genom att skriva siffran: ");
            final List<Skor> skorList = r.getSkor();
            skorList.stream()
                    .filter(skor -> skor.getLagersaldo() != 0)
                    .forEach(skor -> System.out.println((skorList.indexOf(skor) + 1) +
                            ". " +
                            "Namn: " + skor.getNamn() + " | " +
                            "Märke: " + skor.getMärke() + " | " +
                            "Färg: " + skor.getColor() + " | " +
                            "Storlek: " + skor.getStorlek() + " | " +
                            "Lagersaldo: " + skor.getLagersaldo() + " | " +
                            "Pris: " + skor.getPris() + "kr"));
            Scanner scan = new Scanner(System.in);
            String input = scan.nextLine();
            int index = Integer.parseInt(input) - 1;

            if (index >= 0 && index < skorList.size()) {
                Skor selectedSko = skorList.get(index);
                bestId = r.addToCart(kundId, bestId, selectedSko.getId());

                System.out.println("Produkten " + selectedSko.getNamn() + " har lagts till i din beställning med ordernummer: " + bestId);
                produktAntal.put(selectedSko, produktAntal.getOrDefault(selectedSko, 0) + 1);
                while (!input.equals("ja")) {
                    System.out.println("Vill du fortsätta? (ja/nej)");
                    input = scan.nextLine();
                    if (input.equals("nej")) {
                        System.out.println("Här är en sammanfattning av din beställning med ordernummer " + bestId);
                        r.getBeställningSkor(bestId, produktAntal);
                        System.out.println("Tack för att du handlade hos oss!");
                        return;
                    } else if (!input.equals("ja")) {
                        System.out.println("Ogiltig val");
                    }
                }

            }
            else {
                System.out.println("Ogiltig val");
            }
        }
    }
}