import java.awt.Color;
import javax.swing.JLabel;

public class DarkmodeHandler {
    
    private static Color lightBackground = new Color(238, 238, 238);
    private static Color darkBackground = new Color( 31, 31, 31);
    private static Color darkText = new Color(220, 220, 220);
    private static Color lightText = Color.BLACK;



    public static void setMainDarkmode() {

        // Hintergrund des Fensters
        MainWindow.frame.getContentPane().setBackground(darkBackground);

        // Labels stylen
        JLabel[] labels = {
            MainWindow.filterTitel,
            MainWindow.datenTitel,
            MainWindow.ordnerÖfnnenTitel,
            MainWindow.titel,
            MainWindow.kategorie,
            MainWindow.speicherplatzTitel,
            MainWindow.labBenutzer,
            MainWindow.labWelcome
        };

        for (JLabel label : labels) {
            label.setForeground(darkText);
            label.setOpaque(false); // setBackground nur nötig wenn du opak willst
        }

        // Buttons auf Darkmode setzen
        MainWindow.btn_Hinzufügen.setStyle(ButtonCustom.ButtonStyle.DARK_PRIMARY);
        MainWindow.btn_HinzufügenPfad.setStyle(ButtonCustom.ButtonStyle.DARK_PRIMARY);
        MainWindow.btn_löschen.setStyle(ButtonCustom.ButtonStyle.DARK_WARNING);
        MainWindow.btn_ändern.setStyle(ButtonCustom.ButtonStyle.DARK_PRIMARY);
        MainWindow.btn_Neu.setStyle(ButtonCustom.ButtonStyle.DARK_PRIMARY);
        MainWindow.btn_ÖffneOrdner.setStyle(ButtonCustom.ButtonStyle.DARK_ACCENT);
        MainWindow.btn_FilterAnwenden.setStyle(ButtonCustom.ButtonStyle.DARK_PRIMARY);
        MainWindow.btn_Refresh.setStyle(ButtonCustom.ButtonStyle.DARK_PRIMARY);
        MainWindow.btn_ExeÖfnnen.setStyle(ButtonCustom.ButtonStyle.DARK_ACCENT);

        //Textboxen auch mal
        MainWindow.pfadTextBox.setForeground(darkText);
        MainWindow.pfadTextBox.setBackground(darkBackground);
        MainWindow.pfadTextBox.setCaretColor(darkText);
        
        MainWindow.titelTextbox.setForeground(darkText);
        MainWindow.titelTextbox.setBackground(darkBackground);
        MainWindow.titelTextbox.setCaretColor(darkText);
        
        MainWindow.kategorieFilterBox.setForeground(darkText);
        MainWindow.kategorieFilterBox.setBackground(darkBackground);
        MainWindow.kategorieFilterBox.getEditor().getEditorComponent().setBackground(darkBackground); //Textfeld auch hintergrund schwarz
        MainWindow.kategorieFilterBox.getEditor().getEditorComponent().setForeground(darkText); //Schrift vom Textfeld auch

        MainWindow.kategoriebox.setForeground(darkText);
        MainWindow.kategoriebox.setBackground(darkBackground);
        MainWindow.kategoriebox.getEditor().getEditorComponent().setBackground(darkBackground); //Textfeld auch hintergrund schwarz
        MainWindow.kategoriebox.getEditor().getEditorComponent().setForeground(darkText); //Schrift vom Textfeld auch

        MainWindow.speicherPlatzTextBox.setForeground(darkText);
        MainWindow.speicherPlatzTextBox.setBackground(darkBackground);
        MainWindow.speicherPlatzTextBox.setCaretColor(darkText);
    
        MainWindow.toggleSwitch.setSelected(true);
        MainWindow.toggleSwitch.setText("🌞");

        MainWindow.tabelle.setForeground(Color.WHITE);
        MainWindow.tabelle.setBackground(darkBackground);
        MainWindow.tabelle.setGridColor(darkBackground);

        MainWindow.kategorienList.setBackground(darkBackground);
        MainWindow.kategorienList.setForeground(Color.WHITE);


    }

