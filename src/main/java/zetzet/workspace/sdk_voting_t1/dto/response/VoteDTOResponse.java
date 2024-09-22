package zetzet.workspace.sdk_voting_t1.dto.response;

import zetzet.workspace.sdk_voting_t1.entity.vote.VoteOptions;
import zetzet.workspace.sdk_voting_t1.entity.vote.VoteStatus;

import java.util.UUID;
import java.util.List;

public record VoteDTOResponse(
        UUID id,
        String title,
        List<VoteOptions> options,
        VoteStatus status
) {

}


