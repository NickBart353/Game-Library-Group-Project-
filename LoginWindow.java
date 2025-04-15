import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginWindow {

    private static Point mouseClickLocation;
    public static JFrame frame = new JFrame("Login Fenster");
    public static ButtonCustom btn_Login = new ButtonCustom();
    public static JLabel benutzerJLabel = new JLabel("Benutzer:");
    public static JLabel passwortJLabel= new JLabel("Passwort:");
    public static JLabel loginTextJLabel = new JLabel("Loggen sie sich ein");
    public static ButtonCustom btn_Registrieren = new ButtonCustom();
    public static JTextField benutzerNamTextField = new JTextField();
    public static JTextField passwTextField = new JTextField();
    public static RoundToggleButton toggleSwitchLogin = new RoundToggleButton("🌙");
    

    // Startet das Programm und initialisiert das Fenster
    public static void starteProgramm(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 250);
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

        //Alle Textboxen clearen
        benutzerNamTextField.setText("");
        passwTextField.setText("");

        Main.InitializeButtonsLogin();

        //Je nach dem ob Darkmode an ist, stelle das Fenster auf Darkmode
        if (Main.isDarkmode) {
            DarkmodeHandler.setLoginDarkmode();
        }
        else{
            DarkmodeHandler.setLoginLightmode();
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
    // Fügt alle statischen Labels hinzu
    private static void addLabels() {
        

        loginTextJLabel.setBounds(150, 10, 200, 30);
        loginTextJLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));

        benutzerJLabel.setBounds(70, 45, 200, 30);
        benutzerJLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

        passwortJLabel.setBounds(70, 95, 200, 30);
        passwortJLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

        frame.add(loginTextJLabel);
        frame.add(benutzerJLabel);
        frame.add(passwortJLabel);

    }

    // Fügt alle Buttons zum Frame hinzu und setzt deren Design & Position
    public static void addButtons() {

        btn_Login.setText("Einloggen");
        btn_Login.setBounds(150, 140, 100, 30);
        btn_Login.setStyle(ButtonCustom.ButtonStyle.PRIMARY);

        btn_Registrieren.setText("Registrieren");
        btn_Registrieren.setBounds(300, 140, 100, 30);
        btn_Registrieren.setStyle(ButtonCustom.ButtonStyle.DESTRUCTIVE);

        frame.add(btn_Registrieren);

        frame.add(btn_Login);
    }
    private static void addTextFields() {
        
        benutzerNamTextField.setBounds(150, 50, 250, 30);
        passwTextField.setBounds(150, 100, 250, 30);


        frame.add(benutzerNamTextField);
        frame.add(passwTextField);
    }



}