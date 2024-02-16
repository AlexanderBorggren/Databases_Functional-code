import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class StartUI {
    private Scanner scan = new Scanner(System.in);
    private UIFactory factory = new UIFactory();

    public void start() {
        while (true) {
            System.out.println("G eller VG program? Skriv Exit för att avsluta");
            String input = scan.nextLine().trim().toUpperCase();

            if (input.equals("EXIT")) {
                System.out.println("Avslutar");
                break;
            }
            try {
                UI ui = factory.createInterface(input);
                ui.start();
            } catch (IllegalArgumentException e) {
                System.out.println("Ogiltig inmatning");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
