package zetzet.workspace.sdk_voting_t1.mapper;

import org.springframework.stereotype.Component;

import zetzet.workspace.sdk_voting_t1.entity.vote.VoteOptions;
import zetzet.workspace.sdk_voting_t1.entity.vote.VoteStatus;

import java.util.ArrayList;
import java.util.List;

@Component
public class VoteMapper {
    public List<VoteOptions> toEntity(List<String> optionsTexts) {
        List<VoteOptions> voteOptions = new ArrayList<>();

        for (var optionText : optionsTexts) {
            voteOptions.add(VoteOptions.builder().text(optionText).build());
        }

        return voteOptions;
    }
}
