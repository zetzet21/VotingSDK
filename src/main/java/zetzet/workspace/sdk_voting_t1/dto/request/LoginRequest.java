package zetzet.workspace.sdk_voting_t1.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank
        String username,

        @NotBlank
        String password
) {
}