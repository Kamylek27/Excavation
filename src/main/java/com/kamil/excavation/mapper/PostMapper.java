package com.kamil.excavation.mapper;

import com.kamil.excavation.dto.PostRequest;
import com.kamil.excavation.dto.PostResponse;
import com.kamil.excavation.model.Post;
import com.kamil.excavation.model.Subexcavation;
import com.kamil.excavation.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "subexcavation", source = "subexcavation")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "description", source = "postRequest.description")
    Post map(PostRequest postRequest, Subexcavation subexcavation, User user);

    @Mapping(target = "id", source = "postId")
    @Mapping(target = "postName", source = "postName")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "url", source = "url")
    @Mapping(target = "subexcavationName", source = "subexcavation.name")
    @Mapping(target = "userName", source = "user.username")
    PostResponse mapToDto(Post post);

}
