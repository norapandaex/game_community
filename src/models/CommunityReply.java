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

@Table(name = "communityreplies")
@NamedQueries({
    @NamedQuery(
            name = "getAllCommunityReply",
            query = "SELECT cr FROM CommunityReply AS cr WHERE cr.communitycontribution = :cc ORDER BY cr.id"
            ),
    @NamedQuery(
            name = "getCommunityReplyCount",
            query = "SELECT COUNT(cr) FROM CommunityReply AS cr WHERE cr.communitycontribution = :cc"
            )
})
@Entity
public class CommunityReply {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @ManyToOne
    @JoinColumn(name = "to_account_id", nullable = false)
    private Account to_account;

    @ManyToOne
    @JoinColumn(name = "cc_id", nullable = false)
    private CommunityContribution communitycontribution;

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

    public Account getTo_account() {
        return to_account;
    }

    public void setTo_account(Account to_account) {
        this.to_account = to_account;
    }

    public CommunityContribution getCommunitycontribution() {
        return communitycontribution;
    }

    public void setCommunitycontribution(CommunityContribution communitycontribution) {
        this.communitycontribution = communitycontribution;
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
