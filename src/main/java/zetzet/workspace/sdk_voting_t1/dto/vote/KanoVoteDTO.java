package zetzet.workspace.sdk_voting_t1.dto.vote;

import java.util.UUID;

public record KanoVoteDTO(
        UUID voteId,
        UUID voteOptionsId,
        String username,
        String positiveResponse,
        String negativeResponse
) {
}

