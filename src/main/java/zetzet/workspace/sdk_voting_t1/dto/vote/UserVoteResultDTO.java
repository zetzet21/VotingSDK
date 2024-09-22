package zetzet.workspace.sdk_voting_t1.dto.vote;

import java.util.List;
import java.util.UUID;

public record UserVoteResultDTO (
        UUID voteId,

        String option,

        List<VoteOptionWithCountAndPercent> voteOptionWithCountAndPercent
) {
}

