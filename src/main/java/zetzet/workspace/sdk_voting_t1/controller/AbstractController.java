package zetzet.workspace.sdk_voting_t1.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RestController;
import zetzet.workspace.sdk_voting_t1.security.JwtService;

import java.util.Objects;

@RequiredArgsConstructor
public abstract class AbstractController {

    private final JwtService jwtService;

    // Добавляем пустой конструктор
    protected AbstractController() {
        this.jwtService = null; // Или можно инициализировать как-то иначе, если нужно
    }


    protected String getUsername(HttpServletRequest request) {
        var authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        return Objects.requireNonNull(jwtService).extractUsernameWithoutBearerPrefix(authHeader);
    }
}
