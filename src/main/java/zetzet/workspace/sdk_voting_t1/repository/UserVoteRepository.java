package zetzet.workspace.sdk_voting_t1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import zetzet.workspace.sdk_voting_t1.entity.UserVote;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserVoteRepository extends JpaRepository<UserVote, UUID> {

    // Метод для поиска голосов по пользователю и опциям голосования
    Optional<UserVote> findByUserIdAndVoteOptionsId(UUID userId, UUID voteOptionsId);
}

