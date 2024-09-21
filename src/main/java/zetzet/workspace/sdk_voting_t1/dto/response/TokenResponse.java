package zetzet.workspace.sdk_voting_t1.dto.response;

import jakarta.validation.constraints.NotBlank;

public record TokenResponse (
        @NotBlank
        String token,

        @NotBlank
        String refreshToken
) {
}
