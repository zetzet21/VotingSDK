package zetzet.workspace.sdk_voting_t1.entity.vote;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "votes")
@Getter
@Setter
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // Название голосования
    private String title;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<VoteOptions> options;

    // Сущность может содержать различные статусы (активно/закрыто или другое)
    @Enumerated(EnumType.STRING)
    private VoteStatus status;
}

