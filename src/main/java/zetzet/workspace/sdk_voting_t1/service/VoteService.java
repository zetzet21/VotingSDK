package zetzet.workspace.sdk_voting_t1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import zetzet.workspace.sdk_voting_t1.dto.vote.VoteCreateDTO;
import zetzet.workspace.sdk_voting_t1.dto.vote.VoteDTO;
import zetzet.workspace.sdk_voting_t1.dto.vote.VoteUpdateDTO;
import zetzet.workspace.sdk_voting_t1.entity.User;
import zetzet.workspace.sdk_voting_t1.entity.UserVote;
import zetzet.workspace.sdk_voting_t1.entity.Vote;
import zetzet.workspace.sdk_voting_t1.entity.VoteStatus;
import zetzet.workspace.sdk_voting_t1.mapper.VoteMapper;
import zetzet.workspace.sdk_voting_t1.repository.UserRepository;
import zetzet.workspace.sdk_voting_t1.repository.UserVoteRepository;
import zetzet.workspace.sdk_voting_t1.repository.VoteRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;

    private final UserService userService;

    private final UserVoteRepository userVoteRepository;

    private final VoteMapper voteMapper;

    // Метод для регистрации голоса пользователя
    public VoteDTO castVote(String username, UUID voteId, String selectedOption) throws UsernameNotFoundException {
        // Получаем голосование
        Vote vote = voteRepository.findById(voteId)
                .orElseThrow(() -> new RuntimeException("Vote not found"));

        User user = userService.getByUsername(username);

        // Проверяем, голосовал ли пользователь ранее
        UserVote userVote = userVoteRepository.findByUserIdAndVoteId(user.getId(), voteId)
                .orElseGet(() -> {
                    // Если пользователь не голосовал, создаём новую запись
                    UserVote newUserVote = new UserVote();
                    newUserVote.setUser(new User());  // Здесь предполагается, что у вас есть сущность User
                    newUserVote.setVote(vote);
                    return newUserVote;
                });

        // Устанавливаем новый выбранный вариант
        userVote.setSelectedOption(selectedOption);
        userVoteRepository.save(userVote);  // Сохраняем в БД

        // Возвращаем актуализированные данные голосования (опционально)
        return voteMapper.toDto(vote);
    }


    public List<VoteDTO> getAllVotes() {
        return voteRepository.findAll()
                .stream()
                .map(voteMapper::toDto)
                .collect(Collectors.toList());
    }

    public VoteDTO getVoteById(UUID id) {
        return voteMapper.toDto(voteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vote not found")));
    }

    public VoteDTO createVote(VoteCreateDTO voteCreateDTO) {
        Vote vote = voteMapper.toEntity(voteCreateDTO);
        return voteMapper.toDto(voteRepository.save(vote));
    }

    public VoteDTO updateVote(UUID id, VoteUpdateDTO voteUpdateDTO) {
        Vote vote = voteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vote not found"));
        vote.setStatus(VoteStatus.valueOf(voteUpdateDTO.status()));
        return voteMapper.toDto(voteRepository.save(vote));
    }
}


