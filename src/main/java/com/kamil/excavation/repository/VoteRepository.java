package com.kamil.excavation.repository;

import com.kamil.excavation.model.Post;
import com.kamil.excavation.model.User;
import com.kamil.excavation.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote,Long> {
    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);

}
