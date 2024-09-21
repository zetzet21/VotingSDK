package zetzet.workspace.sdk_voting_t1.controller;

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
@RequiredArgsConstructor
public class UserController extends AbstractController {

    private final UserService userService;

    private final JwtService jwtService;


    @GetMapping("/{token}")
    public ResponseEntity<User> getUser(@PathVariable String token) {
        String username = getUsernameFromToken(token);
        User user = userService.getByUsername(username);

        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private String getUsernameFromToken(String token) {
        return jwtService.extractUsernameWithoutBearerPrefix(token);
    }
}