import java.io.File;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class GameManager {
    //Methode sucht sich den letzten Eintrag aus dem Games-Array und rechnet auf die ID die es da gibt eins drauf und gibt das dann zurück
    public static String GetNewID(ArrayList<String[]> games) {
        //Überprüfe ob die ArrayList leer ist
        if (!games.isEmpty()) { 
            //Wenn nicht, bestimme die ID der letzten Zeile in der 0. Spalte 
            String ID = games.get(games.size() -1)[0];

            //Wandel sie um und erhöhe um 1
            Integer _ID = (Integer.parseInt(ID) + 1);
            return _ID.toString();
        } else {
            //Is die Liste leer, dann vergib die erste ID
            return "1";
        }
    }

    //Methode wird aufgerufen um zu überprüfen ob die Werte beim hinzufügen oder ändern korrekt sind
    public boolean AreValuesRight() {
        String titel = MainWindow.titelTextbox.getText();
        String pfad = MainWindow.pfadTextBox.getText();

        String kategorieString;
        try {
            kategorieString = MainWindow.kategoriebox.getSelectedItem().toString();
        } catch (Exception e) {
            //Wenn er da nichts nehmen kann, dann hat er quasi keine kategorie eingegeben
            kategorieString = "";
        }
     
        try {
            Double.parseDouble(MainWindow.speicherPlatzTextBox.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Der Speicherplatz muss eine Zahl sein");
            return false;
        }

        //Ist der Titel eingegeben?
        if (titel.length() < 1) {
            JOptionPane.showMessageDialog(null,"Sie müssen einen Namen eingeben um ein Spiel hinzuzufügen oder zu ändern");
            return false;
        }
        //Ist eine Kategorie ausgewählt?
        else if (kategorieString.length() < 1) {
            JOptionPane.showMessageDialog(null, "Sie müssen eine Kategorie auswählen oder eingeben um ein Spiel hinzuzufügen oder zu ändern");
            return false;
        }
        //Hat er einen Pfad ausgewählt?
        else if (pfad.length() >= 1) {
            //Existiert der Pfad?
            File exepfad = new File(pfad);
            if (!exepfad.exists()) {
                JOptionPane.showMessageDialog(null, "Der Pfad '" + pfad + "' konnte nicht gefunden werden. \nWähle den Pfad für eine  exe-Datei aus um ein Spiel hinzuzufügen oder zu ändern");
                return false;
            }

            //Überprüfe ob die Datei dann mit exe endet
            if (!pfad.endsWith(".exe")) {

                JOptionPane.showMessageDialog(null, "Die ausgewählte Datei muss einer exe-Datei entsprechen, um ein Spiel hinzuzufügen oder zu ändern");
                return false;
            }
        }
        
        //Alles Werte richtig
        return true;
    } 

    //Methode überprüft ob das löschen oder ändern zulässig ist
    public boolean IsOkay(String type) {
        //Merke dir Selected Index
        int SelectedIndex = MainWindow.tabelle.getSelectedRow();
        
        //Hat eine Zeile ausgewählt?
        if (SelectedIndex == -1) {
            //Fehlermeldung warum er nicht löschen darf
            JOptionPane.showMessageDialog(null, "Wähle ein Spiel aus, um es " + type);
            return false;
        }

        //Bestimmt das Spiel was gelöscht werden soll
        String gamename = MainWindow.tabelle.getValueAt(SelectedIndex, 1).toString();

        //Frage den Benutzer ob er wirklich löschen will
        int option;
        //Bestimme MSG-Box jhe nachg vorgang
        if (type.equals("zu löschen")) {
            option = JOptionPane.showOptionDialog(null, "Möchten sie das Spiel '" + gamename + "' wirklich aus ihrer Bibliothek löschen?", "Warnung", 
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        }
        else {
            option = JOptionPane.showOptionDialog(null, "Möchten sie das Spiel '" + gamename + "' aus ihrer Bibliothek wirklich abändern?", "Warnung", 
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        }

        if (option != JOptionPane.YES_OPTION) {
            //Wenn er nicht löschen will, soll er auch nicht
            return false;
        }
        
        //Alles okay, darf deletem
        return true;
    }

    //Methode um bei Auswahl einer Zeile in der Tabelle die Werte in die Textbox zu schreiben
    public static void PrintValuesInTextboxes() {
        //Mache es nur wenn auch was augewählt wurde
        if (MainWindow.tabelle.getSelectedRow() != -1) {
            //Merke dir die ID die ausgewählt wurde
            String ID = MainWindow.tabelle.getValueAt(MainWindow.tabelle.getSelectedRow(), 0).toString();

            //Suche den DS der dieser ID zugehörig ist
            String[] values = null;
            for (String[] game : EventHandler.games) {
                if (game.length > 0 && game[0].equals(ID)) {
                    values = game;
                    break;
                }
            }

            //Printe die Werte nur, wenn du auch welche hast
            if (values != null) {                
                //Werte in die Textboxen
                MainWindow.titelTextbox.setText(values[1]);
                MainWindow.kategoriebox.setSelectedItem(values[2]);
                MainWindow.speicherPlatzTextBox.setText(String.format(Locale.US, "%.3f", Double.parseDouble(values[3])));
                MainWindow.pfadTextBox.setText(values[4]);
                
                //Zeige als Tooltip den ausgewählten pfad an
                MainWindow.pfadTextBox.setToolTipText(values[4]);
            }
            
            //Buttons zum Hinzufügen weg, andere aktiv
            MainWindow.DisableButtons();
        }
        else {
            //Alles unsichtbar, außer hinzufügen und neu
            MainWindow.EnableButtons();
        }
    }

    //Methode entfernt den DS aus dem array (gibt es dann auch zurück) und aus der tabelle
    public void DeleteGame() {
        //Bestimme die ID des ausgewählten Indexes in der Tabelle
        String ID = GetIDFromTable(); 

        //Lösche den DS auch aus dem Array
        for (String[] element : EventHandler.games) {
            //Hat der aktuelle DS grade die ID?
            if (element[0] == ID) {
                //Dann lösche ihn raus und verlasse die Schleife
                EventHandler.games.remove(element);
                break;
            }
        }
    }

    //Methode fügt das Spiel zum Array und zur Tabelle hinzu. ggf. auch die kategorie in die ComboBox
    public void AddGame() {
        //Eintrag zum Array hizufügen
        EventHandler.games.add(new String[] {GetNewID(EventHandler.games), MainWindow.titelTextbox.getText(), MainWindow.kategoriebox.getSelectedItem().toString(), MainWindow.speicherPlatzTextBox.getText(), MainWindow.pfadTextBox.getText(), EventHandler.UserID});
        
        //Wenn die Kategorie noch nicht in der Combo ist, füg sie hinzu
        if (((DefaultComboBoxModel<String>) MainWindow.kategoriebox.getModel()).getIndexOf(MainWindow.kategoriebox.getSelectedItem().toString()) == -1) {
            
            //Füge die grade hinzugefügt Kategorie in die Comboboxen ein
            MainWindow.kategoriebox.addItem(MainWindow.kategoriebox.getSelectedItem().toString());
            MainWindow.kategorieFilterBox.addItem(MainWindow.kategoriebox.getSelectedItem().toString());
        }
    }

    //Methode überprüft welcher Eintrag in der Tabelle angeklickt wurde und gibt die ID davon zurück
    private String GetIDFromTable() {
        //Merke dir den ausgewählten Index
        Integer SelectedIndex = MainWindow.tabelle.getSelectedRow();

        //Wenn keiner ausgewählt ist, gib die -1 zurück
        if (SelectedIndex == -1) {
            return SelectedIndex.toString();
        }
        else {
            //Ansonsten gib die ID zurück, die den DS zugehörig ist der in der Tabelle angeklickt wurde
            return MainWindow.tabelle.getValueAt(SelectedIndex, 0).toString();
        }
    }

    //Methode ändert die Werte für ein AUsgewähltes Spiel
    public void ChangeGame() {
        //Bestimme die ID des ausgewählten Indexes in der Tabelle
        String ID = GetIDFromTable();
        
        //Finde den DS im Array
        for (String[] spiel : EventHandler.games) {
            //Hat der DS die ID
            if (spiel[0] == ID) {
                //Ersetze die Werte im DS durch die neuen
                spiel[1] = MainWindow.titelTextbox.getText();
                spiel[2] = MainWindow.kategoriebox.getSelectedItem().toString();
                spiel[3] = MainWindow.speicherPlatzTextBox.getText();
                spiel[4] = MainWindow.pfadTextBox.getText();
                spiel[5] = EventHandler.UserID;
            }
        }
        
        //Wenn die Kategorie noch nicht in der Combo ist, füg sie hinzu
        if (((DefaultComboBoxModel<String>) MainWindow.kategoriebox.getModel()).getIndexOf(MainWindow.kategoriebox.getSelectedItem().toString()) == -1) {
            
            //Füge die grade hinzugefügt Kategorie in die Comboboxen ein
            MainWindow.kategoriebox.addItem(MainWindow.kategoriebox.getSelectedItem().toString());
            MainWindow.kategorieFilterBox.addItem(MainWindow.kategoriebox.getSelectedItem().toString());
        }
    }

    //Methode wird aufgerufen um eine Exe-Datei zu öffnen
    public void OpenExe() {
        //Bestimme die ID des ausgewählten Indexes in der Tabelle
        String ID = GetIDFromTable(); 

        //Wenn die ID -1 ist dann muss er erst eins auswählen
        if (ID == "-1") {
            JOptionPane.showMessageDialog(null, "Bitte ein Spiel auswählen um eine Exe-Datei dazu zu öffnen");
        }

        //Suche nach dem DS mit der ID
        for (String[] element : EventHandler.games) {
            //Hat der DS die gesuchte ID?
            if (element[0] == ID) {
                //Hat der DS überhaupt nen Exe-Pfad
                if (element[4].length() >= 1) {                
                    //Bilde dir den Command für Powershell damit du mit Admin-Rechten ausführen kannst wenn nötig
                    String command = "powershell -Command \"Start-Process '" + element[4] + "' -Verb runAs\"";

                    try {
                        //Starte dann die Exe erstmal ohne admin rechte
                        Runtime.getRuntime().exec(element[4]);
                    } 
                    catch (Exception e) {
                        try {
                            //Wenn du ohne nicht starten kannst, probiere es nochmal mit Admin-Rechten
                            Runtime.getRuntime().exec(command);    
                        } 
                        catch (Exception ee) {
                            //Wenn du weder mit, noch ohne admin rechten starten kannst gibts nen Error
                            JOptionPane.showMessageDialog(null, "Die Exe-Datei konnte nicht geöffnet werden. \nÜberprüfen sie den Pfad und versuchen es erneut!", "Fehler beim Öffnen der Exe-Datei", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
                else {
                    //Er hat keinen, dann frag ob er hinzufügen will
                    int option = JOptionPane.showOptionDialog(null, "Sie haben für das Spiel '" + element[1] + "' keinen Pfad zu einer Exe-Datei hinzugefügt\n\nMöchten sie eine hinzufügen?", "", 
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

                    //Will er?
                    if (option == JOptionPane.YES_OPTION) {
                        //Speichere den Exe-Pfad im DS
                        element[4] = SelectExefile();

                        try {
                            //Versuche ihn zu öffnen
                            Runtime.getRuntime().exec(element[4]);

                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Die Exe-Datei konnte zwar hinzugefügt aber nicht geöffnet werden. \nÜberprüfen sie den Pfad und versuchen es erneut!\n\n" + e.getMessage());
                        }
                    }
                    else {
                        //Falls nicht, dann mach halt nichts
                        return;
                    }
                }
            }
        }
    }

    //Methode öffnet einen FileDialog und gibt den Pfad zur Exe zurück
    public String SelectExefile() {
        String path = "";

       //FileChooser erstellen und als Anfangs directory da wo Programme und Programme (x86) sind
       JFileChooser filechooser = new JFileChooser(new File(System.getenv("SystemDrive") + "\\"));

        //Nur exe-dateien anzeigbar
        filechooser.setFileFilter(new FileNameExtensionFilter("Executable Dateien (*.exe)", "exe"));

       //Öffnen
       int result = filechooser.showOpenDialog(null);

        //Hat er etwas ausgewählt? 
        if (result == JFileChooser.APPROVE_OPTION) {
            //wandle die ausgewählte File in eine Instanz aus der FIle klasse um und gib den ganzen Pfad back
            return filechooser.getSelectedFile().getAbsolutePath();
        }

        //Hat nichts ausgewählt dann bekommt er direkt nen leeren String einfach
        return path;
    }

    //Methode öffnet einen FolderBrowserDialog, der ausgewählte Ordner entspricht einem Spiel
    public void SelectFolder() {
        //FBD erstellen
        JFileChooser chooser = new JFileChooser(new File(System.getenv("SystemDrive") + "\\"));
        chooser.setDialogTitle("Wähle den Ordner eines Spieles aus, welches Sie hinzufügen willst");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);    //Nur Ordner auswählbar
        chooser.setAcceptAllFileFilterUsed(false);

        //Dialog anzeigen
        int result = chooser.showOpenDialog(null);
        
        //Hat er einen ausgewählt?
        if (result == JFileChooser.APPROVE_OPTION) {
            //Speichere ausgewählten ordner
            File selectedFolder = chooser.getSelectedFile();
        
            //Printe Name in die Textbox
            MainWindow.titelTextbox.setText(selectedFolder.getName());
            
            //Und auch den Speicherplatz
            MainWindow.speicherPlatzTextBox.setText(String.format("%.3f", getFolderSize(selectedFolder)));
 
        }

        //Falls nich dann passiert nix
    }

    //Methode um nach Auswahl des Ordners die Größe zu berechnen
    private double getFolderSize(File folder) {
        double size = 0;                    //Erstmal 0 Bytes
        File[] files = folder.listFiles();  //Liste aller files in dem Ordner

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    size += file.length();
                } else {
                    //Selbe methode nochmal aufrufen, um die Größe des Unterordners zu berechnen
                    size += getFolderSize(file); 
                }
            }
        }

        //Rechne Bytes in GB um
        size /= 1073741824;

        //Runde die GB auf 2 nachkommastellen und gib zurück
        return Math.round(size * 100.0) / 100.0;
    }
}