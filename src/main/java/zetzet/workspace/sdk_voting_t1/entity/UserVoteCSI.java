package zetzet.workspace.sdk_voting_t1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import zetzet.workspace.sdk_voting_t1.entity.vote.VoteOptions;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "user_vote_csi")
public class UserVoteCSI {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // Пользователь, который голосует
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Голосование, к которому относится голос
    @ManyToOne
    @JoinColumn(name = "vote_options_id")
    private VoteOptions voteOptions;

    @Column(name = "user_rating")
    private Integer userRating;
}
