package zetzet.workspace.sdk_voting_t1.dto.vote;

import zetzet.workspace.sdk_voting_t1.entity.vote.VoteStatus;

import java.util.UUID;
import java.util.List;

public record VoteDTO(
        UUID id,
        String title,
        List<String> options,
        VoteStatus status
) {

}


