package zetzet.workspace.sdk_voting_t1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import zetzet.workspace.sdk_voting_t1.dto.response.UserInfoResponse;
import zetzet.workspace.sdk_voting_t1.entity.User;
import zetzet.workspace.sdk_voting_t1.mapper.UserMapper;
import zetzet.workspace.sdk_voting_t1.security.JwtService;
import zetzet.workspace.sdk_voting_t1.service.UserService;

@RestController
@RequestMapping("/api/user")
@SecurityRequirement(name = "Bearer Authentication")
public class UserController extends AbstractController {

    private final UserService userService;

    private final UserMapper mapper;

    public UserController(JwtService jwtService, UserService userService, UserMapper mapper) {
        super(jwtService);
        this.userService = userService;
        this.mapper = mapper;
    }

    @Operation(summary = "Получить все голосования", description = "Возвращает информацию о всех голосованиях.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешно получена информация о голосованиях"),
                    @ApiResponse(responseCode = "400", description = "Неверные параметры запроса"),
                    @ApiResponse(responseCode = "401", description = "Не авторизован, пользователь не аутентифицирован"),
                    @ApiResponse(responseCode = "403", description = "Нет доступа")
            }
    )
    @GetMapping()
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserInfoResponse> getUser(HttpServletRequest request) {
        String username = getUsername(request);
        User user = userService.getByUsername(username);

        return ResponseEntity.ok().body(mapper.toDto(user));
    }
}