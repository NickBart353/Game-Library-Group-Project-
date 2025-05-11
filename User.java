public class User {

    private final String name;
    private final String password;
    private final String id;

    public User(String name, String pw,String id){
        this.name = name;
        this.password = pw;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getId() {
        return id;
    }
}
