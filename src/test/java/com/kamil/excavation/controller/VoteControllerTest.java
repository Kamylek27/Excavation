package com.kamil.excavation.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.kamil.excavation.dto.PostRequest;
import com.kamil.excavation.dto.SubexcavationDto;
import com.kamil.excavation.dto.VoteDto;
import com.kamil.excavation.model.VoteType;
import com.kamil.excavation.security.JwtProvider;
import com.kamil.excavation.service.RefreshTokenService;
import com.kamil.excavation.service.VoteService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = VoteController.class)
@ActiveProfiles("test")
public class VoteControllerTest {

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
    private VoteService voteService;

    @Test
    @WithMockUser(username = "user1", password = "pwd", roles = "USER")
    void shouldVote() throws Exception {

        final Long postId = 1L;
        PostRequest post = new PostRequest(postId, "Test", "Test", "Test", "Test");

        VoteDto vote = new VoteDto(VoteType.UPVOTE, postId);

        this.mockMvc.perform(post("/api/votes/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(vote)))
                .andExpect(status().isOk());
    }


}
