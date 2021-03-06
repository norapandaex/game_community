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


@Table(name = "communities")
@NamedQueries({
    @NamedQuery(
            name = "getAllCommunities",
            query = "SELECT c FROM Community AS c ORDER BY c.id DESC"
            ),
    @NamedQuery(
            name = "getCommunityId",
            query = "SELECT c FROM Community AS c WHERE c.id = :cid"
            ),
    @NamedQuery(
            name = "getCommunityName",
            query = "SELECT c.name FROM Community AS c WHERE c.id = :cid"
            ),
    @NamedQuery(
            name = "getSearch",
            query = "SELECT c FROM Community AS c WHERE (c.name LIKE :skeyword OR c.game LIKE :skeyword) ORDER BY c.id DESC"
            )
})
@Entity
public class Community {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Lob
    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "game", nullable = false)
    private String game;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
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
