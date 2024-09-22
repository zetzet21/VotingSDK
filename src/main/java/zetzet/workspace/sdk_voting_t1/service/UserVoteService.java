package zetzet.workspace.sdk_voting_t1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import zetzet.workspace.sdk_voting_t1.dto.vote.*;
import zetzet.workspace.sdk_voting_t1.entity.User;
import zetzet.workspace.sdk_voting_t1.entity.UserVote;
import zetzet.workspace.sdk_voting_t1.entity.UserVoteCSI;
import zetzet.workspace.sdk_voting_t1.entity.vote.Vote;
import zetzet.workspace.sdk_voting_t1.kano.KanoClassification;
import zetzet.workspace.sdk_voting_t1.repository.*;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserVoteService {

    private final UserVoteRepository userVoteRepository;

    private final UserVoteCSIRepository userVoteCSIRepository;

    private final VoteOptionsRepository voteOptionsRepository;

    private final VoteRepository voteRepository;

    private final KanoClassification kanoClassification;

    private final UserRepository userRepository;

    // Метод для записи голоса пользователя
    public void castVote(KanoVoteDTO kanoVoteDTO) {
        UUID userId = userRepository.findByUsername(kanoVoteDTO.username()).get().getId();

        UserVote userVote = userVoteRepository.findByUserIdAndVoteOptionsId(userId, kanoVoteDTO.voteId())
                .orElseGet(() -> {
                    UserVote newUserVote = new UserVote();
                    newUserVote.setUser(new User(userId));
                    newUserVote.setVoteOptions(voteOptionsRepository.findById(kanoVoteDTO.voteOptionsId())
                            .orElseThrow(() -> new RuntimeException("Vote not found")));
                    return newUserVote;
                });

        userVote.setPositiveResponse(kanoVoteDTO.positiveResponse());
        userVote.setNegativeResponse(kanoVoteDTO.negativeResponse());
        userVoteRepository.save(userVote);
    }

    public void castVote(CSIVoteDto csiVoteDto) {
        UUID userId = userRepository.findByUsername(csiVoteDto.username()).get().getId();

        UserVoteCSI userVote = userVoteCSIRepository.findByUserIdAndVoteOptionsId(userId, csiVoteDto.voteId())
                .orElseGet(() -> {
                    UserVoteCSI newUserVote = new UserVoteCSI();
                    newUserVote.setUser(new User(userId));
                    newUserVote.setVoteOptions(voteOptionsRepository.findById(csiVoteDto.voteOptionsId())
                            .orElseThrow(() -> new RuntimeException("Vote not found")));
                    return newUserVote;
                });

        userVote.setUserRating(csiVoteDto.userRating());
        userVoteCSIRepository.save(userVote);
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

        DecimalFormat decimalFormat = new DecimalFormat("#,##");

        return results.entrySet()
                .stream()
                .map(entry -> new UserVoteResultDTO(
                        entry.getKey(),
                        entry.getValue()
                                .entrySet()
                                .stream()
                                .map(x -> new VoteOptionWithCountAndPercent(
                                        x.getKey(),
                                        x.getValue(),
                                        Double.valueOf(decimalFormat.format((double) 100 * x.getValue() / countUserVotes.get(entry.getKey()))))
                                )
                                .toList())
                )
                .toList();
    }

    public List<UserVoteCSIResultDTO> processCSIResults(UUID voteId) {
        Vote vote = voteRepository.findById(voteId).orElseThrow();

        List<UserVoteCSI> userVotes = new ArrayList<>();

        for (var option : vote.getOptions()) {
            userVotes.addAll(option.getResultsCSI());
        }

        Map<String, Long> classificationWithCount = new HashMap<>();
        Map<String, Long> classificationWithSum = new HashMap<>();
        for (var userVote : userVotes){

            var optionsText = userVote.getVoteOptions().getText();

            if (!classificationWithCount.containsKey(optionsText)){
                classificationWithCount.put(optionsText, 0L);
                classificationWithSum.put(optionsText, 0L);
            }

            classificationWithCount.put(
                    optionsText,
                    classificationWithCount.get(optionsText) + 1
            );

            classificationWithSum.put(
                    optionsText,
                    classificationWithSum.get(optionsText) + userVote.getUserRating()
            );
        }

        Map<String, Double> result = new HashMap<>();

        for (var key : classificationWithCount.keySet()) {
            result.put(
                    key,
                    (double) classificationWithSum.get(key) / classificationWithCount.get(key) * 100
            );
        }

        return result.entrySet()
                .stream()
                .map(x -> new UserVoteCSIResultDTO(x.getKey(), x.getValue()))
                .toList();
    }
}

