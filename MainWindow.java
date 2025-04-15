// Komplett überarbeiteter Code mit besserem UI-Layout und Styling
import javax.swing.*;
import java.awt.*;
import javax.swing.table.*;

import java.awt.event.*;

public class MainWindow {

    private static Point mouseClickLocation;
    public static JFrame frame = new JFrame("Datei Management System");
    public static ButtonCustom btn_Hinzufügen = new ButtonCustom();
    public static ButtonCustom btn_HinzufügenPfad = new ButtonCustom();
    public static ButtonCustom btn_löschen = new ButtonCustom();
    public static ButtonCustom btn_ändern = new ButtonCustom();
    public static ButtonCustom btn_Neu = new ButtonCustom();
    public static ButtonCustom btn_ÖffneOrdner = new ButtonCustom();
    public static ButtonCustom btn_FilterAnwenden = new ButtonCustom();
    public static ButtonCustom btn_ExeÖfnnen = new ButtonCustom();
    public static ButtonCustom btn_Refresh = new ButtonCustom();
    public static JButton btn_Logout = new JButton();
    public static JTable tabelle;
    public static JComboBox<String> kategoriebox;
    public static JComboBox<String> kategorieFilterBox;
    public static JTextField pfadTextBox;
    public static JTextField titelTextbox;
    public static JTextField speicherPlatzTextBox;
    public static RoundToggleButton toggleSwitch = new RoundToggleButton("🌙");
    public static DefaultTableModel model;
    public static JLabel filterTitel = new JLabel("Sortieren Nach :");
    public static JLabel datenTitel = new JLabel("Daten manuell einfügen ");
    public static JLabel ordnerÖfnnenTitel = new JLabel("Wählen Sie ein Ordner aus");
    public static JLabel titel = new JLabel("Name :");
    public static JLabel kategorie = new JLabel("Kategorie :");
    public static JLabel speicherplatzTitel = new JLabel("Speicherplatz in GB:");
    public static JLabel labBenutzer = new JLabel();
    public static JLabel labWelcome = new JLabel("Willkommen, ");
    public static JList<String> kategorienList;
    // Startet das Programm und initialisiert das Fenster
    public static void starteProgramm(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1015, 790);  // Höhe um 100px vergrößert
        frame.setLayout(null);
        frame.setResizable(false);
        enableDragMove(frame);

        Image icon = Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/Ressource/images.png"));
        Image scaledIcon = icon.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        frame.setIconImage(scaledIcon);

        addToggleButton();
        addLabels();
        addButtons();
        addTextFields();
        addSeparators();
        addComboBox();
        addTabel();
        addList();

        frame.setVisible(true);

        //Alle Textboxen leeren
        pfadTextBox.setText("");
        titelTextbox.setText("");
        speicherPlatzTextBox.setText("");
        kategoriebox.setSelectedItem("");

        //Rufe die Methode aus dem Main.java aus
        Main.InitializeMainWindow();

