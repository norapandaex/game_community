package models;

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

@Table(name = "favorites")
@NamedQueries({
    @NamedQuery(
            name = "checkFav",
            query = "SELECT f FROM Favorite AS f WHERE f.account = :account"
            ),
    @NamedQuery(
            name = "getFavoriteCount",
            query = "SELECT COUNT(f) FROM Favorite AS f WHERE f.account = :account"
            )
})
@Entity
public class Favorite {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @ManyToOne
    @JoinColumn(name = "ac_id", nullable = true)
    private AccountContribution accountcontribution;

    @ManyToOne
    @JoinColumn(name = "cc_id", nullable = true)
    private CommunityContribution communitycontribution;

    @ManyToOne
    @JoinColumn(name = "ar_id", nullable = true)
    private AccountReply accountreply;

    @ManyToOne
    @JoinColumn(name = "cr_id", nullable = true)
    private CommunityReply communityreply;


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

    public AccountReply getAccountreply() {
        return accountreply;
    }

    public void setAccountreply(AccountReply accountreply) {
        this.accountreply = accountreply;
    }

    public AccountContribution getAccountcontribution() {
        return accountcontribution;
    }

    public void setAccountcontribution(AccountContribution accountcontribution) {
        this.accountcontribution = accountcontribution;
    }

    public CommunityContribution getCommunitycontribution() {
        return communitycontribution;
    }

    public void setCommunitycontribution(CommunityContribution communitycontribution) {
        this.communitycontribution = communitycontribution;
    }

    public CommunityReply getCommunityreply() {
        return communityreply;
    }

    public void setCommunityreply(CommunityReply communityreply) {
        this.communityreply = communityreply;
    }



}
