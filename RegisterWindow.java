import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegisterWindow {

    private static Point mouseClickLocation;
    public static JFrame frame = new JFrame("Registrieren");
    public static ButtonCustom btn_Login = new ButtonCustom();
    public static JLabel benutzerJLabel = new JLabel("Benutzername eingeben:");
    public static JLabel passwortJLabel= new JLabel("Passwort eingeben:");
    public static JLabel passwortJLabel2= new JLabel("Passwort erneut eingeben:");
    public static JLabel loginTextJLabel = new JLabel("Erstelle einen Benutzer");
    public static ButtonCustom btn_Abbrechen = new ButtonCustom();
    public static JTextField benutzerNamTextField = new JTextField();
    public static JTextField txtPassword1 = new JTextField();
    public static JTextField txtPassword2 = new JTextField();
    public static RoundToggleButton toggleSwitchLogin = new RoundToggleButton("🌙");
    

    // Startet das Programm und initialisiert das Fenster
    public static void starteProgramm(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(550, 320);
        frame.setLayout(null);
        frame.setResizable(false);
        enableDragMove(frame);


        

        Image icon = Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/Ressource/images.png"));
        Image scaledIcon = icon.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        frame.setIconImage(scaledIcon);



        addTextFields();
        addToggleButton();
        addLabels();
        addButtons();
        frame.setVisible(true);

        //Alle textboxen leer
        benutzerNamTextField.setText("");
        txtPassword1.setText("");
        txtPassword2.setText("");

        Main.InitializeButtonsRegister();

        //Je nach dem ob Darkmode an ist, stelle das Fenster auf Darkmode
        if (Main.isDarkmode) {
            DarkmodeHandler.setRegisterDarkmode();
        }
        else{
            DarkmodeHandler.setRegisterLightmode();
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

        toggleSwitchLogin.setBounds(10, 10, 25, 25);
        toggleSwitchLogin.setFocusPainted(false);
        toggleSwitchLogin.setBorderPainted(false);             
        toggleSwitchLogin.setContentAreaFilled(false); 
        toggleSwitchLogin.setToolTipText("Darkmode AN/AUS");
        
        toggleSwitchLogin.setFont(new Font("Segoe UI Emoji", Font.BOLD, 14));
        frame.add(toggleSwitchLogin);
    }
    
    private static void addLabels() {
        loginTextJLabel.setBounds(60, 10, 200, 30);
        loginTextJLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));

        benutzerJLabel.setBounds(60, 70, 200, 30);
        benutzerJLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
    
        passwortJLabel.setBounds(60, 120, 200, 30);
        passwortJLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
    
        passwortJLabel2.setBounds(60, 170, 200, 30);
        passwortJLabel2.setFont(new Font("Segoe UI", Font.BOLD, 14));

        frame.add(passwortJLabel2);
        frame.add(loginTextJLabel);
        frame.add(benutzerJLabel);
        frame.add(passwortJLabel);

    }

    // Fügt alle Buttons zum Frame hinzu und setzt deren Design & Position
    public static void addButtons() {
        // Login Button unter Passwort-Feld
        btn_Login.setText("Registrieren");
        btn_Login.setBounds(125, 220, 125, 30);
        btn_Login.setStyle(ButtonCustom.ButtonStyle.PRIMARY);

        // Registrieren Button daneben
        btn_Abbrechen.setText("Abbrechen");
        btn_Abbrechen.setBounds(300, 220, 125, 30);
        btn_Abbrechen.setStyle(ButtonCustom.ButtonStyle.DESTRUCTIVE);

        frame.add(btn_Login);
        frame.add(btn_Abbrechen);
    }

    private static void addTextFields() {
        
        benutzerNamTextField.setBounds(250, 70, 200, 30);

        txtPassword1.setBounds(250, 120, 200, 30);
        txtPassword2.setBounds(250, 170, 200, 30);

        frame.add(txtPassword1);
        frame.add(benutzerNamTextField);
        frame.add(txtPassword2);
    }
}