package war.server.core.entity;




import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class User extends BaseEntity implements Serializable {

    @Column(name = "username")
    private String username;

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {

        StringBuffer sb = new StringBuffer();
        sb.append("id : ");
        sb.append(getId());
        sb.append("version : ");
        sb.append(getVersion());
        sb.append(" ; ");
        sb.append("username : ");
        sb.append(getUsername());
        sb.append(" \n");

        return sb.toString();
    }
}

