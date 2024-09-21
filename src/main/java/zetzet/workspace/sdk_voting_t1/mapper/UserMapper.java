package zetzet.workspace.sdk_voting_t1.mapper;

import org.springframework.stereotype.Component;
import zetzet.workspace.sdk_voting_t1.dto.request.RegistrationRequest;
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
}
