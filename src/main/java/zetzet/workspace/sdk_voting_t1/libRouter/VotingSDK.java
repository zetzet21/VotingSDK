package zetzet.workspace.sdk_voting_t1.libRouter;

import lombok.RequiredArgsConstructor;
import zetzet.workspace.sdk_voting_t1.dto.request.LoginRequest;
import zetzet.workspace.sdk_voting_t1.dto.request.RegistrationRequest;
import zetzet.workspace.sdk_voting_t1.dto.request.VoteDTORequest;
import zetzet.workspace.sdk_voting_t1.dto.response.TokenResponse;
import zetzet.workspace.sdk_voting_t1.dto.response.VoteDTOResponse;
import zetzet.workspace.sdk_voting_t1.dto.vote.KanoVoteDTO;
import zetzet.workspace.sdk_voting_t1.dto.vote.UserVoteResultDTO;
import zetzet.workspace.sdk_voting_t1.entity.User;
import zetzet.workspace.sdk_voting_t1.service.AuthenticationService;
import zetzet.workspace.sdk_voting_t1.service.UserService;
import zetzet.workspace.sdk_voting_t1.service.UserVoteService;
import zetzet.workspace.sdk_voting_t1.service.VoteService;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class VotingSDK {

    private final VoteService voteService;
    private final UserVoteService userVoteService;
    private final UserService userService;
    private final AuthenticationService authenticationService;

    // Публичные методы для доступа
    public List<VoteDTOResponse> getAllVotes() {
        return voteService.getAllVotes();
    }

    public VoteDTORequest createVote(VoteDTORequest voteDTO) {
        return voteService.createVote(voteDTO);
    }

    public void closeVote(UUID voteId) {
        voteService.closeVote(voteId);
    }

    public void castVote(KanoVoteDTO kanoVoteDTO) {
        userVoteService.castVote(kanoVoteDTO);
    }

    public List<UserVoteResultDTO> getKanoResults(UUID voteId) {
        return userVoteService.processKanoResults(voteId);
    }

    public User getUser(String username) {
        return userService.getByUsername(username);
    }

    public TokenResponse registerUser(RegistrationRequest registrationRequest) {
        return authenticationService.register(registrationRequest);
    }

    public TokenResponse loginUser(LoginRequest loginRequest) {
        return authenticationService.login(loginRequest);
    }
}
