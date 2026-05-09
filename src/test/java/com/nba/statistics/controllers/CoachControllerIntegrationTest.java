package com.nba.statistics.controllers;

import com.nba.statistics.repositories.CoachRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CoachControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CoachRepository coachRepository;

    @Test
    void shouldShowCoachList() throws Exception {
        mockMvc.perform(get("/index-coach"))
                .andExpect(status().isOk())
                .andExpect(view().name("index-coach"))
                .andExpect(model().attributeExists("coaches"));
    }

    @Test
    void shouldShowAddCoachForm() throws Exception {
        mockMvc.perform(get("/add/coach"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-coach"))
                .andExpect(model().attributeExists("teams"));
    }

    @Test
    void shouldAddCoach() throws Exception {
        mockMvc.perform(post("/add/coach")
                        .param("name", "Test Coach")
                        .param("age", "48")
                        .param("team.id", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/index-coach"));

        assertThat(coachRepository.findAll())
                .anyMatch(coach -> coach.getName().equals("Test Coach"));
    }

    @Test
    void shouldReturnFormWhenCoachValidationFails() throws Exception {
        mockMvc.perform(post("/add/coach")
                        .param("name", "")
                        .param("age", "0")
                        .param("team.id", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-coach"))
                .andExpect(model().attributeHasFieldErrors("coach", "name", "age"));
    }

    @Test
    void shouldUpdateForm() throws Exception {
        mockMvc.perform(get("/edit/coach/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("update-coach"))
                .andExpect(model().attributeExists("coach"))
                .andExpect(model().attributeExists("teams"));
    }

    @Test
    void shouldUpdateCoach() throws Exception {
        mockMvc.perform(post("/update/coach/1")
                        .param("name", "Update Coach")
                        .param("age", "48")
                .param("team.id", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/index-coach"));
    }

    @Test
    void shouldReturnUpdateFormWhenCoachValidationFails() throws Exception {
        mockMvc.perform(post("/update/coach/1")
                        .param("name", "")
                        .param("age", "0")
                        .param("team.id", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("update-coach"))
                .andExpect(model().attributeHasFieldErrors("coach", "name", "age"))
                .andExpect(model().attributeExists("teams"));
    }

    @Test
    void shouldDeleteCoach() throws Exception {
        assertThat(coachRepository.findById(1)).isPresent();
        mockMvc.perform(get("/delete/coach/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/index-coach"));
    }
}