    public static void setMainLightmode() {
        Color lightText = Color.BLACK;
        MainWindow.frame.getContentPane().setBackground(lightBackground);

        // Labels stylen
        JLabel[] labels = {
            MainWindow.filterTitel,
            MainWindow.datenTitel,
            MainWindow.ordnerÖfnnenTitel,
            MainWindow.titel,
            MainWindow.kategorie,
            MainWindow.speicherplatzTitel,
            MainWindow.labBenutzer,
            MainWindow.labWelcome
        };

        for (JLabel label : labels) {
            label.setForeground(lightText);
            label.setOpaque(false); // kannst du auch true setzen + Background
        }

        // Buttons auf Lightmode zurücksetzen
        MainWindow.btn_Hinzufügen.setStyle(ButtonCustom.ButtonStyle.PRIMARY);
        MainWindow.btn_HinzufügenPfad.setStyle(ButtonCustom.ButtonStyle.PRIMARY);
        MainWindow.btn_löschen.setStyle(ButtonCustom.ButtonStyle.DESTRUCTIVE);
        MainWindow.btn_ändern.setStyle(ButtonCustom.ButtonStyle.PRIMARY);
        MainWindow.btn_Neu.setStyle(ButtonCustom.ButtonStyle.PRIMARY);
        MainWindow.btn_ÖffneOrdner.setStyle(ButtonCustom.ButtonStyle.SECONDARY);
        MainWindow.btn_FilterAnwenden.setStyle(ButtonCustom.ButtonStyle.PRIMARY);
        MainWindow.btn_Refresh.setStyle(ButtonCustom.ButtonStyle.PRIMARY);
        MainWindow.btn_ExeÖfnnen.setStyle(ButtonCustom.ButtonStyle.SECONDARY);


        MainWindow.kategoriebox.setForeground(lightText);
        MainWindow.kategoriebox.setBackground(lightBackground);
        MainWindow.kategoriebox.getEditor().getEditorComponent().setBackground(lightBackground); //Textfeld auch hintergrund weiß
        MainWindow.kategoriebox.getEditor().getEditorComponent().setForeground(lightText); //Schrift vom Textfeld schwarz

        MainWindow.kategorieFilterBox.setForeground(lightText);
        MainWindow.kategorieFilterBox.setBackground(lightBackground);
        MainWindow.kategorieFilterBox.getEditor().getEditorComponent().setBackground(lightBackground); //Textfeld auch hintergrund weiß
        MainWindow.kategorieFilterBox.getEditor().getEditorComponent().setForeground(lightText); //Schrift vom Textfeld schwarz

        //Textboxen auch mal
        MainWindow.pfadTextBox.setForeground(lightText);
        MainWindow.pfadTextBox.setBackground(lightBackground);
        MainWindow.pfadTextBox.setCaretColor(lightText);
        
        MainWindow.titelTextbox.setForeground(lightText);
        MainWindow.titelTextbox.setBackground(lightBackground);
        MainWindow.titelTextbox.setCaretColor(lightText);
        
        MainWindow.speicherPlatzTextBox.setForeground(lightText);
        MainWindow.speicherPlatzTextBox.setBackground(lightBackground);
        MainWindow.speicherPlatzTextBox.setCaretColor(lightText);
        
        MainWindow.toggleSwitch.setSelected(false);
        MainWindow.toggleSwitch.setText("🌙");
    }
    
    public static void setLoginDarkmode() {
    
        LoginWindow.frame.getContentPane().setBackground(darkBackground);
    
        LoginWindow.loginTextJLabel.setForeground(darkText);
        LoginWindow.benutzerJLabel.setForeground(darkText);
        LoginWindow.passwortJLabel.setForeground(darkText);
    
        LoginWindow.benutzerNamTextField.setBackground(new Color(50, 50, 50));
        LoginWindow.benutzerNamTextField.setForeground(darkText);
        LoginWindow.benutzerNamTextField.setCaretColor(darkText);
    
        LoginWindow.passwTextField.setBackground(new Color(50, 50, 50));
        LoginWindow.passwTextField.setForeground(darkText);
        LoginWindow.passwTextField.setCaretColor(darkText);
    
        LoginWindow.btn_Login.setStyle(ButtonCustom.ButtonStyle.DARK_ACCENT);
        LoginWindow.btn_Registrieren.setStyle(ButtonCustom.ButtonStyle.DARK_ACCENT);
    
        
        LoginWindow.toggleSwitchLogin.setSelected(true);
        LoginWindow.toggleSwitchLogin.setText("🌞");
    }
    
