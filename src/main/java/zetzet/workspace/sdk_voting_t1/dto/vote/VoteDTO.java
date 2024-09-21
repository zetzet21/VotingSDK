package zetzet.workspace.sdk_voting_t1.dto.vote;

import java.time.LocalDateTime;
import java.util.UUID;

public record VoteDTO(
        UUID id,
        String title,
        String description,
        String status,
        LocalDateTime createdAt
) {}

