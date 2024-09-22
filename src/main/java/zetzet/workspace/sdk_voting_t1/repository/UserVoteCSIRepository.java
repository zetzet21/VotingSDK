package zetzet.workspace.sdk_voting_t1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zetzet.workspace.sdk_voting_t1.entity.UserVoteCSI;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserVoteCSIRepository extends JpaRepository<UserVoteCSI, UUID> {

    // Метод для поиска голосов по пользователю и опциям голосования
    Optional<UserVoteCSI> findByUserIdAndVoteOptionsId(UUID userId, UUID voteOptionsId);
}