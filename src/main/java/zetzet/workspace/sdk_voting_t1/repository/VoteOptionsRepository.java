package zetzet.workspace.sdk_voting_t1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zetzet.workspace.sdk_voting_t1.entity.vote.VoteOptions;

import java.util.UUID;

@Repository
public interface VoteOptionsRepository extends JpaRepository<VoteOptions, UUID> {
}
