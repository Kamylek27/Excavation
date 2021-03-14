package com.kamil.excavation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kamil.excavation.config.SecurityConfig;
import com.kamil.excavation.config.WebConfig;
import com.kamil.excavation.dto.LoginRequest;
import com.kamil.excavation.dto.SubexcavationDto;
import com.kamil.excavation.security.JwtProvider;
import com.kamil.excavation.service.RefreshTokenService;
import com.kamil.excavation.service.SubexcavationService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.is;

@WebMvcTest(controllers = SubexcavationController.class)
@ActiveProfiles("test")
public class SubexcavationControllerTest {


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
    private SubexcavationService subexcavationService;


    @Test
    @WithMockUser(username = "user1", password = "pwd", roles = "USER")
    void shouldCreateNewSubexcavation() throws Exception {
        SubexcavationDto sub = new SubexcavationDto(1L, "Test", "Test", 23);

        this.mockMvc.perform(post("/api/subexcavation")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(sub)))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username = "user1", password = "pwd", roles = "USER")
    void shouldFetchAllSubs() throws Exception {
        this.mockMvc.perform(get("/api/subexcavation"))
                .andExpect(status().isOk());
    }


    @Test
    @WithMockUser(username = "user1", password = "pwd", roles = "USER")
    void shouldFetchOneSubById() throws Exception {
        final Long subId = 1L;
        SubexcavationDto sub = new SubexcavationDto(subId, "Test", "Test", 23);
        this.mockMvc.perform(get("/api/subexcavation/view-subexcavation/{id}", subId))
                .andExpect(status().isOk());
    }

}
