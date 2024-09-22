package zetzet.workspace.sdk_voting_t1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zetzet.workspace.sdk_voting_t1.dto.request.VoteDTORequest;
import zetzet.workspace.sdk_voting_t1.dto.response.VoteDTOResponse;
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
    public List<VoteDTOResponse> getAllVotes() {
        return voteRepository.findAll()
                .stream()
                .map(vote ->
                        new VoteDTOResponse(vote.getId(),
                                vote.getTitle(),
                                vote.getOptions().stream().toList(),
                                vote.getStatus()))
                .toList();
    }

    // Метод для создания нового голосования
    public VoteDTORequest createVote(VoteDTORequest voteDTO) {
        // Создание нового голосования
        Vote vote = new Vote();
        vote.setTitle(voteDTO.title());
        vote.setStatus(VoteStatus.ACTIVE);

        List<VoteOptions> options = voteDTO.options().stream()
                .map(optionText -> {
                    VoteOptions voteOption = new VoteOptions();
                    voteOption.setText(optionText);
                    voteOption.setVote(vote);
                    return voteOption;
                }).collect(Collectors.toList());

        vote.setOptions(options);

        Vote savedVote = voteRepository.save(vote);

        return new VoteDTORequest(savedVote
                .getId(), savedVote
                .getTitle(), savedVote
                .getOptions()
                .stream()
                .map(VoteOptions::getText)
                .toList(), savedVote
                .getStatus());
    }

    // Метод для закрытия голосования
    public void closeVote(UUID voteId) {
        Vote vote = voteRepository.findById(voteId).orElseThrow(() -> new RuntimeException("Vote not found"));
        vote.setStatus(VoteStatus.CLOSED);
        voteRepository.save(vote);
    }
}
