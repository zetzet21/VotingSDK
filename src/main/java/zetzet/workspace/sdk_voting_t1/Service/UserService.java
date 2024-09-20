package zetzet.workspace.sdk_voting_t1.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import zetzet.workspace.sdk_voting_t1.Model.User;
import zetzet.workspace.sdk_voting_t1.Repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(String jsonUser) throws JsonProcessingException {
        User user = jsonStringToUser(jsonUser);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    // Преобразование json в объект User
    public User jsonStringToUser(String jsonUser) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(jsonUser, User.class);
        return user;
    }
}
