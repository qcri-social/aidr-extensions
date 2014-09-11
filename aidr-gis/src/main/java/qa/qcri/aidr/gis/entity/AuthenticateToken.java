package qa.qcri.aidr.gis.entity;


import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "authenticate_token")
public class AuthenticateToken implements Serializable {

    private static final long serialVersionUID = 4720813686204397970L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 36, name = "token", unique = true)
    private String token;

    @Column(name = "status")
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
