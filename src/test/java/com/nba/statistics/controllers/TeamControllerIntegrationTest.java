package com.nba.statistics.controllers;

import com.nba.statistics.entities.Team;
import com.nba.statistics.repositories.TeamRepository;
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
class TeamControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TeamRepository teamRepository;

    @Test
    void shouldShowTeamList() throws Exception {
        mockMvc.perform(get("/index-team"))
                .andExpect(status().isOk())
                .andExpect(view().name("index-team"))
                .andExpect(model().attributeExists("teams"))
                .andExpect(model().attributeExists("currentPage"))
                .andExpect(model().attributeExists("totalPages"))
                .andExpect(model().attributeExists("totalItems"));
    }

    @Test
    void shouldShowAddTeamForm() throws Exception {
        mockMvc.perform(get("/add/team"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-team"));
    }

    @Test
    void shouldAddTeam() throws Exception {
        mockMvc.perform(post("/add/team")
                        .param("name", "Boston Celtics")
                        .param("team_abbr", "BOS")
                        .param("location", "Boston/Massachusetts"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/index-team"));

        assertThat(teamRepository.findAll())
                .anyMatch(team -> team.getName().equals("Boston Celtics"));
    }

    @Test
    void shouldReturnFormWhenTeamValidationFails() throws Exception {
        mockMvc.perform(post("/add/team")
                        .param("name", "")
                        .param("team_abbr", "VERY_LONG_ABBR")
                        .param("location", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("add-team"))
                .andExpect(model().attributeHasFieldErrors("team", "name", "team_abbr", "location"));
    }

    @Test
    void shouldShowUpdateTeamForm() throws Exception {
        mockMvc.perform(get("/edit/team/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("update-team"))
                .andExpect(model().attributeExists("team"));
    }

    @Test
    void shouldUpdateTeam() throws Exception {
        mockMvc.perform(post("/update/team/1")
                        .param("name", "Updated Team")
                        .param("team_abbr", "UPD")
                        .param("location", "Updated/Location"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/index-team"));

        Team updatedTeam = teamRepository.findById(1).orElseThrow();

        assertThat(updatedTeam.getName()).isEqualTo("Updated Team");
        assertThat(updatedTeam.getTeam_abbr()).isEqualTo("UPD");
        assertThat(updatedTeam.getLocation()).isEqualTo("Updated/Location");
    }

    @Test
    void shouldReturnUpdateTeamFormWhenValidationFails() throws Exception {
        mockMvc.perform(post("/update/team/1")
                        .param("name", "")
                        .param("team_abbr", "VERY_LONG_ABBR")
                        .param("location", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("update-team"))
                .andExpect(model().attributeHasFieldErrors("team", "name", "team_abbr", "location"));
    }

}