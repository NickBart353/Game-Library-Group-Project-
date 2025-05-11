import java.util.ArrayList;

public class LoginHandler {
    private ArrayList<User> users;

    //Konstruktor für die Klasse LoginHandler
    public LoginHandler(ArrayList<User> _users) {
        users = _users;
    }

    //Methode zum überprüfen ob der User registriert ist
    public boolean IsUserRegistered(String username) {

        //Gehe jeden Benutzer durch
        for (User user : users){
            if(user.getName().equals(username)){
                return true;
            }
        }

        //Es gibt diesen User nicht
        return false;
    }

    //Methode zum überprüfen ob Passwort korrekt ist
    public boolean IsPasswordCorrect(String username, String Passwort) {

        //Gehe jeden Benutzer durch
        for (User user : users) {
            //Passt das Passwort zu dem Benutzer?
            if (user.getName().equals(username) &&
                    user.getPassword().equals(Passwort)) {
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
        for (User user : users) {
            //Hast du den User gefunden?
            if (user.getName().equals(username) &&
                    user.getPassword().equals(passwort)) {
                //Ja? Dann gib ID zurück
                return user.getId();
            }
        }

        //Wenn du doch nix gefunden hast, dann nimm -1
        return "-1";
    }

    //Methode um den Username anhand der UserID rauszubekommen
    public String GetUsernameFromID(String ID) {
        //Gehe jeden Usr durch
        for (User user : users) {
            //Enthält er die UserID?
            if (user.getId().equals(ID)) {
                //Gib Namen zurück
                return user.getName();
            }
        }

        //Ansonsten Fehler
        return "No Username found";
    }

    //Methode um einen neuen Benutzer beim registrieren in die .csv-Datei zu speichern
    public void AddUser(String username, String password) {

        //Füge den User auch dem Array hinzu
        EventHandler.users.add(new User(username,password,GetNewID(EventHandler.users)));

        //Direkt in der .csv speichern
        DataHandler.SetUserToTemp(EventHandler.users);
    }

    public static String GetNewID(ArrayList<User> users) {
        //Überprüfe ob die ArrayList leer ist
        if (!users.isEmpty()) {
            //Wenn nicht, bestimme die ID der letzten Zeile in der 0. Spalte
            String ID = users.getLast().getId();

            //Wandel sie um und erhöhe um 1
            Integer _ID = (Integer.parseInt(ID) + 1);
            return _ID.toString();
        } else {
            //Is die Liste leer, dann vergib die erste ID
            return "0";
        }
    }
}
