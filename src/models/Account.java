package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "accounts")
@NamedQueries({
    @NamedQuery(
            name = "getAllAccounts",
            query = "SELECT a FROM Account AS a ORDER BY a.id DESC"
            ),
    @NamedQuery(
            name = "checkId",
            query = "SELECT COUNT(a) FROM Account AS a WHERE a.code = :code"
            ),
    @NamedQuery(
            name = "checkLoginIdAndPassword",
            query = "SELECT a FROM Account AS a WHERE a.delete_flag = 0 AND a.code = :code AND a.password = :pass"
            ),
    @NamedQuery(
            name = "getMyAccount",
            query = "SELECT a FROM Account AS a WHERE a.delete_flag = 0 AND a.id = :id AND a.password = :pass"
            ),
    @NamedQuery(
            name = "getAccountSearch",
            query = "SELECT a FROM Account AS a WHERE (a.name LIKE :name OR a.code = :code OR a.profile LIKE :profile) ORDER BY a.id DESC"
            )
})
@Entity
public class Account {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "password", length = 64, nullable = false)
    private String password;

    @Lob
    @Column(name = "profile", nullable = true)
    private String profile;

    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updated_at;

    @Column(name = "delete_flag", nullable = false)
    private Integer delete_flag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public Integer getDelete_flag() {
        return delete_flag;
    }

    public void setDelete_flag(Integer delete_flag) {
        this.delete_flag = delete_flag;
    }


}
