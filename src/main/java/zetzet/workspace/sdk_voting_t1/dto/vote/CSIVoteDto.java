package zetzet.workspace.sdk_voting_t1.dto.vote;

import java.util.UUID;

public record CSIVoteDto (
        UUID voteId,
        UUID voteOptionsId,
        UUID userId,
        Integer userRating
) {
}
