package zetzet.workspace.sdk_voting_t1.dto.response;

import lombok.Builder;

@Builder
public record UserInfoResponse(
        String surname,
        String firstName,
        String gender,
        String username,
        String email
) {
}
