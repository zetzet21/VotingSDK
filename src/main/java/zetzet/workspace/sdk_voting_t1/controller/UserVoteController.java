package zetzet.workspace.sdk_voting_t1.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import zetzet.workspace.sdk_voting_t1.dto.vote.KanoVoteDTO;
import zetzet.workspace.sdk_voting_t1.dto.vote.UserVoteResultDTO;
import zetzet.workspace.sdk_voting_t1.service.UserVoteService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/uservotes")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@PreAuthorize("isAuthenticated()")
public class UserVoteController {

    private final UserVoteService userVoteService;

    // Записать голос пользователя
    @PostMapping
    public ResponseEntity<Void> castVote(@RequestBody KanoVoteDTO kanoVoteDTO) {
        userVoteService.castVote(kanoVoteDTO);
        return ResponseEntity.ok().build();
    }

    // Получить результаты голосования по методу Кано
    @GetMapping("/results/{voteId}")
    public List<UserVoteResultDTO> getKanoResults(@PathVariable UUID voteId) {
        return userVoteService.processKanoResults(voteId);
    }
}

