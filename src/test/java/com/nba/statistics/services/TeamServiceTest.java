package com.nba.statistics.services;

import com.nba.statistics.entities.Team;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class TeamServiceTest {

    @Autowired
    private TeamService teamService;

    @Test
    void shouldFindTeamById() {
        //given
        Team team = teamService.findById(1);
        //then when
        assertThat(team.getName()).isEqualTo("Golden State Warriors");
    }

    @Test
    void shouldFindAllTeams() {
        //given
        Iterator<Team> teams = teamService.findAll();
        //then when
        assertThat(teams.hasNext()).isTrue();
    }

    @Test
    void shouldThrowExceptionWhenTeamNotFound() {
        //given then when
        assertThatThrownBy(() -> teamService.findById(99999))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid team Id");
    }
}