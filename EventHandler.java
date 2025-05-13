import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class EventHandler {

    //Array für die Daten der Spiele und Users
    public static String UserID = "-1";
    public static ArrayList<Game> games;
    public static ArrayList<User> users;

    //Instanzen für GameManager und
    GameManager gamemanager;
    public static LoginHandler login;

    //Konstruktor der Eventhandler klasse
    public EventHandler() {

        //Hole dir die Daten für Games/ Users

        users = DataHandler.getUsersFromTemp();
        games = DataHandler.getGamesFromTemp();

        //Instanzen für GameManager und GameFilter initialisieren
        gamemanager = new GameManager();
        //Instanz für das LoginFenster initialisieren
        login = new LoginHandler(users);
    }
    //Methode überprüft ob die eingegebenen Werte für das registrieren eines Users stimmen, wenn ja dann Anlegen. Ansonsten Fehlermeldung
    public static void RegisterUser() {
        String username = RegisterWindow.benutzerNamTextField.getText();
        String PW1 = RegisterWindow.txtPassword1.getText();
        String PW2 = RegisterWindow.txtPassword2.getText();
       
        //Hat er Benutzername eingegeben
        if (username.length() < 1) {
            JOptionPane.showMessageDialog(null, "Sie müssen einen Benutzernamen eingeben um einen Benutzer zu registrieren!", "Achtung!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //Hat er 2 Passwörter eingeben?
        else if (PW1.length() < 1 || PW2.length() < 1) {
            JOptionPane.showMessageDialog(null, "Sie müssen in beide Passwortfelder etwas eingeben um einen Benutzer hinzuzufügen!", "Achtung!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //Stimmen die überein?
        else if (! PW1.equals(PW2)) {
            JOptionPane.showMessageDialog(null, "Beide Passwörter müssen übereinstimmen, um einen Benutzer hinzuzufügen!", "Achtung!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //Alles top
        else {
            //User anlegen
            login.AddUser(username, PW1);

            //Ausgabe das es geklappt hat
            JOptionPane.showMessageDialog(null, "Der Benutzer '" + username + "'' wurde erfolgreich angelegt. \nSie können sich nun anmelden", "Benutzer angelegt", JOptionPane.INFORMATION_MESSAGE);

            //Registrieren fenster schließen
            RegisterWindow.frame.dispose();

            //Einloggen FEnser öffnen
            LoginWindow.starteProgramm();
            Main.setDarkmode(Main.isDarkmode);
    
            //Einfach so kann man nicht ändern/löschen oder eine Exe-Datei öffnen, deswegen erstmal ausgrauen und enablen
            MainWindow.EnableButtons();
        }
    }

    //Methode überprüft die EIngaben des Benutzers und logt ihn ggf. ein
    public static void LoginUser() {
        String username = LoginWindow.benutzerNamTextField.getText();
        String passwort = LoginWindow.passwTextField.getText();

        //Hat er überhaupt Username eingegeben?
        if (username.length() < 1) {
            JOptionPane.showMessageDialog(null, "Sie müssen einen Benutzernamen eingeben um sich einzuloggen!", "Achtung!", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //Existiert der User?
        if (login.IsUserRegistered(username)) {
            //Ist das Passwort richtig?
            if (login.IsPasswordCorrect(username, passwort)) {
                //Logge den User ein, und merke dir die UserID
                UserID = login.LogUserIn(username, passwort);

                //Schließe LoginFenster
                LoginWindow.frame.dispose();
                
                //Öffne das MainWindow
                MainWindow.starteProgramm();
                Main.setDarkmode(Main.isDarkmode);

                //Printe den Namen ins Label
                MainWindow.labBenutzer.setText(login.GetUsernameFromID(UserID));

                //Genauso wie wenn er keinen DS in Tabelle angeklickt hat
                MainWindow.EnableButtons();
            } else {
                //Fehlermeldung das Passwort falsch ist
                JOptionPane.showMessageDialog(null, "Das eingegebene Passwort ist inkorrekt. \nBitte versuchen sie es erneut!", "Falsches Passwort!", JOptionPane.ERROR_MESSAGE);
            }
        }
        else {
            //Erstelle eine Messagebox die fragt ob er registriert werden soll
            int antwort = JOptionPane.showConfirmDialog(
                null, 
                "Ein Benutzer mit dem Namen '" + username + "' existiert nicht! \nWollen sie sich jetzt registrieren?", 
                "Registrierung erforderlich!", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.QUESTION_MESSAGE
            );

            //Er will registrieren
            if (antwort == JOptionPane.YES_OPTION) {
                //Schließe das Login WIndow
                LoginWindow.frame.dispose();

                //Öffne das Fenster zum Loggin
                RegisterWindow.starteProgramm();
                Main.setDarkmode(Main.isDarkmode);
            }
        }
    }

    //Methode fragt User ob er speichern will, und logt ihn dann aus
    public void LogUserOut() {
        //Frage ob er speichern will
        if (SaveData() <= 0) {
            //Schließe das Fenster hier
            MainWindow.frame.dispose();

            //Wenn ja, dann öffne login Fenster
            LoginWindow.starteProgramm();
            Main.setDarkmode(Main.isDarkmode);

            //Textboxen bei login leeren
            LoginWindow.benutzerNamTextField.setText("");
            LoginWindow.passwTextField.setText("");
        }

        //Er will nicht speichern, mache nichts
    }

    //Methode um einen FBD zu öffnen, in dem man ein Spiel zum hinzufügen auswählen kann
    public void OffneOrdner() 
    {  
        //Wähle Ordner aus und printe Name/Speicherplatz in die Textboxen
        gamemanager.SelectFolder();
    }

    //Methode zum hinzufügen eines Spiels
    public void Hinzufugen() { 
        //Überprüfe ob die eingegebenen Werte richtig sind
        if (gamemanager.AreValuesRight()) {
            //Füge das Spiel dem Array und in der Tabelle hinzu
            gamemanager.AddGame();

            //Mach das Ding frei fürs nächste Spiel
            Neu();

            //Bilde die nun veränderte Sammlung von Spielen neu ab
            Main.RefreshTabelle();
        }

        //Falls nicht, dann hat er die MessageBox mit Fehler schon ausgegeben
    }

    //Methode zum Löschen eines Spiels
    public void Loschen() 
    {  
        //Überprüfe ob löschen okey ist
        if (gamemanager.IsOkay("zu löschen")) {
            //Wenn ja dann lösche das Game
            gamemanager.DeleteGame();

            //Bilde die nun veränderte Sammlung von Spielen neu ab
            Main.RefreshTabelle();
        }

        //Löschen nicht okay, Fehlermeldung kennt er schon
        
        //Mache so oder so Eingabefelder leer
        Neu();
    }
    
    //Methode zum Ändern eines Spiels
    public void Andern() 
    {  
        //Überprüfe ob er ändern darf und ob die Werte mit denen er ändern will korrekt sind
        if (gamemanager.IsOkay("ab zu ändern") && gamemanager.AreValuesRight()) {
            gamemanager.ChangeGame();

            //Bilde die nun veränderte Sammlung von Spielen neu ab
            Main.RefreshTabelle();
        }

        //Löschen nicht okay, Fehlermeldung kennt er schon
        
        //Mache so oder so Eingabefelder leer
        Neu();
    }
    
    //Methode um die Eingabefelder für ein Spiel zu leeren
    public void Neu() {
        MainWindow.titelTextbox.setText("");
        MainWindow.kategoriebox.setSelectedItem("");
        MainWindow.speicherPlatzTextBox.setText("");
        MainWindow.pfadTextBox.setText("");

        //Wähle auch in der Tabelle nichts mehr aus            
        MainWindow.tabelle.clearSelection();
    }
    
    //Methode um einem Spiel ienen Pfad zur Exe-Datei hinzuzufügen
    public void HinzufugenPfad() 
    {
        //Schaue ob es schon eine Pfad gibt
        if (MainWindow.pfadTextBox.getText() == "") {
            int option = JOptionPane.showOptionDialog(null, "Sie haben für dieses Spiel schon einen Pfad hinzugefügt, wollen sie einen anderne hinzufügen?", "Warnung", 
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        
            //Nur wenn er nicht ändern will abbrechen
            if (option == JOptionPane.NO_OPTION) {
                return;
            }
        }

        //Er will hinzufügen

        //Dann speichere den ausgewählten Pfad im Textfeld
        MainWindow.pfadTextBox.setText(gamemanager.SelectExefile());

        //Zeige als Tooltip den ausgewählten pfad an
        MainWindow.pfadTextBox.setToolTipText(MainWindow.pfadTextBox.getText());
    }

    //Methode um die Exe Datei zu einem Spiel zu öffnen
    public void OpenExeFile() {
        //Überprüfe ob sie eine hat und zu öffnen, ansonsten Fehlermeldung
        gamemanager.OpenExe();

        //Egal ob geöffnet oder nicht, mache alles leer
        Neu();
    }

    //Methode um das Datagrid nach den gegebenen Sachen zu filtern
    public void FilterAnwenden() 
    {  
        //Zuerstmal falls was ausgewählt wurde, nichts mehr auswählen und Textboxen leeren
        MainWindow.tabelle.clearSelection();
        Neu();

        String Kategorie = MainWindow.kategorieFilterBox.getSelectedItem().toString();

        //Checke ob er nach Kategorie filter will
        if (Kategorie.equals("Alle")) {
            //Nein, dann bilde alle ab
            Main.RefreshTabelle();
        }            
        //Wenn nicht dann bilde nur ab die diese Kategorie dazugehörig sind
        else{
            
            //Tabelle leeren
            MainWindow.model.setRowCount(0);
            
            //Gehe jedes angelegte Spiel durch
            for (Game game : games) {
                if (game.getKategorie().equals(Kategorie) &&   //Gehört es zu dieser kategorie?
                        game.getId().equals(UserID)         //Und zu diesem User?
                ) {
                    //Ja, dann füg es zur Tabelle hinzu
                    MainWindow.eintragHinzufuegen(game.getId(), game.getName(), Kategorie, game.getGroesse());
                }
            }

        }

        //Möchte er nach irgendwas sortieren?
        if (MainWindow.kategorienList.getSelectedIndex() != -1) {
            //Ja, dann merke dir nach was er sorieren will
            String Sortierung = MainWindow.kategorienList.getSelectedValue();

            //Einen sortierer für diese Tabelle erstellen
            TableRowSorter<TableModel> sorter = new TableRowSorter<>(MainWindow.tabelle.getModel());
            
            //Der Sortierer muss wissen das in der 3. Spalte double stehen und keine string
            sorter.setComparator(3, (Double o1, Double o2) -> o1.compareTo(o2));

            //Sortierer zur Tabelle hinzufügen
            MainWindow.tabelle.setRowSorter(sorter);

            //Liste erstellen nach was man sortieren will
            List<RowSorter.SortKey> sortKeys = new ArrayList<>();

            //Ja, dann schaue nach was und sortiere dementsprechen
            switch (Sortierung) {
                case "Name aufsteigend":
                    sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING)); //Nach der 1. Spalte aufsteigend (0 ist die ID)
                    break;
                case "Name absteigend":
                    sortKeys.add(new RowSorter.SortKey(1, SortOrder.DESCENDING));
                    break;
                case "Kategorie aufsteigend":
                    sortKeys.add(new RowSorter.SortKey(2, SortOrder.ASCENDING)); //Nach der 2. Spalte aufsteigend (0 ist die ID)
                    break;
                case "Kategorie absteigend":
                    sortKeys.add(new RowSorter.SortKey(2, SortOrder.DESCENDING));
                    break;
                case "Speicherplatz aufsteigend":
                    sortKeys.add(new RowSorter.SortKey(3, SortOrder.ASCENDING)); //Nach der 3. Spalte aufsteigend (0 ist die ID)
                    break;
                case "Speicherplatz absteigend":
                    sortKeys.add(new RowSorter.SortKey(3, SortOrder.DESCENDING));
                    break;
                default:
                    //Is wies is, Bro will nichts sortieren
                    break;
            }

            //Und sortieren
            sorter.setSortKeys(sortKeys);
            sorter.sort();
        }
    }

    //Methode wird aufgerufen um Daten zu speichern, anhand des RÜckgabewertes kann man sehen was passieren soll
    //0 = Beenden, AUsloggen        1 = Einfach so weiter
    public Integer SaveData() {
        //User fragen ob er speichern will
        int option = JOptionPane.showConfirmDialog(MainWindow.frame,
            "Möchten Sie die Änderungen speichern?",
            "Änderungen speichern",
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE);

        //Benutzer klickt auf Ja und will speichern & schließen
        if (option == JOptionPane.YES_OPTION) {
            DataHandler.SetGamesToTemp(games);
            DataHandler.SetUserToTemp(users);
            DataHandler.SetModeToTemp(Main.isDarkmode);
            return 0;
        }
        //Benutzer will einfach so schließen ohne zu speichern
        else if (option == JOptionPane.NO_OPTION) {
            return 0;
        }
        //Benutzer klickt das Fenster weg, dann gar nichts
        else {
            MainWindow.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Schließen verhindern
            return 1;
        }
    }

    //Methode wird aufgerufen um die JTable, Combo und Listbox zu zurückzusetzen wie am Anfang
    public void ResetTable() {
        //Wähle in der Combo wieder "Alle aus"
        MainWindow.kategorieFilterBox.setSelectedItem("Alle");

        //Wähle in der Listbox nichts aus
        MainWindow.kategorienList.clearSelection();

        //Leere auch die Textboxen oben
        Neu();

        //Alle Games die es gibt wieder in die JTable
        Main.RefreshTabelle();
    }
}
