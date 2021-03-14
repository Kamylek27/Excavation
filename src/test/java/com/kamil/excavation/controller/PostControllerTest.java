package com.kamil.excavation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kamil.excavation.dto.PostRequest;
import com.kamil.excavation.dto.RegisterRequest;
import com.kamil.excavation.dto.SubexcavationDto;
import com.kamil.excavation.security.JwtProvider;
import com.kamil.excavation.service.PostService;
import com.kamil.excavation.service.RefreshTokenService;
import com.kamil.excavation.service.SubexcavationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PostController.class)
@ActiveProfiles("test")
public class PostControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private JwtProvider jwtProvider;

    @MockBean
    private RefreshTokenService refreshTokenService;

    @MockBean
    private PostService postService;


    @Test
    @WithMockUser(username = "user1", password = "pwd", roles = "USER")
    void shouldCreateNewPost() throws Exception {
        PostRequest post = new PostRequest(1L, "Test", "Test", "Test", "Test");

        this.mockMvc.perform(post("/api/posts/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(post)))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldFetchAllPosts() throws Exception {
        this.mockMvc.perform(get("/api/posts/"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldFetchOnePostById() throws Exception {
        final Long postId = 1L;
        PostRequest post = new PostRequest(postId, "Test", "Test", "Test", "Test");

        this.mockMvc.perform(get("/api/posts/{id}", postId))
                .andExpect(status().isOk());
    }


    @Test
    void shouldFetchOnePostByIdSub() throws Exception {
        final Long subId = 1L;
        final Long postId = 1L;

        PostRequest post = new PostRequest(postId, "Test", "Test", "Test", "Test");
        SubexcavationDto sub = new SubexcavationDto(subId, "Test", "Test", 23);
        this.mockMvc.perform(get("/api/posts/by-subexcavation/{id}", subId))
                .andExpect(status().isOk());
    }


    @Test
    @WithMockUser(username = "user1", password = "pwd", roles = "USER")
    void shouldFetchOnePostByUserName() throws Exception {
        final Long postId = 1L;

        RegisterRequest user = new RegisterRequest("user1", "test@test.com", "test");
        PostRequest post = new PostRequest(postId, "Test", "Test", "Test", "Test");

        this.mockMvc.perform(get("/api/posts/by-user/{userName}", user.getUsername()))
                .andExpect(status().isOk());
    }



}