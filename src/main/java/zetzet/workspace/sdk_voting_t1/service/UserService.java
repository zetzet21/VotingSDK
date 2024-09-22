package zetzet.workspace.sdk_voting_t1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import zetzet.workspace.sdk_voting_t1.dto.response.UserInfoResponse;
import zetzet.workspace.sdk_voting_t1.entity.role.Role;
import zetzet.workspace.sdk_voting_t1.entity.User;
import zetzet.workspace.sdk_voting_t1.mapper.UserMapper;
import zetzet.workspace.sdk_voting_t1.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper mapper;

    public UserInfoResponse getByUsername(String username) throws UsernameNotFoundException {
        User user =  userRepository.findByUsername(username).
                orElseThrow(() -> new UsernameNotFoundException("User with login:" + username + "not found"));

        return mapper.toDto(user);
    }

    public void registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public List<String> getRolesByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with login:" + username + "not found"))
                .getRoles()
                .stream()
                .map(Role::getRole)
                .toList();
    }
}
