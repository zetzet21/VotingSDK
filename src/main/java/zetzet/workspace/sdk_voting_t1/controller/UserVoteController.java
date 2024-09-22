package zetzet.workspace.sdk_voting_t1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import zetzet.workspace.sdk_voting_t1.dto.vote.CSIVoteDto;
import zetzet.workspace.sdk_voting_t1.dto.vote.KanoVoteDTO;
import zetzet.workspace.sdk_voting_t1.dto.vote.UserVoteCSIResultDTO;
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

    @Operation(summary = "Получить все голосования", description = "Возвращает информацию о всех голосованиях.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешно получена информация о голосованиях"),
                    @ApiResponse(responseCode = "400", description = "Неверные параметры запроса"),
                    @ApiResponse(responseCode = "401", description = "Не авторизован, пользователь не аутентифицирован"),
                    @ApiResponse(responseCode = "403", description = "Нет доступа")
            }
    )
    @PostMapping("/kano")
    public ResponseEntity<Void> castVote(@RequestBody KanoVoteDTO kanoVoteDTO) {
        userVoteService.castVote(kanoVoteDTO);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Получить все голосования", description = "Возвращает информацию о всех голосованиях.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешно получена информация о голосованиях"),
                    @ApiResponse(responseCode = "400", description = "Неверные параметры запроса"),
                    @ApiResponse(responseCode = "401", description = "Не авторизован, пользователь не аутентифицирован"),
                    @ApiResponse(responseCode = "403", description = "Нет доступа")
            }
    )
    @PostMapping("/csi")
    public ResponseEntity<Void> castVote(@RequestBody CSIVoteDto csiVoteDto) {
        userVoteService.castVote(csiVoteDto);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Получить все голосования", description = "Возвращает информацию о всех голосованиях.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешно получена информация о голосованиях"),
                    @ApiResponse(responseCode = "400", description = "Неверные параметры запроса"),
                    @ApiResponse(responseCode = "401", description = "Не авторизован, пользователь не аутентифицирован"),
                    @ApiResponse(responseCode = "403", description = "Нет доступа")
            }
    )
    @GetMapping("/results/kano/{voteId}")
    public List<UserVoteResultDTO> getKanoResults(@PathVariable UUID voteId) {
        return userVoteService.processKanoResults(voteId);
    }

    @Operation(summary = "Получить все голосования", description = "Возвращает информацию о всех голосованиях.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешно получена информация о голосованиях"),
                    @ApiResponse(responseCode = "400", description = "Неверные параметры запроса"),
                    @ApiResponse(responseCode = "401", description = "Не авторизован, пользователь не аутентифицирован"),
                    @ApiResponse(responseCode = "403", description = "Нет доступа")
            }
    )
    @GetMapping("/results/csi/{voteId}")
    public List<UserVoteCSIResultDTO> getCSIResults(@PathVariable UUID voteId) {
        return userVoteService.processCSIResults(voteId);
    }
}

