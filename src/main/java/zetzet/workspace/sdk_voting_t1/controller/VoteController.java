package zetzet.workspace.sdk_voting_t1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zetzet.workspace.sdk_voting_t1.dto.request.VoteDTORequest;
import zetzet.workspace.sdk_voting_t1.dto.response.VoteDTOResponse;
import zetzet.workspace.sdk_voting_t1.service.VoteService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/votes")
@RequiredArgsConstructor
public class VoteController {

    private final VoteService voteService;

    // Получить все голосования
    @GetMapping
    public List<VoteDTOResponse> getAllVotes() {
        return voteService.getAllVotes();
    }

    // Создать новое голосование
    @PostMapping
    public ResponseEntity<VoteDTORequest> createVote(@RequestBody VoteDTORequest voteDTO) {
        return ResponseEntity.ok(voteService.createVote(voteDTO));
    }

    // Закрыть голосование
    @PostMapping("/{voteId}/close")
    public ResponseEntity<Void> closeVote(@PathVariable UUID voteId) {
        voteService.closeVote(voteId);
        return ResponseEntity.ok().build();
    }
}
