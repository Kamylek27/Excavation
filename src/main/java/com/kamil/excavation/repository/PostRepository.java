package com.kamil.excavation.repository;

import com.kamil.excavation.model.Post;
import com.kamil.excavation.model.Subexcavation;
import com.kamil.excavation.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllBySubexcavation(Subexcavation subexcavation);

    List<Post> findByUser(User user);
}