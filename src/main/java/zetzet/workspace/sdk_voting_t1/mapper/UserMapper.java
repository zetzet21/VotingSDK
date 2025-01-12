package zetzet.workspace.sdk_voting_t1.mapper;

import org.springframework.stereotype.Component;

import zetzet.workspace.sdk_voting_t1.dto.request.RegistrationRequest;
import zetzet.workspace.sdk_voting_t1.dto.response.UserInfoResponse;
import zetzet.workspace.sdk_voting_t1.entity.User;

@Component
public class UserMapper {

    public User toEntity(RegistrationRequest registrationRequest) {
        if (registrationRequest == null) {
            return null;
        }

        User user = new User();
        user.setSurname(registrationRequest.surname());
        user.setFirstName(registrationRequest.firstName());
        user.setUsername(registrationRequest.username());
        user.setPassword(registrationRequest.password());
        user.setEmail(registrationRequest.email());
        user.setGender(registrationRequest.gender());
        return user;
    }

    public UserInfoResponse toDto(User user) {
        if (user == null) {
            return null;
        }

        return UserInfoResponse.builder()
                .firstName(user.getFirstName())
                .surname(user.getSurname())
                .username(user.getUsername())
                .email(user.getEmail())
                .gender(user.getGender())
                .build();
    }
}
