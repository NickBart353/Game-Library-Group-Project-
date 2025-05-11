import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class DataHandler {

    //String zum Tempordner
    private static final String tempDir = System.getProperty("java.io.tmpdir");
    private static final String gameName = "Games.csv";
    private static final String userName = "Users.csv";

    //Methode um sich die Daten für Games und User aus dem Temp-Ordner in eine ArrayList innerhalb des Programms zu kopieren
    public static ArrayList<User> getUsersFromTemp()
    {
        try {
            //Array in dem die Spiele gespeichert werden
            ArrayList<User> userData = new ArrayList<>();

            //Bestimme den Pfad zum Temp Ordner, und zur Daten.csv Datei
            File file = new File(tempDir, "Speicherplatzrechner\\" + userName);
            File directory = new File(tempDir, "Speicherplatzrechner");

            //Überprüfe ob der Ordner existier in dem die Dateien liegen sollen
            if (!directory.exists()) {
                //Falls nicht dann erstelle ihn
                directory.mkdirs();

                //Kannst auch gleich nen leeres Array returnen, da es die Daten 100%ig auch nich gibt
                return new ArrayList<>();
            }

            //Überprüfe ob die Datei in Temp existiert
            if (file.exists()) {
                //Erstelle einen Scanner der Zeilenweiße die Datei ließt
                Scanner sc = new Scanner(file);

                //Solange er noch lesen kann...
                while (sc.hasNextLine()) {
                    //...Lese eine Zeile und Splitte sie direkt anhand des Semikolons
                    String[] lineparts = sc.nextLine().split(";");

                    //Müssen mindestens 3 Teile sein, sonst Indexfehler bei leerer Zeile
                    if (lineparts.length >= 3) {
                        // Füge auch für jede Zeile einen Eintrag hinzu, nur weniger Spalten
                        userData.add(new User(lineparts[1], lineparts[2], lineparts[0]));
                    }
                }

                //Scanner beenden
                sc.close();

                //Wenn er alle Daten ausgelesen hat, gib das Array zurück
                return userData;
            }
            else {
                //Wenn es sie nicht gibt, dann erstelle die Datei
                file.createNewFile();

                //Erzeuge einen Writer um in die Datei der User zu schreiben
                FileWriter userWriter = new FileWriter(file);

                //Lege einen admin/admin an und zerstöre den writer
                userWriter.write("0;admin;admin\n");
                userWriter.write("1;sysadmin;sysadmin");
                userWriter.close();

                //Gib ein Array zurück wo schon admin und sysadmin drinnen sind
                return new ArrayList<>() {{
                    add(new User("admin", "admin", "0"));
                    add(new User("sysadmin", "sysadmin", "1"));
                }};
            }
        } catch (Exception e) {
            //Gib Fehlermeldung aus
            JOptionPane.showMessageDialog(null, e.getMessage(), userName, 0);
        
            //Leeres Array zurückgeben
            return new ArrayList<>();
        }
    }

    public static ArrayList<Game> getGamesFromTemp()
    {
        try {
            //Array in dem die Spiele gespeichert werden
            ArrayList<Game> gameData = new ArrayList<>();

            //Bestimme den Pfad zum Temp Ordner, und zur Daten.csv Datei
            File file = new File(tempDir, "Speicherplatzrechner\\" + gameName);
            File directory = new File(tempDir, "Speicherplatzrechner");

            //Überprüfe ob der Ordner existier in dem die Dateien liegen sollen
            if (!directory.exists()) {
                //Falls nicht dann erstelle ihn
                directory.mkdirs();

/*                if (gameName.equals("Games.csv")) {
                    //Dann gib ein Array zurück was schon einige Werte hat
                    return Main.PrintTestDaten();
                }*/

                //Kannst auch gleich nen leeres Array returnen, da es die Daten 100%ig auch nich gibt
                return new ArrayList<>();
            }


            //Überprüfe ob die Datei in Temp existiert
            if (file.exists()) {
                //Erstelle einen Scanner der Zeilenweiße die Datei ließt
                Scanner sc = new Scanner(file);

                //Solange er noch lesen kann...
                while (sc.hasNextLine()) {
                    //...Lese eine Zeile und Splitte sie direkt anhand des Semikolons
                    String[] lineparts = sc.nextLine().split(";");

                    //Müssen mindestens 5 Teile sein, sonst Indexfehler bei leerer Zeile
                    if (lineparts.length >= 5) {
                        // Füge für jede Zeile einen Eintrag in die ArrayList hinzu
                        gameData.add(new Game(lineparts[1], lineparts[3], lineparts[2], lineparts[4], lineparts[5],lineparts[0]));
                    }
                }

                //Scanner beenden
                sc.close();

                //Wenn er alle Daten ausgelesen hat, gib das Array zurück
                return gameData;
            }
            else {
                //Wenn es sie nicht gibt, dann erstelle die Datei
                file.createNewFile();

                //Da es eh keine Daten gibt, kannst du auch das leere Array zurückgeben
                return new ArrayList<>();
            }
        } catch (Exception e) {
            //Gib Fehlermeldung aus
            JOptionPane.showMessageDialog(null, e.getMessage(), gameName, 0);

            //Leeres Array zurückgeben
            return new ArrayList<>();
        }
    }

    public static void SetUserToTemp(ArrayList<User> benutzer) {
        //Erstelle die File Objekte für Games und Users
        File fileUsers = new File(tempDir, "Speicherplatzrechner\\" + "Users.csv");

        try {
            //Erzeuge einen Writer um in die Datei der USer zu schreiben
            FileWriter userWriter = new FileWriter(fileUsers);

            //Gehe jede Zeile im Array Users durch
            for (User user: benutzer) {
                //Bilde den String für eine Zeile
                String line = user.getId() + ";" + user.getName() + ";" + user.getPassword() + "\n";

                userWriter.write(line);
            }

            //Wenn das Array zu Ende ist, dann zerstöre den Writer
            userWriter.close();
        } catch (Exception e) {
            //Gib Meldung aus das Daten für User nicht gespeichert werden konnten
            JOptionPane.showMessageDialog(
                    null,
                    "Daten für die User konnten nicht gespeichert werden. \nBitte versuche es erneut\n\n\n" + e.getMessage(),
                    "Achtung",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public static void SetGamesToTemp(ArrayList<Game> games) {
        //Erstelle die File Objekte für Games und Users
        File fileGames = new File(tempDir, "Speicherplatzrechner\\" + "Games.csv");

        try {
            //Erzeuge einen Writer um in die Datei der Games zu schreiben
            FileWriter gameWriter = new FileWriter(fileGames);

            //Gehe jede Zeile im Array Games durch
            for (Game game: games) {
                //Bilde den String für eine Zeile
                String line = game.getId() + ";" + game.getName() + ";" + game.getKategorie() + ";" + game.getGroesse() + ";" + game.getExePath()+ ";" + game.getUser() + "\n";

                gameWriter.write(line);
            }

            //Wenn das Array zu Ende ist, dann zerstöre den Writer
            gameWriter.close();
        } catch (Exception e) {
            //Gib Meldung aus das Daten für Spiele nicht gespeichert werden konnten
            JOptionPane.showConfirmDialog(null, "Daten für die Spiele konnten nicht gespeichert werden. \nBitte versuche es erneut\n\n\n" + e.getMessage() ,
                    "Achtung", JOptionPane.OK_OPTION,  JOptionPane.ERROR_MESSAGE);
        }
    }

}
