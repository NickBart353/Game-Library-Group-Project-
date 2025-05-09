public class User {

    private String name;
    private String pw;
    private static int IDCOUNTER=0;
    private int id;

    public User(String name, String pw,int tempId){

        this.name = name;
        this.pw = pw;
        if(tempId==-1){
            this.id=IDCOUNTER;
        } else{
            this.id = tempId;
        }
        IDCOUNTER++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
