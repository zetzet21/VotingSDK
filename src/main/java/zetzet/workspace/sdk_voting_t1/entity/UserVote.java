package zetzet.workspace.sdk_voting_t1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "user_vote")
public class UserVote {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;  // Пользователь, который голосует

    @ManyToOne
    @JoinColumn(name = "vote_id")
    private Vote vote;  // Голосование, к которому относится голос

    private String selectedOption;  // Вариант, выбранный пользователем

}
