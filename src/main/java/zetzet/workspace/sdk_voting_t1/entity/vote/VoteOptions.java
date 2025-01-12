package zetzet.workspace.sdk_voting_t1.entity.vote;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import zetzet.workspace.sdk_voting_t1.entity.UserVote;
import zetzet.workspace.sdk_voting_t1.entity.UserVoteCSI;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "vote_options")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VoteOptions {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String text;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "vote_id")
    @JsonIgnore
    private Vote vote;

    @OneToMany(mappedBy = "voteOptions", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<UserVote> results;

    @OneToMany(mappedBy = "voteOptions", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<UserVoteCSI> resultsCSI;
}

