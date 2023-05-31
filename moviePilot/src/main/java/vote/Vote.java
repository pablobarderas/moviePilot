package vote;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.app.moviePilot.model.user.User;
import com.app.moviePilot.model.visualContent.VisualContent;

public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long voteId;
    private VoteType voteType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ContentId", referencedColumnName = "IdApi")
    private VisualContent visualContent;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "user_Id")
    private User user;

    
    public Vote(Long voteId, VoteType voteType, VisualContent visualContent, User user) {
        this.voteId = voteId;
        this.voteType = voteType;
        this.visualContent = visualContent;
        this.user = user;
    }


    public Vote() {
    }


    public Long getVoteId() {
        return voteId;
    }


    public void setVoteId(Long voteId) {
        this.voteId = voteId;
    }


    public VoteType getVoteType() {
        return voteType;
    }


    public void setVoteType(VoteType voteType) {
        this.voteType = voteType;
    }


    public VisualContent getVisualContent() {
        return visualContent;
    }


    public void setVisualContent(VisualContent visualContent) {
        this.visualContent = visualContent;
    }


    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }
    
}//end class