package zetzet.workspace.sdk_voting_t1.dto.vote;

import java.util.List;

public record UserVoteResultDTO (

        String option,

        List<VoteOptionWithCountAndPercent> voteOptionWithCountAndPercent
) {
}

