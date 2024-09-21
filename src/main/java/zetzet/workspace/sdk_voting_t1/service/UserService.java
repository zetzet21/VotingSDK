package zetzet.workspace.sdk_voting_t1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import zetzet.workspace.sdk_voting_t1.entity.Role;
import zetzet.workspace.sdk_voting_t1.entity.User;
import zetzet.workspace.sdk_voting_t1.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public User getByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).
                orElseThrow(() -> new UsernameNotFoundException("User with login:" + username + "not found"));
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
