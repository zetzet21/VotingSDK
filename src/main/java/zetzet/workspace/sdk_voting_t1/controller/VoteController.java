package zetzet.workspace.sdk_voting_t1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import zetzet.workspace.sdk_voting_t1.dto.request.VoteDTORequest;
import zetzet.workspace.sdk_voting_t1.dto.response.VoteDTOResponse;
import zetzet.workspace.sdk_voting_t1.service.VoteService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/votes")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@PreAuthorize("isAuthenticated()")
public class VoteController {

    private final VoteService voteService;

    @Operation(summary = "Получить все голосования", description = "Возвращает информацию о всех голосованиях.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешно получена информация о голосованиях"),
                    @ApiResponse(responseCode = "400", description = "Неверные параметры запроса"),
                    @ApiResponse(responseCode = "401", description = "Не авторизован, пользователь не аутентифицирован"),
                    @ApiResponse(responseCode = "403", description = "Нет доступа")
            }
    )
    @GetMapping("/all")
    public List<VoteDTOResponse> getAllVotes() {
        return voteService.getAllVotes();
    }

    @Operation(summary = "Получить все голосования", description = "Возвращает информацию о всех голосованиях.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешно получена информация о голосованиях"),
                    @ApiResponse(responseCode = "400", description = "Неверные параметры запроса"),
                    @ApiResponse(responseCode = "401", description = "Не авторизован, пользователь не аутентифицирован"),
                    @ApiResponse(responseCode = "403", description = "Нет доступа")
            }
    )
    @GetMapping("/{voteId}")
    public VoteDTOResponse getVote(@PathVariable UUID voteId) {
        return voteService.getVote(voteId);
    }

    @Operation(summary = "Получить все голосования", description = "Возвращает информацию о всех голосованиях.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешно получена информация о голосованиях"),
                    @ApiResponse(responseCode = "400", description = "Неверные параметры запроса"),
                    @ApiResponse(responseCode = "401", description = "Не авторизован, пользователь не аутентифицирован"),
                    @ApiResponse(responseCode = "403", description = "Нет доступа")
            }
    )
    @PostMapping
    public ResponseEntity<VoteDTORequest> createVote(@RequestBody VoteDTORequest voteDTO) {
        return ResponseEntity.ok(voteService.createVote(voteDTO));
    }

    @Operation(summary = "Получить все голосования", description = "Возвращает информацию о всех голосованиях.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешно получена информация о голосованиях"),
                    @ApiResponse(responseCode = "400", description = "Неверные параметры запроса"),
                    @ApiResponse(responseCode = "401", description = "Не авторизован, пользователь не аутентифицирован"),
                    @ApiResponse(responseCode = "403", description = "Нет доступа")
            }
    )
    @PostMapping("/{voteId}/close")
    public ResponseEntity<Void> closeVote(@PathVariable UUID voteId) {
        voteService.closeVote(voteId);
        return ResponseEntity.ok().build();
    }
}
