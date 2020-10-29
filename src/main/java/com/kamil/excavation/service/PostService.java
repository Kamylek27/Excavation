package com.kamil.excavation.service;


import com.kamil.excavation.dto.PostRequest;
import com.kamil.excavation.dto.PostResponse;
import com.kamil.excavation.exception.PostNotFoundException;
import com.kamil.excavation.exception.SubexcavationNotFoundException;
import com.kamil.excavation.mapper.PostMapper;
import com.kamil.excavation.model.Post;
import com.kamil.excavation.model.Subexcavation;
import com.kamil.excavation.model.User;
import com.kamil.excavation.repository.PostRepository;
import com.kamil.excavation.repository.SubexcavationRepository;
import com.kamil.excavation.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final SubexcavationRepository subexcavationRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final PostMapper postMapper;

    @Transactional(readOnly = true)
    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id.toString()));
        return postMapper.mapToDto(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }

    public void save(PostRequest postRequest) {
        Subexcavation subexcavation = subexcavationRepository.findByName(postRequest.getSubexcavationName())
                .orElseThrow(() -> new SubexcavationNotFoundException(postRequest.getSubexcavationName()));
        postRepository.save(postMapper.map(postRequest, subexcavation, authService.getCurrentUser()));
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsBySubexcavation(Long subredditId) {
        Subexcavation subreddit = subexcavationRepository.findById(subredditId)
                .orElseThrow(() -> new SubexcavationNotFoundException(subredditId.toString()));
        List<Post> posts = postRepository.findAllBySubexcavation(subreddit);
        return posts.stream().map(postMapper::mapToDto).collect(toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return postRepository.findByUser(user)
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }
}