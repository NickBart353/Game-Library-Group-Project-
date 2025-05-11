public class Game {

    private static Integer IDCOUNTER=0;
    private final String id;
    private String name;
    private String kategorie;
    private String exePath;
    private String groesse;
    private String user;

    public Game(String tempName, String tempGroesse, String tempKat, String tempPath, String tempUser, String tempID){
        name = tempName;
        groesse = tempGroesse;
        kategorie = tempKat;
        exePath = tempPath;
        user = tempUser;

        if (tempID.equals("-1")){
            id = IDCOUNTER.toString();
        } else{
            id = tempID;
        }
        IDCOUNTER++;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKategorie() {
        return kategorie;
    }

    public void setKategorie(String kategorie) {
        this.kategorie = kategorie;
    }

    public String getExePath() {
        return exePath;
    }

    public void setExePath(String exePath) {
        this.exePath = exePath;
    }

    public String getGroesse() {
        return groesse;
    }

    public void setGroesse(String groesse) {
        this.groesse = groesse;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
