package zetzet.workspace.sdk_voting_t1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import zetzet.workspace.sdk_voting_t1.dto.vote.KanoVoteDTO;
import zetzet.workspace.sdk_voting_t1.dto.vote.UserVoteResultDTO;
import zetzet.workspace.sdk_voting_t1.dto.vote.VoteOptionWithCountAndPercent;
import zetzet.workspace.sdk_voting_t1.entity.User;
import zetzet.workspace.sdk_voting_t1.entity.UserVote;
import zetzet.workspace.sdk_voting_t1.entity.vote.Vote;
import zetzet.workspace.sdk_voting_t1.kano.KanoClassification;
import zetzet.workspace.sdk_voting_t1.repository.UserVoteRepository;
import zetzet.workspace.sdk_voting_t1.repository.VoteOptionsRepository;
import zetzet.workspace.sdk_voting_t1.repository.VoteRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserVoteService {

    private final UserVoteRepository userVoteRepository;

    private final VoteOptionsRepository voteOptionsRepository;

    private final VoteRepository voteRepository;

    private final KanoClassification kanoClassification;

    // Метод для записи голоса пользователя
    public void castVote(KanoVoteDTO kanoVoteDTO) {
        UserVote userVote = userVoteRepository.findByUserIdAndVoteOptionsId(kanoVoteDTO.userId(), kanoVoteDTO.voteId())
                .orElseGet(() -> {
                    UserVote newUserVote = new UserVote();
                    newUserVote.setUser(new User(kanoVoteDTO.userId()));
                    newUserVote.setVoteOptions(voteOptionsRepository.findById(kanoVoteDTO.voteOptionsId())
                            .orElseThrow(() -> new RuntimeException("Vote not found")));
                    return newUserVote;
                });

        userVote.setPositiveResponse(kanoVoteDTO.positiveResponse());
        userVote.setNegativeResponse(kanoVoteDTO.negativeResponse());
        userVoteRepository.save(userVote);
    }

    // Метод для обработки результатов по методу Кано
    public List<UserVoteResultDTO> processKanoResults(UUID voteId) {
        Vote vote = voteRepository.findById(voteId).orElseThrow();

        List<UserVote> userVotes = new ArrayList<>();
        Map<String, Integer> countUserVotes = new HashMap<>();

        for (var option : vote.getOptions()) {
            userVotes.addAll(option.getResults());
            countUserVotes.put(option.getText(), option.getResults().size());
        }

        Map<String, Map<String, Long>> results = userVotes.stream()
                .collect(Collectors.groupingBy(
                        v -> v.getVoteOptions().getText(),
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> {
                                    Map<String, Long> classificationWithCount = new HashMap<>();
                                    for (var userVote : list){
                                        String classification = kanoClassification.classify(
                                                userVote.getPositiveResponse(),
                                                userVote.getNegativeResponse()
                                        );

                                        if (!classificationWithCount.containsKey(classification)){
                                            classificationWithCount.put(classification, 0L);
                                        }

                                        classificationWithCount.put(
                                                classification,
                                                classificationWithCount.get(classification) + 1
                                        );
                                    }

                                    return classificationWithCount;
                                }
                        )
        ));



        return results.entrySet()
                .stream()
                .map(entry -> new UserVoteResultDTO(
                        voteId,
                        entry.getKey(),
                        entry.getValue()
                                .entrySet()
                                .stream()
                                .map(x -> new VoteOptionWithCountAndPercent(
                                        x.getKey(),
                                        x.getValue(),
                                        (double) (100 * x.getValue() / countUserVotes.get(entry.getKey()))))
                                .toList())
                )
                .toList();
    }
}

