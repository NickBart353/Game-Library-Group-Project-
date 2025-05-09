import java.util.ArrayList;

public class LoginHandler {
    private ArrayList<String[]> users;
    private ArrayList<User> benutzer;

    //Konstruktor für die Klasse LoginHandler
    public LoginHandler(ArrayList<String[]> _users, ArrayList<User> _benutzer) {
        users = _users;
        benutzer = _benutzer;
    }

    //Methode zum überprüfen ob der User registriert ist
    public boolean IsUserRegistered(String username) {
        //Gehe jeden Benutzer durch
        for (String[] benutzer : users) {            
            //Hat ein Benutzer den Benutzernamen?
            if (benutzer[1].equals(username)) {
                //Ja? Dann darf er
                return true;
            }
        }

       // if(benutzer != null){
            for (User user : benutzer){
                if(user.getName().equals(username)){
                    return true;
                }
            }
       // }

        //Es gibt diesen User nicht
        return false;
    }

    //Methode zum überprüfen ob Passwort korrekt ist
    public boolean IsPasswordCorrect(String username, String Passwort) {
        //Gehe jeden Benutzer durch
        for (String[] benutzer : users) {
            //Passt das Passwort zu dem Benutzer?
            if (benutzer[1].equals(username) &&
                benutzer[2].equals(Passwort)) {
                //Ja? Dann darf er
                return true;
            }
        }

        //Passwort ist nicht korrekt
        return false;
    }

    //Methode um einen User einzuloggen
    public String LogUserIn(String username, String passwort) {
        //Gehe jeden Benutzer durch
        for (String[] benutzer : users) {
            //Hast du den User gefunden?
            if (benutzer[1].equals(username) &&
                benutzer[2].equals(passwort)) {
                //Ja? Dann gib ID zurück
                return benutzer[0];
            }
        }

        //Wenn du doch nix gefunden hast, dann nimm -1
        return "-1";
    }

    //Methode um den Username anhand der UserID rauszubekommen
    public String GetUsernameFromID(String ID) {
        //Gehe jeden Usr durch
        for (String[] user : users) {
            //Enthält er die UserID?
            if (user[0].equals(ID)) {
                //Gib Namen zurück
                return user[1];
            }
        }

        //Ansonsten Fehler
        return "No Username found";
    }

    //Methode um einen neuen Benutzer beim registrieren in die .csv-Datei zu speichern
    public void AddUser(String username, String password) {
        //Speichere dir die ID für den neunen User
        String ID = GameManager.GetNewID(EventHandler.users);

        //Füge den User auch dem Array hinzu
        EventHandler.users.add(new String[] {ID, username, password});

        //Direkt in der .csv speichern
        DataHandler.SetDataToTemp(EventHandler.games, users);
    }
}
