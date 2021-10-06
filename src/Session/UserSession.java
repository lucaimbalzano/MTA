package Session;

public class UserSession {

    private String email;
    private String password;
    private int privilege;
    public static UserSession session = null;

    private UserSession(String email, String password, int privilege) {
        this.email = email;
        this.password = password;
        this.privilege = privilege;
    }

    private UserSession(){}

    public static UserSession getInstanceUserSession(){
        if(session == null)
            return new UserSession();
        return session;
    }

    public static UserSession getInstanceUserSession(String email, String password, int privilege){
        if(session == null)
            return new UserSession(email, password, privilege);
        return session;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPrivilege() {
        return privilege;
    }

    public void setPrivilege(int privilege) {
        this.privilege = privilege;
    }


}
