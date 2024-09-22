package zetzet.workspace.sdk_voting_t1.dto.vote;

public record VoteOptionWithCountAndPercent(
        String classification,

        Long count,

        Double percent
) {
}
