package zetzet.workspace.sdk_voting_t1.dto.request;

import zetzet.workspace.sdk_voting_t1.entity.vote.VoteOptions;
import zetzet.workspace.sdk_voting_t1.entity.vote.VoteStatus;

import java.util.List;
import java.util.UUID;

public record VoteDTORequest(
        UUID id,
        String title,
        List<String> options,
        VoteStatus status
) {

}


