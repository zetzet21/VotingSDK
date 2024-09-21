package zetzet.workspace.sdk_voting_t1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zetzet.workspace.sdk_voting_t1.dto.vote.VoteDTO;
import zetzet.workspace.sdk_voting_t1.entity.vote.Vote;
import zetzet.workspace.sdk_voting_t1.entity.vote.VoteOptions;
import zetzet.workspace.sdk_voting_t1.entity.vote.VoteStatus;
import zetzet.workspace.sdk_voting_t1.mapper.VoteMapper;
import zetzet.workspace.sdk_voting_t1.repository.VoteOptionsRepository;
import zetzet.workspace.sdk_voting_t1.repository.VoteRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;

    private final VoteOptionsRepository voteOptionsRepository;

    private final VoteMapper mapper;

    // Метод для получения всех голосований
    public List<VoteDTO> getAllVotes() {
        return voteRepository.findAll()
                .stream()
                .map(vote -> new VoteDTO(vote.getId(), vote.getTitle(), vote.getOptions().stream().map(VoteOptions::getText).toList(), vote.getStatus()))
                .toList();
    }

    // Метод для создания нового голосования
    public VoteDTO createVote(VoteDTO voteDTO) {
        Vote vote = new Vote();
        vote.setTitle(voteDTO.title());
        vote.setStatus(VoteStatus.ACTIVE);
        voteRepository.save(vote);
        voteOptionsRepository.saveAll(mapper.toEntity(voteDTO.options()));
        return new VoteDTO(vote.getId(), vote.getTitle(), vote.getOptions().stream().map(VoteOptions::getText).toList(), vote.getStatus());
    }

    // Метод для закрытия голосования
    public void closeVote(UUID voteId) {
        Vote vote = voteRepository.findById(voteId).orElseThrow(() -> new RuntimeException("Vote not found"));
        vote.setStatus(VoteStatus.CLOSED);
        voteRepository.save(vote);
    }
}
