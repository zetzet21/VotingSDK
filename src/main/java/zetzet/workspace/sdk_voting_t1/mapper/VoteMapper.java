package zetzet.workspace.sdk_voting_t1.mapper;

import org.springframework.stereotype.Component;
import zetzet.workspace.sdk_voting_t1.dto.vote.VoteCreateDTO;
import zetzet.workspace.sdk_voting_t1.dto.vote.VoteDTO;
import zetzet.workspace.sdk_voting_t1.entity.Vote;
import zetzet.workspace.sdk_voting_t1.entity.VoteStatus;

@Component
public class VoteMapper {

    // Преобразовать Vote в VoteDTO
    public VoteDTO toDto(Vote vote) {
        return new VoteDTO(
                vote.getId(),
                vote.getTitle(),
                vote.getDescription(),
                vote.getStatus().name(),
                vote.getCreatedAt()
        );
    }

    // Преобразовать VoteCreateDTO в сущность Vote
    public Vote toEntity(VoteCreateDTO dto) {
        Vote vote = new Vote();
        vote.setTitle(dto.title());  // Обращение к полям record через вызов методов
        vote.setDescription(dto.description());
        vote.setStatus(VoteStatus.OPEN);  // По умолчанию голосование открыто
        return vote;
    }
}
