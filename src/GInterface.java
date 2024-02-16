import GUtils.SucessLoginCommand;
import Repository.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class GInterface implements UI {

    @Override
    public void start() throws SQLException, IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("G program initieras...");

        while (true) {
            System.out.println("Skriv in din epost: ");
            final String epostInput = scan.nextLine();
            System.out.println("Skriv in ditt lösenord: ");
            final String passwordInput = scan.nextLine();

            Repository repo = new Repository();
            final List<Kunder> kunder = repo.getKunder();
            Optional<Kunder> match = kunder.stream()
                    .filter(k -> k.getEpost().equals(epostInput) && k.getPassword().equals(passwordInput))
                    .findFirst();

            if (match.isPresent()) {
                System.out.println("Inloggning lyckades!");
                new SucessLoginCommand().execute(match);
                return;
            } else {
                System.out.println("Felaktig epost eller lösenord. Försök igen.");
            }
        }
    }
}
