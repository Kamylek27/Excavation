package com.kamil.excavation.repository;

import com.kamil.excavation.model.Post;
import com.kamil.excavation.model.User;
import com.kamil.excavation.model.Vote;

import java.util.Optional;

public interface VoteRepository {
    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);

    void save(Vote mapToVote);
}
