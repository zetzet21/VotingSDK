package zetzet.workspace.sdk_voting_t1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zetzet.workspace.sdk_voting_t1.dto.vote.VoteCreateDTO;
import zetzet.workspace.sdk_voting_t1.dto.vote.VoteDTO;
import zetzet.workspace.sdk_voting_t1.dto.vote.VoteUpdateDTO;
import zetzet.workspace.sdk_voting_t1.entity.Vote;
import zetzet.workspace.sdk_voting_t1.entity.VoteStatus;
import zetzet.workspace.sdk_voting_t1.mapper.VoteMapper;
import zetzet.workspace.sdk_voting_t1.repository.VoteRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private VoteMapper voteMapper;

    public List<VoteDTO> getAllVotes() {
        return voteRepository.findAll()
                .stream()
                .map(voteMapper::toDto)
                .collect(Collectors.toList());
    }

    public VoteDTO getVoteById(UUID id) {
        return voteMapper.toDto(voteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vote not found")));
    }

    public VoteDTO createVote(VoteCreateDTO voteCreateDTO) {
        Vote vote = voteMapper.toEntity(voteCreateDTO);
        return voteMapper.toDto(voteRepository.save(vote));
    }

    public VoteDTO updateVote(UUID id, VoteUpdateDTO voteUpdateDTO) {
        Vote vote = voteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vote not found"));
        vote.setStatus(VoteStatus.valueOf(voteUpdateDTO.status()));
        return voteMapper.toDto(voteRepository.save(vote));
    }
}