    public static void setLoginLightmode() {
    
        LoginWindow.frame.getContentPane().setBackground(lightBackground);
    
        LoginWindow.loginTextJLabel.setForeground(lightText);
        LoginWindow.benutzerJLabel.setForeground(lightText);
        LoginWindow.passwortJLabel.setForeground(lightText);
    
        LoginWindow.benutzerNamTextField.setBackground(Color.WHITE);
        LoginWindow.benutzerNamTextField.setForeground(lightText);
        LoginWindow.benutzerNamTextField.setCaretColor(lightText);
    
        LoginWindow.passwTextField.setBackground(Color.WHITE);
        LoginWindow.passwTextField.setForeground(lightText);
        LoginWindow.passwTextField.setCaretColor(lightText);

        LoginWindow.toggleSwitchLogin.setSelected(false);
        LoginWindow.toggleSwitchLogin.setText("🌙");
    
        LoginWindow.btn_Login.setStyle(ButtonCustom.ButtonStyle.PRIMARY);
        LoginWindow.btn_Registrieren.setStyle(ButtonCustom.ButtonStyle.PRIMARY);
    }

    public static void setRegisterDarkmode() {
    
        RegisterWindow.frame.getContentPane().setBackground(darkBackground);
    
        RegisterWindow.loginTextJLabel.setForeground(darkText);
        RegisterWindow.benutzerJLabel.setForeground(darkText);
        RegisterWindow.passwortJLabel.setForeground(darkText);
        RegisterWindow.passwortJLabel2.setForeground(darkText);
    
        RegisterWindow.benutzerNamTextField.setBackground(new Color(50, 50, 50));
        RegisterWindow.benutzerNamTextField.setForeground(darkText);
        RegisterWindow.benutzerNamTextField.setCaretColor(darkText);
    
        RegisterWindow.txtPassword1.setBackground(new Color(50, 50, 50));
        RegisterWindow.txtPassword1.setForeground(darkText);
        RegisterWindow.txtPassword1.setCaretColor(darkText);
    
        RegisterWindow.txtPassword2.setBackground(new Color(50, 50, 50));
        RegisterWindow.txtPassword2.setForeground(darkText);
        RegisterWindow.txtPassword2.setCaretColor(darkText);
    
        RegisterWindow.toggleSwitchLogin.setSelected(true);
        RegisterWindow.toggleSwitchLogin.setText("🌞");

        RegisterWindow.btn_Login.setStyle(ButtonCustom.ButtonStyle.DARK_ACCENT);
        RegisterWindow.btn_Abbrechen.setStyle(ButtonCustom.ButtonStyle.DARK_ACCENT);
    }

    public static void setRegisterLightmode() {
    
        RegisterWindow.frame.getContentPane().setBackground(lightBackground);
    
        RegisterWindow.loginTextJLabel.setForeground(lightText);
        RegisterWindow.benutzerJLabel.setForeground(lightText);
        RegisterWindow.passwortJLabel.setForeground(lightText);
        RegisterWindow.passwortJLabel2.setForeground(lightText);
    
        RegisterWindow.benutzerNamTextField.setBackground(Color.WHITE);
        RegisterWindow.benutzerNamTextField.setForeground(lightText);
        RegisterWindow.benutzerNamTextField.setCaretColor(lightText);
    
        RegisterWindow.txtPassword1.setBackground(Color.white);
        RegisterWindow.txtPassword1.setForeground(lightText);
        RegisterWindow.txtPassword1.setCaretColor(lightText);
    
        RegisterWindow.txtPassword2.setBackground(Color.white);
        RegisterWindow.txtPassword2.setForeground(lightText);
        RegisterWindow.txtPassword2.setCaretColor(lightText);
        
        RegisterWindow.toggleSwitchLogin.setSelected(false);
        RegisterWindow.toggleSwitchLogin.setText("🌙");

        RegisterWindow.btn_Login.setStyle(ButtonCustom.ButtonStyle.PRIMARY);
        RegisterWindow.btn_Abbrechen.setStyle(ButtonCustom.ButtonStyle.PRIMARY);
    }

}
