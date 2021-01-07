package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "follows")
@NamedQueries({
    @NamedQuery(
            name = "getFollowCount",
            query = "SELECT COUNT(f) FROM Follow AS f WHERE f.account = :account"
            )
})
@Entity
public class Follow {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @ManyToOne
    @JoinColumn(name = "follow", nullable = false)
    private Account follow;

    @Column(name = "followed_at", nullable = false)
    private Timestamp followed_at;

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

    public Account getFollow() {
        return follow;
    }

    public void setFollow(Account follow) {
        this.follow = follow;
    }

    public Timestamp getFollowed_at() {
        return followed_at;
    }

    public void setfollowed_at(Timestamp followed_at) {
        this.followed_at = followed_at;
    }
}
