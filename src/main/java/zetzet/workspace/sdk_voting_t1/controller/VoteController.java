package zetzet.workspace.sdk_voting_t1.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import zetzet.workspace.sdk_voting_t1.dto.vote.VoteCreateDTO;
import zetzet.workspace.sdk_voting_t1.dto.vote.VoteDTO;
import zetzet.workspace.sdk_voting_t1.dto.vote.VoteUpdateDTO;
import zetzet.workspace.sdk_voting_t1.service.VoteService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/votes")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class VoteController {

    private final VoteService voteService;

    @GetMapping
    @PreAuthorize("hasRole('ANALYST')")
    public List<VoteDTO> getAllVotes() {
        return voteService.getAllVotes();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ANALYST')")
    public ResponseEntity<VoteDTO> getVoteById(@PathVariable UUID id) {
        return ResponseEntity.ok(voteService.getVoteById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<VoteDTO> createVote(@RequestBody VoteCreateDTO voteCreateDTO) {
        return ResponseEntity.ok(voteService.createVote(voteCreateDTO));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<VoteDTO> updateVote(@PathVariable UUID id, @RequestBody VoteUpdateDTO voteUpdateDTO) {
        return ResponseEntity.ok(voteService.updateVote(id, voteUpdateDTO));
    }

    // Пользователь голосует за вариант в голосовании
    @PostMapping("/{voteId}/vote")
    public ResponseEntity<VoteDTO> castVote(@PathVariable UUID voteId,
                                            @RequestParam UUID userId,
                                            @RequestParam String selectedOption) {
        return ResponseEntity.ok(voteService.castVote(userId, voteId, selectedOption));
    }
}

