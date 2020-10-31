package com.kamil.excavation.mapper;

import com.github.marlonlom.utilities.timeago.TimeAgo;
import com.kamil.excavation.dto.PostRequest;
import com.kamil.excavation.dto.PostResponse;
import com.kamil.excavation.model.Post;
import com.kamil.excavation.model.Subexcavation;
import com.kamil.excavation.model.User;
import com.kamil.excavation.repository.CommentRepository;
import com.kamil.excavation.repository.VoteRepository;
import com.kamil.excavation.service.AuthService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class PostMapper {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private AuthService authService;

    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "description", source = "postRequest.description")
    @Mapping(target = "subexcavation", source = "subexcavation")
    @Mapping(target = "voteCount", constant = "0")
    public abstract Post map(PostRequest postRequest, Subexcavation subexcavation, User user);

    @Mapping(target = "id", source = "postId")
    @Mapping(target = "subexcavationName", source = "subexcavation.name")
    @Mapping(target = "userName", source = "user.username")
    @Mapping(target = "commentCount", expression = "java(commentCount(post))")
    @Mapping(target = "duration", expression = "java(getDuration(post))")
    public abstract PostResponse mapToDto(Post post);


    Integer commentCount(Post post) {
        return commentRepository.findByPost(post).size();
    }

    String getDuration(Post post) {
        return TimeAgo.using(post.getCreatedDate().toEpochMilli());
    }

}
