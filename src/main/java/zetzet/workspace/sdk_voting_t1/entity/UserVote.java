package zetzet.workspace.sdk_voting_t1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import zetzet.workspace.sdk_voting_t1.entity.vote.Vote;
import zetzet.workspace.sdk_voting_t1.entity.vote.VoteOptions;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "user_vote")
public class UserVote {

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

    // Ответ на вопрос: как вы бы себя чувствовали, если функция будет добавлена
    private String positiveResponse;
    // Ответ на вопрос: как вы бы себя чувствовали, если функция НЕ будет добавлена
    private String negativeResponse;
}

