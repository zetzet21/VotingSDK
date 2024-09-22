package zetzet.workspace.sdk_voting_t1.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zetzet.workspace.sdk_voting_t1.entity.User;
import zetzet.workspace.sdk_voting_t1.security.JwtService;
import zetzet.workspace.sdk_voting_t1.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController extends AbstractController {

    private final UserService userService;

    public UserController(JwtService jwtService, UserService userService) {
        super(jwtService);
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<User> getUser(HttpServletRequest request) {
        String username = getUsername(request);
        User user = userService.getByUsername(username);

        return ResponseEntity.ok().body(user);
    }
}