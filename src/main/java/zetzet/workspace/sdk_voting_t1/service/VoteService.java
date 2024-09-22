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
import java.util.stream.Collectors;

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
                .map(vote ->
                        new VoteDTO(vote.getId(),
                                vote.getTitle(),
                                vote.getOptions().stream().toList(),
                                vote.getStatus()))
                .toList();
    }

    // Метод для создания нового голосования
    public VoteDTO createVote(VoteDTO voteDTO) {
        // Создание нового голосования
        Vote vote = new Vote();
        vote.setTitle(voteDTO.title());
        vote.setStatus(VoteStatus.ACTIVE);

        List<VoteOptions> options = voteDTO.options().stream()
                .map(option -> {
                    VoteOptions voteOption = new VoteOptions();
                    voteOption.setText(option.getText());
                    voteOption.setVote(vote);
                    return voteOption;
                }).collect(Collectors.toList());

        vote.setOptions(options);

        Vote savedVote = voteRepository.save(vote);

        return new VoteDTO(savedVote
                .getId(), savedVote
                .getTitle(), options, savedVote
                .getStatus());
    }

    // Метод для закрытия голосования
    public void closeVote(UUID voteId) {
        Vote vote = voteRepository.findById(voteId).orElseThrow(() -> new RuntimeException("Vote not found"));
        vote.setStatus(VoteStatus.CLOSED);
        voteRepository.save(vote);
    }
}
