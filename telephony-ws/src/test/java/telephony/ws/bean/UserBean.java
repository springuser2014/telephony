package telephony.ws.bean;


/**
 * asd.
 */
public class UserBean {

    private String username;
    private String password;

    /**
     * asd.
     * @param username asd.
     * @param password asd.
     */
    public UserBean(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * asd.
     * @return asd.
     */
    public String getPassword() {
        return password;
    }

    /**
     * asd.
     * @param password asd.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * asd.
     * @return asd.
     */
    public String getUsername() {
        return username;
    }

    /**
     * asd.
     * @param username asd.
     */
    public void setUsername(String username) {
        this.username = username;

    }
}