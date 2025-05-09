import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class DataHandler {

    //String zum Tempordner
    public static String tempDir = System.getProperty("java.io.tmpdir");

    //Methode um sich die Daten für Games und User aus dem Temp-Ordner in eine ArrayList innerhalb des Programms zu kopieren
    public static ArrayList<String[]> getDataFromTemp(String Dateiname)
    {
        try {
            //Array in dem die Spiele gespeichert werden
            ArrayList<String[]> data = new ArrayList<>();
            //ArrayList<Game> gameData = new ArrayList<>();
            //ArrayList<User> userData = new ArrayList<>();

            //Bestimme den Pfad zum Temp Ordner, und zur Daten.csv Datei
            File file = new File(tempDir, "Speicherplatzrechner\\" + Dateiname);
            File directory = new File(tempDir, "Speicherplatzrechner");

            //Überprüfe ob der Ordner existier in dem die Dateien liegen sollen
            if (!directory.exists()) {
                //Falls nicht dann erstelle ihn
                directory.mkdirs();

                //Erstellt er grade zum ersten mal das directory für die games?
                if (Dateiname.equals("Games.csv")) {
                    //Dann gib ein Array zurück was schon einige Werte hat
                    return Main.PrintTestDaten();
                }

                //Kannst auch gleich nen leeres Array returnen, da es die Daten 100%ig auch nich gibt
                return new ArrayList<String[]>();
            }


            //Überprüfe ob die Datei in Temp existiert
            if (file.exists()) {
                //Erstelle einen Scanner der Zeilenweiße die Datei ließt
                Scanner sc = new Scanner(file);

                //Solange er noch lesen kann...
                while (sc.hasNextLine()) {
                    //...Lese eine Zeile und Splitte sie direkt anhand des Semikolons
                    String[] lineparts = sc.nextLine().split(";");
                    
                    //Will er Games hinzufügen?
                    if (Dateiname.contains("Games")) {
                        //Müssen mindestens 5 Teile sein, sonst Indexfehler bei leerer Zeile
                        if (lineparts.length >= 5) {
                            // Füge für jede Zeile einen Eintrag in die ArrayList hinzu
                            data.add(new String[] {lineparts[0], lineparts[1], lineparts[2], lineparts[3], lineparts[4], lineparts[5]});
                            //gameData.add(new Game(lineparts[1], lineparts[2], lineparts[3], lineparts[4], lineparts[5],"-1"));
                        }
                    } 
                    //Dann will er Users hinzufügen
                    else{       
                        //Müssen mindestens 3 Teile sein, sonst Indexfehler bei leerer Zeile
                        if (lineparts.length >= 3) {
                            // Füge auch für jede Zeile einen Eintrag hinzu, nur weniger Spalten
                            data.add(new String[] {lineparts[0], lineparts[1], lineparts[2]});
                            //userData.add(new User(lineparts[1], lineparts[2], Integer.parseInt(lineparts[0])));
                        }
                    }
                }

                //Scanner beenden
                sc.close();

                //Wenn er alle Daten ausgelesen hat, gib das Array zurück
                return data;
            }
            else {
                //Wenn es sie nicht gibt, dann erstelle die Datei
                file.createNewFile();

                //Erstellt du die Datei für den User grade?
                if (Dateiname.equals("Users.csv")) {
                    //Erzeuge einen Writer um in die Datei der User zu schreiben
                    FileWriter userWriter = new FileWriter(file);
                
                    //Lege einen admin/admin an und zerstöre den writer
                    userWriter.write("1;admin;admin\n");
                    userWriter.write("1;sysadmin;sysadmin");
                    userWriter.close();

                    //Gib ein Array zurück wo schon admin und sysadmin drinnen sind
                    return new ArrayList<String[]>() {{
                        add(new String[] {"1", "admin", "admin"});
                        add(new String[] {"2", "sysadmin", "sysadmin"});
                    }};
                }

                //Da es eh keine Daten gibt, kannst du auch das leere Array zurückgeben
                return new ArrayList<String[]>();
            }
        } catch (Exception e) {
            //Gib Fehlermeldung aus
            JOptionPane.showMessageDialog(null, e.getMessage(), Dateiname, 0);
        
            //Leeres Array zurückgeben
            return new ArrayList<String[]>();
        }
    }

    //Methode um beim beenden des Programms die Daten für Games und Users zurück in den entsprechenden Dateien zu speichern
    public static void SetDataToTemp(ArrayList<String[]> games, ArrayList<String[]> users) {
        //Erstelle die File Objekte für Games und Users
        File fileGames = new File(tempDir, "Speicherplatzrechner\\" + "Games.csv");
        File fileUsers = new File(tempDir, "Speicherplatzrechner\\" + "Users.csv");
        
        try {
            //Erzeuge einen Writer um in die Datei der Games zu schreiben
            FileWriter gameWriter = new FileWriter(fileGames);

            //Gehe jede Zeile im Array Games durch
            for (int i = 0; i < games.size(); i++) {
                //Bilde den String für eine Zeile
                String line = games.get(i)[0] + ";" + games.get(i)[1] + ";" + games.get(i)[2] + ";" + games.get(i)[3] + ";" + games.get(i)[4] + ";" + games.get(i)[5] + "\n";
            
                gameWriter.write(line);
            }

            //Wenn das Array zu Ende ist, dann zerstöre den Writer
            gameWriter.close();
        } catch (Exception e) {
            //Gib Meldung aus das Daten für Spiele nicht gespeichert werden konnten
            JOptionPane.showConfirmDialog(null, "Daten für die Spiele konnten nicht gespeichert werden. \nBitte versuche es erneut\n\n\n" + e.getMessage() , 
            "Achtung", JOptionPane.OK_OPTION,  JOptionPane.ERROR_MESSAGE);
        }

        try {
            //Erzeuge einen Writer um in die Datei der USer zu schreiben
            FileWriter userWriter = new FileWriter(fileUsers);

            //Gehe jede Zeile im Array Users durch
            for (int i = 0; i < users.size(); i++) {
                //Bilde den String für eine Zeile
                String line = users.get(i)[0] + ";" + users.get(i)[1] + ";" + users.get(i)[2] + "\n";
            
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
}
