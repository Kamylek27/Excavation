package com.kamil.excavation.mapper;


import com.kamil.excavation.dto.SubexcavationDto;
import com.kamil.excavation.model.Post;
import com.kamil.excavation.model.Subexcavation;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubexcavationMapper {

    @Mapping(target = "numberOfPosts", expression = "java(mapPosts(subexcavation.getPosts()))")
    SubexcavationDto mapSubexcavationToDto(Subexcavation subexcavation);

    default Integer mapPosts(List<Post> numberOfPosts) {
        return numberOfPosts.size();
    }

    @InheritInverseConfiguration
    @Mapping(target = "posts", ignore = true)
    Subexcavation mapDtoToSubreddit(SubexcavationDto subexcavationDto);
}

