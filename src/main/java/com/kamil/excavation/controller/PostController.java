package com.kamil.excavation.controller;

import com.kamil.excavation.dto.PostRequest;
import com.kamil.excavation.dto.PostResponse;
import com.kamil.excavation.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/posts/")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<Void> createPost(@RequestBody PostRequest postRequest) {
        postService.save(postRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPosts() {
        return status(HttpStatus.OK).body(postService.getAllPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPost(@PathVariable Long id) {
        return status(HttpStatus.OK).body(postService.getPost(id));
    }

    @GetMapping("by-subexcavation/{id}")
    public ResponseEntity<List<PostResponse>> getPostsBySubexcavation(@PathVariable Long id) {
        return status(HttpStatus.OK).body(postService.getPostsBySubexcavation(id));
    }

    @GetMapping("/by-user/{userName}")
    public ResponseEntity<List<PostResponse>> getPostsByUsername(@PathVariable String userName) {
        return status(HttpStatus.OK).body(postService.getPostsByUsername(userName));
    }

}