        //Je nach dem ob Darkmode an ist, stelle das Fenster auf Darkmode
        if (Main.isDarkmode) {
            DarkmodeHandler.setMainDarkmode();
        }
        else{
            DarkmodeHandler.setMainLightmode();
        }
    }

    // Ermöglicht das Ziehen des Fensters per Maus
    public static void enableDragMove(JFrame frame) {
        frame.getContentPane().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseClickLocation = e.getPoint();
            }
        });

        frame.getContentPane().addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point currentScreenPos = e.getLocationOnScreen();
                frame.setLocation(currentScreenPos.x - mouseClickLocation.x, currentScreenPos.y - mouseClickLocation.y);
            }
        });
    }

    private static void addToggleButton(){
        toggleSwitch.setBounds(50, 80, 25, 25); 
        toggleSwitch.setFocusPainted(false);
        toggleSwitch.setBorderPainted(false);             
        toggleSwitch.setContentAreaFilled(false); 
        toggleSwitch.setToolTipText("Darkmode AN/AUS");
        
        toggleSwitch.setFont(new Font("Segoe UI Emoji", Font.BOLD, 14));
        frame.add(toggleSwitch);
    }

    // Fügt alle statischen Labels hinzu
    private static void addLabels() {
        filterTitel.setBounds(90, 380, 200 ,30);  
        filterTitel.setFont(new Font("Segoe UI", Font.BOLD, 16));

        datenTitel.setBounds(370, 120, 400, 40);  
        datenTitel.setFont(new Font("Segoe UI", Font.BOLD, 16));

        ordnerÖfnnenTitel.setBounds(50, 120, 200, 30);  
        ordnerÖfnnenTitel.setFont(new Font("Segoe UI", Font.BOLD, 14));

        titel.setBounds(300, 170, 100, 30);  
        titel.setFont(new Font("Segoe UI", Font.BOLD, 14));

        kategorie.setBounds(300, 210, 100, 30);  
        kategorie.setFont(new Font("Segoe UI", Font.BOLD, 14));

        speicherplatzTitel.setBounds(300, 250, 140, 30);  
        speicherplatzTitel.setFont(new Font("Segoe UI", Font.BOLD, 14));

        labBenutzer.setBounds(560, 20, 150, 40);
        labBenutzer.setFont(new Font("Segoe UI", Font.BOLD, 25));

        labWelcome.setBounds(390, 20, 170, 40);
        labWelcome.setFont(new Font("Segoe UI", Font.BOLD, 25));


        frame.add(labBenutzer);
        frame.add(labWelcome);
        frame.add(filterTitel);
        frame.add(datenTitel);
        frame.add(ordnerÖfnnenTitel);
        frame.add(titel);
        frame.add(kategorie);
        frame.add(speicherplatzTitel);
    }

    // Fügt alle Buttons zum Frame hinzu und setzt deren Design & Position
    public static void addButtons() {
        btn_Hinzufügen.setText("Hinzufügen");
        btn_Hinzufügen.setBounds(820, 140, 150, 40);  

        btn_löschen.setText("Löschen");
        btn_löschen.setBounds(820, 190, 150, 40);  
        btn_löschen.setStyle(ButtonCustom.ButtonStyle.DESTRUCTIVE);

        btn_ändern.setText("Änderungen speichern");
        btn_ändern.setBounds(820, 240, 150, 40);  
        btn_ändern.setStyle(ButtonCustom.ButtonStyle.SECONDARY);

        btn_Neu.setText("Neu");
        btn_Neu.setBounds(820, 290, 150, 40);  

        btn_ÖffneOrdner.setText("Öffne Ordner");
        btn_ÖffneOrdner.setBounds(60, 190, 150, 40);  

        btn_FilterAnwenden.setText("Anwenden");
        btn_FilterAnwenden.setBounds(50, 680, 100, 30);  

        btn_HinzufügenPfad.setText("Exe-Datei hinzufügen");
        btn_HinzufügenPfad.setBounds(300, 300, 150, 30);  
        btn_HinzufügenPfad.setStyle(ButtonCustom.ButtonStyle.SECONDARY);

        btn_ExeÖfnnen.setText("Exe-Datei Öffnen");
        btn_ExeÖfnnen.setBounds(330, 680, 500, 30);  

        btn_Logout.setBounds(40, 10, 50, 50);
        btn_Logout.setOpaque(false);        //Transparent
        btn_Logout.setContentAreaFilled(false);     //Kein normaler hintergrud
        btn_Logout.setBorderPainted(false);         //Und kein border mehr
        btn_Logout.setIcon(new ImageIcon(Main.class.getResource("/Ressource/logout_resize.png")));     //Bild als Icon in hintergrund, skaliert sich selbst        

        btn_Refresh.setBounds(170, 680, 100, 30); 
        //btn_Refresh.setIcon(new ImageIcon(Main.class.getResource("/Ressource/refresh-25.png")));
        btn_Refresh.setText("↪ Zurücksetzen");
        btn_Refresh.setIconTextGap(10);
        btn_Refresh.setHorizontalTextPosition(SwingConstants.RIGHT);
        btn_Refresh.setHorizontalAlignment(SwingConstants.LEFT);

        //Button wird rausgenommen, vielleicht später nochmal rein wenn wir die funktion brauchen
        //frame.add(btn_Logout);
        
        frame.add(btn_Refresh);
        frame.add(btn_ExeÖfnnen);
        frame.add(btn_Hinzufügen);
        frame.add(btn_löschen);
        frame.add(btn_ändern);
        frame.add(btn_Neu);
        frame.add(btn_ÖffneOrdner);
        frame.add(btn_FilterAnwenden);
        frame.add(btn_HinzufügenPfad);
    }

    // Fügt die ComboBoxen hinzu und stylt sie
    private static void addComboBox() {
        kategoriebox = new JComboBox<>();
        kategoriebox.setEditable(true);
        kategoriebox.setBounds(455, 210, 250 ,30);  
        kategoriebox.setFont(new Font("Segoe UI", Font.BOLD, 14));
        kategoriebox.setBackground(Color.WHITE);
        kategoriebox.setForeground(Color.DARK_GRAY);
        kategoriebox.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        kategorieFilterBox = new JComboBox<>();
        kategorieFilterBox.setBounds(50, 420, 220 ,30);  
        kategorieFilterBox.setFont(new Font("Segoe UI", Font.BOLD, 14));
        kategorieFilterBox.setBackground(Color.WHITE);
        kategorieFilterBox.setForeground(Color.DARK_GRAY);
        kategorieFilterBox.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        frame.add(kategoriebox);
        frame.add(kategorieFilterBox);
    }

    // Fügt die Textfelder hinzu und setzt deren Position
    private static void addTextFields() {
        titelTextbox = new JTextField();
        titelTextbox.setBounds(455, 170, 250, 30);  

        speicherPlatzTextBox = new JTextField();
        speicherPlatzTextBox.setBounds(455, 250, 250, 30);  

        pfadTextBox = new JTextField();
        pfadTextBox.setBounds(505, 290, 200, 30);  
        pfadTextBox.setEditable(false);

        frame.add(titelTextbox);
        frame.add(speicherPlatzTextBox);
        frame.add(pfadTextBox);
    }

    // Erstellt die Tabelle, setzt das Modell und macht sie hübsch
    private static void addTabel() {
        String[] spalten = {"ID","Name", "Kategorie", "Speicherplatz in GB"};
        model = new DefaultTableModel(spalten, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabelle = new JTable(model);
        tabelle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabelle.setRowHeight(24);
        tabelle.setGridColor(new Color(200, 200, 200));
        tabelle.setSelectionBackground(new Color(100, 149, 237));
        tabelle.setSelectionForeground(Color.WHITE);
        tabelle.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        TableColumnModel columnModel = tabelle.getColumnModel();
        columnModel.getColumn(0).setMinWidth(0);
        columnModel.getColumn(0).setMaxWidth(0);
        columnModel.getColumn(0).setPreferredWidth(0);

        JScrollPane scrollPane = new JScrollPane(tabelle);
        scrollPane.setBounds(330, 420, 500, 250);  
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        frame.add(scrollPane);
    }

    // Erstellt die Sortierliste auf der linken Seite
    private static void addList() {
        String[] kategorien = {"Name aufsteigend", "Name absteigend", "Kategorie aufsteigend", "Kategorie absteigend", "Speicherplatz aufsteigend", "Speicherplatz absteigend"};
        kategorienList = new JList<>(kategorien);
        kategorienList.setFont(new Font("Segoe UI", Font.BOLD, 14));
        kategorienList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        kategorienList.setSelectedIndex(-1);
        kategorienList.setBackground(new Color(245, 245, 245));
        kategorienList.setForeground(new Color(50, 50, 50));
        kategorienList.setSelectionBackground(new Color(140, 140, 140));
        kategorienList.setSelectionForeground(Color.WHITE);
        kategorienList.setFixedCellHeight(25);
        kategorienList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                return label;
            }
        });

        JScrollPane scrollPane = new JScrollPane(kategorienList);
        scrollPane.setBounds(50, 480, 220, 155);  
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));
        frame.add(scrollPane);
    }

    // Fügt Trennlinien (Separatoren) zur optischen Abgrenzung ein
    private static void addSeparators() {
        JSeparator sep1 = new JSeparator();
        sep1.setBounds(30, 370, 880, 1);  
        sep1.setForeground(new Color(128, 128, 128));

        JSeparator sep2 = new JSeparator();
        sep2.setBounds(130, 90, 780, 1);  
        sep2.setForeground(new Color(128, 128, 128));

        JSeparator sepVert = new JSeparator(SwingConstants.VERTICAL);
        sepVert.setForeground(new Color(128, 128, 128));
        sepVert.setBounds(800, 110, 2, 240);  

        JSeparator sepVertCenter = new JSeparator(SwingConstants.VERTICAL);
        sepVertCenter.setForeground(new Color(128, 128, 128));
        sepVertCenter.setBounds(270, 110, 2, 240);  

        frame.add(sep1);
        frame.add(sep2);
        frame.add(sepVert);
        frame.add(sepVertCenter);
    }

    // Fügt einen neuen Eintrag in die Tabelle hinzu
    public static void eintragHinzufuegen(String ID, String name, String kategorie, String speicher) {
        //Wandel den Speicherpatz in einen Double um das du auch nach ihm sortieren kann
        Double DBL_speicher = Double.parseDouble(speicher);
        
        model.addRow(new Object[]{ID, name, kategorie, DBL_speicher});
    }

    //Methode macht Button Hinzufügen und HinzufügenPfad nicht mehr anklickbar und macht die anderen nutzbar
    public static void DisableButtons() {
        //Darkmode ist an...
        if (Main.isDarkmode) {
            //BTN hinzufügen  ausgrauen
            MainWindow.btn_Hinzufügen.setStyle(ButtonCustom.ButtonStyle.DISABLED_DARK);        
            
            //Alle anderen normal
            MainWindow.btn_löschen.setStyle(ButtonCustom.ButtonStyle.DARK_WARNING);
            MainWindow.btn_ändern.setStyle(ButtonCustom.ButtonStyle.DARK_PRIMARY);
            MainWindow.btn_ExeÖfnnen.setStyle(ButtonCustom.ButtonStyle.DARK_ACCENT);
        }
        //Darkmode ist aus...
        else {
            //BTN hinzufügen ausgrauen
            MainWindow.btn_Hinzufügen.setStyle(ButtonCustom.ButtonStyle.DISABLED_LIGHT);

            MainWindow.btn_löschen.setStyle(ButtonCustom.ButtonStyle.DESTRUCTIVE);
            MainWindow.btn_ändern.setStyle(ButtonCustom.ButtonStyle.PRIMARY);
            MainWindow.btn_ExeÖfnnen.setStyle(ButtonCustom.ButtonStyle.SECONDARY);
        }
        
        //Wenn was ausgewählt wurde kannst du ändern, löschen oder exe-öffnen, aber nicht hinzufügen
        MainWindow.btn_Hinzufügen.setEnabled(false);

        MainWindow.btn_löschen.setEnabled(true);
        MainWindow.btn_ändern.setEnabled(true);
        MainWindow.btn_ExeÖfnnen.setEnabled(true);
    };

    //Methode macht Button Hinzufügen und HinzufügenPfad anklickbar und graut andere aus
    public static void EnableButtons() 
    {
        //Darkmode ist an...
        if (Main.isDarkmode) {
            //BTN hinzufügen ausgrauen
            MainWindow.btn_Hinzufügen.setStyle(ButtonCustom.ButtonStyle.DARK_PRIMARY);        
        
            //Alle anderen normal
            MainWindow.btn_löschen.setStyle(ButtonCustom.ButtonStyle.DISABLED_DARK);
            MainWindow.btn_ändern.setStyle(ButtonCustom.ButtonStyle.DISABLED_DARK);
            MainWindow.btn_ExeÖfnnen.setStyle(ButtonCustom.ButtonStyle.DISABLED_DARK);
        }
        //Darkmode ist aus...
        else {
            //BTN hinzufügen /ausgrauen
            MainWindow.btn_Hinzufügen.setStyle(ButtonCustom.ButtonStyle.PRIMARY);
            
            MainWindow.btn_löschen.setStyle(ButtonCustom.ButtonStyle.DISABLED_LIGHT);
            MainWindow.btn_ändern.setStyle(ButtonCustom.ButtonStyle.DISABLED_LIGHT);
            MainWindow.btn_ExeÖfnnen.setStyle(ButtonCustom.ButtonStyle.DISABLED_LIGHT);
        }

        //Wenn ncihts ausgewählt ist, kannst du nur hinzufügen
        MainWindow.btn_Hinzufügen.setEnabled(true);

        MainWindow.btn_löschen.setEnabled(false);
        MainWindow.btn_ändern.setEnabled(false);
        MainWindow.btn_ExeÖfnnen.setEnabled(false);
    };
}
