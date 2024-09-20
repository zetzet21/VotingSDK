package zetzet.workspace.sdk_voting_t1.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import zetzet.workspace.sdk_voting_t1.Model.User;
import zetzet.workspace.sdk_voting_t1.Repository.UserRepository;
import zetzet.workspace.sdk_voting_t1.Service.UserService;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody String jsonUser) throws JsonProcessingException{
        userService.registerUser(jsonUser);
        return ResponseEntity.ok().body("Registration successfully!");
    }


}
