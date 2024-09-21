package zetzet.workspace.sdk_voting_t1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zetzet.workspace.sdk_voting_t1.dto.vote.VoteCreateDTO;
import zetzet.workspace.sdk_voting_t1.dto.vote.VoteDTO;
import zetzet.workspace.sdk_voting_t1.dto.vote.VoteUpdateDTO;
import zetzet.workspace.sdk_voting_t1.service.VoteService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/votes")
public class VoteController {

    @Autowired
    private VoteService voteService;

    // Получить список всех голосований
    @GetMapping
    public List<VoteDTO> getAllVotes() {
        return voteService.getAllVotes();
    }

    // Получить голосование по ID
    @GetMapping("/{id}")
    public ResponseEntity<VoteDTO> getVoteById(@PathVariable UUID id) {
        return ResponseEntity.ok(voteService.getVoteById(id));
    }

    // Создать новое голосование (только администратор)
    @PostMapping
    public ResponseEntity<VoteDTO> createVote(@RequestBody VoteCreateDTO voteCreateDTO) {
        return ResponseEntity.ok(voteService.createVote(voteCreateDTO));
    }

    // Обновить голосование (закрыть/открыть)
    @PutMapping("/{id}")
    public ResponseEntity<VoteDTO> updateVote(@PathVariable UUID id, @RequestBody VoteUpdateDTO voteUpdateDTO) {
        return ResponseEntity.ok(voteService.updateVote(id, voteUpdateDTO));
    }
}

