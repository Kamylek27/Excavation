package com.kamil.excavation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kamil.excavation.dto.LoginRequest;
import com.kamil.excavation.dto.RefreshTokenRequest;
import com.kamil.excavation.dto.RegisterRequest;
import com.kamil.excavation.security.JwtProvider;
import com.kamil.excavation.service.AuthService;
import com.kamil.excavation.service.RefreshTokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = AuthController.class)
@ActiveProfiles("test")
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuthService authService;

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private JwtProvider jwtProvider;

    @MockBean
    private RefreshTokenService refreshTokenService;


    @Test
    public void shouldCreateNewUser() throws Exception {

        RegisterRequest user = new RegisterRequest("Test1", "test@test.com", "test");

        this.mockMvc.perform(post("/api/auth/signup")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().is(200));
    }


    @Test
    public void shouldLoginUser() throws Exception {

        LoginRequest user = new LoginRequest("Test1", "test");

        this.mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().is(200));
    }


    @Test
    public void shouldRefreshToken() throws Exception {

        RefreshTokenRequest token = new RefreshTokenRequest("131242141242141241", "test");

        this.mockMvc.perform(post("/api/auth/refresh/token")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(token)))
                .andExpect(status().isOk());
    }


    @Test
    public void shouldLogoutUser() throws Exception {

        RefreshTokenRequest token = new RefreshTokenRequest("131242141242141241", "test");

        this.mockMvc.perform(post("/api/auth/logout")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(token)))
                .andExpect(status().is(200));
    }


    @Test
    public void shouldVeryficationUserByToken() throws Exception {

        String token = "2131232131231232";

        this.mockMvc.perform(get("/api/auth/accountVerification/{token}", token))
                .andExpect(status().is(200));
    }


}



