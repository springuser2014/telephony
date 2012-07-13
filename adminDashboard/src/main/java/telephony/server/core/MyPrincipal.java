package telephony.server.core;


import java.util.HashSet;
import java.util.Set;

public class MyPrincipal {

    private Set<String> roles = new HashSet<String>();

    private String username;
    private String password;

    public MyPrincipal() {
        username = "pawel";
        password  = "pawel123";

        roles.add("admin");
        roles.add("user");
    }

    public Set<String> getRoles() {
        return roles;
    }

    public Object getUsername() {
        return username;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String p) {
        this.password = p;
    }
}
