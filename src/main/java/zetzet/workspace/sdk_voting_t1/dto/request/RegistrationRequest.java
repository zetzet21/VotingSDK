package zetzet.workspace.sdk_voting_t1.dto.request;

import jakarta.validation.constraints.NotBlank;

public record RegistrationRequest(

        @NotBlank
        String surname,

        @NotBlank
        String firstName,

        @NotBlank
        String username,

        @NotBlank
        String password,

        @NotBlank
        String email,

        @NotBlank
        String gender
) {
}