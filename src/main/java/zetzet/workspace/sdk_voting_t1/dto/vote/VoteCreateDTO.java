package zetzet.workspace.sdk_voting_t1.dto.vote;

import java.util.List;

public record VoteCreateDTO(
        String title,
        String description,
        List<String> options
) {}
