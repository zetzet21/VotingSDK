package zetzet.workspace.sdk_voting_t1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "vote_options")
@Getter
@Setter
public class VoteOptions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(nullable = false)
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vote_id")
    private Vote vote;

    @OneToMany(mappedBy = "voteOption", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VoteResult> results;
}
