package zetzet.workspace.sdk_voting_t1.entity.vote;

import jakarta.persistence.*;
import lombok.*;

import zetzet.workspace.sdk_voting_t1.entity.UserVote;

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
    private Vote vote;

    @OneToMany(mappedBy = "voteOptions", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserVote> results;
}

