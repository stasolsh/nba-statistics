package com.nba.statistics.controllers;

import com.nba.statistics.entities.Positions;
import com.nba.statistics.repositories.PlayerRepository;
import com.nba.statistics.repositories.TeamRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class PlayerControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Test
    void shouldShowPlayerList() throws Exception {
        mockMvc.perform(get("/index-player"))
                .andExpect(status().isOk())
                .andExpect(view().name("index-player"))
                .andExpect(model().attributeExists("players"))
                .andExpect(model().attributeExists("currentPage"))
                .andExpect(model().attributeExists("totalPages"))
                .andExpect(model().attributeExists("totalItems"));
    }

    @Test
    void shouldShowAddPlayerForm() throws Exception {
        mockMvc.perform(get("/add/player"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-player"))
                .andExpect(model().attributeExists("teams"));
    }

    @Test
    void shouldAddPlayer() throws Exception {
        mockMvc.perform(post("/add/player")
                        .param("name", "Test Player")
                        .param("age", "25")
                        .param("pos", Positions.PG.name())
                        .param("team.id", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/index-player"));

        assertThat(playerRepository.findPlayersByNameLike("Test Player")).hasSize(1);
    }

    @Test
    void shouldReturnFormWhenPlayerValidationFails() throws Exception {
        mockMvc.perform(post("/add/player")
                        .param("name", "")
                        .param("age", "0")
                        .param("pos", Positions.PG.name())
                        .param("team.id", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-player"))
                .andExpect(model().attributeHasFieldErrors("player", "name", "age"));
    }

    @Test
    void shouldFilterPlayersByName() throws Exception {
        mockMvc.perform(get("/filter-player")
                        .param("keyword", "Stephen"))
                .andExpect(status().isOk())
                .andExpect(view().name("index-player"))
                .andExpect(model().attributeExists("players"));
    }

    @Test
    void shouldDeletePlayer() throws Exception {
        assertThat(playerRepository.findById(1)).isPresent();

        mockMvc.perform(get("/delete/player/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/index-player"));

        assertThat(playerRepository.findById(1)).isNotPresent();
    }
}
