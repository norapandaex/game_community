package models;

import java.sql.Blob;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Table(name = "communitycontributions")
@NamedQueries({
    @NamedQuery(
            name = "getAllContributions",
            query = "SELECT cc FROM CommunityContribution AS cc WHERE cc.community = :c ORDER BY cc.id DESC"
            ),
    @NamedQuery(
            name = "getContributionsCount",
            query = "SELECT COUNT(cc) FROM CommunityContribution AS cc WHERE cc.community = :c"
            )
})
@Entity
public class CommunityContribution {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @ManyToOne
    @JoinColumn(name = "community_id", nullable = false)
    private Community community;

    @Lob
    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "image", nullable = true)
    private Blob image;

    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;

    @Column(name = "delete_flag", nullable = false)
    private Integer delete_flag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Integer getDelete_flag() {
        return delete_flag;
    }

    public void setDelete_flag(Integer delete_flag) {
        this.delete_flag = delete_flag;
    }
}
