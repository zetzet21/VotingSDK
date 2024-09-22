package zetzet.workspace.sdk_voting_t1.dto.vote;

public record VoteOptionWithCount(
        String classification,

        Long count
) {
}
