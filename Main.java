import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Main {

    // Instanz für die EventHandler Klasse
    private static EventHandler logik;
    
    // Merken, ob Darkmode an/aus ist
    public static boolean isDarkmode = true;

    public static void main(String[] args) {
        // Initialisiere Instanz
        logik = new EventHandler();
        
        // Rufe zuerst das Login Fenster auf und initialisiere auch die Buttons auf ihm        
        LoginWindow.starteProgramm();
    }

    // Methode verknüpft die Buttons aus dem MainWindow mit den Events aus EventHandler.java
    public static void InitializeMainWindow() {
        // Methode um darauf zu reagieren, wenn in der Tabelle unten etwas ausgewählt wurde
        // Überprüfen, ob der ListSelectionListener bereits hinzugefügt wurde
        ListSelectionModel selectionModel = MainWindow.tabelle.getSelectionModel();

        // Sicherstellen, dass der Listener nur einmal hinzugefügt wird
        // (wir speichern den Listener, um ihn nur einmal hinzuzufügen)
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Verhindere das, das Event doppelt aufgerufen wird
                if (!e.getValueIsAdjusting()) {
                    // Rufe Methode auf, um Werte in die Textboxen zu schreiben
                    GameManager.PrintValuesInTextboxes();
                }
            }
        });
    
        // Methode um den Benutzer zu fragen, ob er vorm Schließen speichern will
        if (MainWindow.frame.getWindowListeners().length == 0) {
            MainWindow.frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    if (logik.SaveData() <= 0) {
                        MainWindow.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Beenden erlauben
                    }
                }
            });
        }
        
        // Buttons mit den Click-Events versehen
        InitializeButtonsMain();
    
        // Vorgefertigte Werte in die Comboboxen printen
        InitializeComboBoxes();
    
        // Testdaten in Array/Tabelle drucken
        RefreshTabelle();   // Zeige sie auch an
    }

    //Methode printet vorgefertigte Kategorien in die Comboboxen
    private static void InitializeComboBoxes() {
        //Vorgefertige Kategorien in die ComboBoxen
        MainWindow.kategorieFilterBox.addItem("Alle");
        MainWindow.kategorieFilterBox.addItem("Shooter");
        MainWindow.kategorieFilterBox.addItem("Simulation");
        MainWindow.kategorieFilterBox.addItem("Strategie");
        MainWindow.kategorieFilterBox.addItem("Horror");
        
        MainWindow.kategoriebox.addItem("Shooter");
        MainWindow.kategoriebox.addItem("Simulation");
        MainWindow.kategoriebox.addItem("Strategie");
        MainWindow.kategoriebox.addItem("Horror");

        //In der oberen nichts auswählen
        MainWindow.kategoriebox.setSelectedIndex(-1);

        //In der zum filtern standartmäßig "Alle" auswählen
        MainWindow.kategorieFilterBox.setSelectedIndex(0);
    }

    // Methode, um die Buttons im MainWindow zu initialisieren
    private static void InitializeButtonsMain() {
        // Events auf die Buttons hinzufügen, sicher nur einmal ausgeführt
        if (MainWindow.btn_Hinzufügen.getActionListeners().length == 0) {
            MainWindow.btn_Hinzufügen.addActionListener(e -> logik.Hinzufugen());
        }
        if (MainWindow.btn_löschen.getActionListeners().length == 0) {
            MainWindow.btn_löschen.addActionListener(e -> logik.Loschen());
        }
        if (MainWindow.btn_HinzufügenPfad.getActionListeners().length == 0) {
            MainWindow.btn_HinzufügenPfad.addActionListener(e -> logik.HinzufugenPfad());
        }
        if (MainWindow.btn_ExeÖfnnen.getActionListeners().length == 0) {
            MainWindow.btn_ExeÖfnnen.addActionListener(e -> logik.OpenExeFile());
        }
        if (MainWindow.btn_ändern.getActionListeners().length == 0) {
            MainWindow.btn_ändern.addActionListener(e -> logik.Andern());
        }
        if (MainWindow.btn_Neu.getActionListeners().length == 0) {
            MainWindow.btn_Neu.addActionListener(e -> logik.Neu());
        }
        if (MainWindow.btn_ÖffneOrdner.getActionListeners().length == 0) {
            MainWindow.btn_ÖffneOrdner.addActionListener(e -> logik.OffneOrdner());
        }
        if (MainWindow.btn_FilterAnwenden.getActionListeners().length == 0) {
            MainWindow.btn_FilterAnwenden.addActionListener(e -> logik.FilterAnwenden());
        }
        if (MainWindow.toggleSwitch.getActionListeners().length == 0) {
            MainWindow.toggleSwitch.addActionListener(e -> setDarkmode(MainWindow.toggleSwitch.isSelected()));
        }
        if (MainWindow.btn_Logout.getActionListeners().length == 0) {
            MainWindow.btn_Logout.addActionListener(e -> logik.LogUserOut());
        }
        if (MainWindow.btn_Refresh.getActionListeners().length == 0) {
            MainWindow.btn_Refresh.addActionListener(e -> logik.ResetTable());
        }
    }

    // Methode, um die Buttons im LoginWindow zu initialisieren
    public static void InitializeButtonsLogin() {
        if (LoginWindow.btn_Registrieren.getActionListeners().length == 0) {
            LoginWindow.btn_Registrieren.addActionListener(e -> {
                // Login schließen
                LoginWindow.frame.dispose();

                // Registrierfenster öffnen und in Darkmode evtl. setzen
                RegisterWindow.starteProgramm();
                setDarkmode(isDarkmode);
            });
        }
        
        if (LoginWindow.btn_Login.getActionListeners().length == 0) {
            LoginWindow.btn_Login.addActionListener(e -> EventHandler.LoginUser());
        }

        if (LoginWindow.toggleSwitchLogin.getActionListeners().length == 0) {
            LoginWindow.toggleSwitchLogin.addActionListener(e -> setDarkmode(LoginWindow.toggleSwitchLogin.isSelected()));
        }
    }

    // Methode, um die Buttons im RegisterWindow zu initialisieren
    public static void InitializeButtonsRegister() {
        if (RegisterWindow.toggleSwitchLogin.getActionListeners().length == 0) {
            RegisterWindow.toggleSwitchLogin.addActionListener(e -> setDarkmode(RegisterWindow.toggleSwitchLogin.isSelected()));
        }

        if (RegisterWindow.btn_Login.getActionListeners().length == 0) {
            RegisterWindow.btn_Login.addActionListener(e -> EventHandler.RegisterUser());
        }

        if (RegisterWindow.btn_Abbrechen.getActionListeners().length == 0) {
            RegisterWindow.btn_Abbrechen.addActionListener(e -> {
                // Schließe Register Window
                RegisterWindow.frame.dispose();

                // Öffne Login Window
                LoginWindow.starteProgramm();
                Main.setDarkmode(Main.isDarkmode);
            });
        }
    }

    // Methode setzt die GUI auf Dark- bzw. Whitemode
    public static void setDarkmode(boolean dark) {
        isDarkmode = dark;
    
        if (isDarkmode) {
            // Fenster & Style umstellen
            
            
            if (MainWindow.frame.isActive()) {
                DarkmodeHandler.setMainDarkmode();
                MainWindow.toggleSwitch.setSelected(true);
                MainWindow.toggleSwitch.setText("🌞");

                //Ist grade etwas in der Tabelle ausgewählt?
                if (MainWindow.tabelle.getSelectedRow() == -1) {
                    //Nein, dann sind nur hinzufügen und Pfad hinzufügen benutzbar
                    MainWindow.EnableButtons();
                }
                else{
                    //Wenn etwas augewählt ist, kann man nur ändern, löschen oder Exe-Öffnen
                    MainWindow.DisableButtons();
                }
            }

            if (LoginWindow.frame.isActive()) {    
                DarkmodeHandler.setLoginDarkmode();
                LoginWindow.toggleSwitchLogin.setSelected(true);
                LoginWindow.toggleSwitchLogin.setText("🌞");
            }

            if (RegisterWindow.frame.isActive()) {            
                RegisterWindow.toggleSwitchLogin.setSelected(true);
                RegisterWindow.toggleSwitchLogin.setText("🌞");
                DarkmodeHandler.setRegisterDarkmode();
            }
        } 
        else {
            if (MainWindow.frame.isActive()) {
                DarkmodeHandler.setMainLightmode();
                MainWindow.toggleSwitch.setSelected(false);
                MainWindow.toggleSwitch.setText("🌙");
        
                                //Ist grade etwas in der Tabelle ausgewählt?
                if (MainWindow.tabelle.getSelectedRow() == -1) {
                    //Nein, dann sind nur hinzufügen und Pfad hinzufügen benutzbar
                    MainWindow.EnableButtons();
                }
                else{
                    //Wenn etwas augewählt ist, kann man nur ändern, löschen oder Exe-Öffnen
                    MainWindow.DisableButtons();
                }
            }

            if (LoginWindow.frame.isActive()) {    
                DarkmodeHandler.setLoginLightmode();
                LoginWindow.toggleSwitchLogin.setSelected(false);
                LoginWindow.toggleSwitchLogin.setText("🌙");
            }

            if (RegisterWindow.frame.isActive()) {            
                RegisterWindow.toggleSwitchLogin.setSelected(false);
                RegisterWindow.toggleSwitchLogin.setText("🌙");
                DarkmodeHandler.setRegisterLightmode();
            }
        }
    }
    
    // Methode fügt mehrere Test-Datensätze in das Array der Spiele
    public static ArrayList<Game> PrintTestDaten() {
        // Erstelle ein ArrayList mit den Testdaten
        ArrayList<Game> games = new ArrayList<>() {{
            add(new Game ("Call of Duty", "Shooter", "60", "","0","0" ));
            add(new Game ("League of Legends", "Strategie", "40", "","0", "1" ));
            add(new Game ("Clash of Clans", "Strategie", "3", "", "0", "2"));
            add(new Game ("Phasmophobia", "Horror", "8", "", "0", "3"));
            add(new Game ("Euro Truck Simulator 4", "Simulation", "10", "", "0", "4"));
            // Auch für Sysadmin schon welche
            add(new Game ("Call of Duty_sysadmin", "Strategie", "10", "", "1", "1"));
            add(new Game ("Minecraft_sysadmin", "Simulation", "20", "", "1", "2"));
        }};
        return games;
    }

    // Methode aktualisiert die Tabelle, je nach dem welche Spiele grade in der ArrayList<String[]> games sind
    public static void RefreshTabelle() {
        // Alle Einträge aus Tabelle löschen
        MainWindow.model.setRowCount(0);

        for (Game game : EventHandler.games) {
            // Gehört das Spiel zum angemeldeten User?
            if (game.getUser().equals(EventHandler.UserID)) {
                // Füge das Spiel zur Tabelle hinzu
                MainWindow.eintragHinzufuegen(game.getId(), game.getName(), game.getKategorie(), game.getGroesse());
                // Hat die Kategorie einen Namen, der noch nicht in den Comboboxen ist?
                if (((DefaultComboBoxModel<String>) MainWindow.kategoriebox.getModel()).getIndexOf(game.getKategorie()) == -1) {
                    // Füge die gerade aktuelle Kategorie in die Comboboxen ein
                    MainWindow.kategoriebox.addItem(game.getKategorie());
                    MainWindow.kategorieFilterBox.addItem(game.getKategorie());

                }
            }
        }
    }
}
