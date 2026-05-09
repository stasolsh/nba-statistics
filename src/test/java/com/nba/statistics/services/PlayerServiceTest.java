package com.nba.statistics.services;

import com.nba.statistics.entities.Player;
import com.nba.statistics.entities.Team;
import com.nba.statistics.repositories.TeamRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class PlayerServiceTest {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private TeamRepository teamRepository;

    @Test
    void shouldFindPlayersByNameLike() {
        //given then
        List<Player> players = playerService.findPlayersByNameLike("Stephen");
        //when
        assertThat(players)
                .isNotEmpty()
                .allMatch(player -> player.getName().startsWith("Stephen"));
    }

    @Test
    void shouldFindPlayersByTeamAndAgeBetween() {
        //given
        Team team = teamRepository.findById(1).orElseThrow();
        //then
        List<Player> players = playerService.findPlayersByTeamAndAgeBetween(team, 16, 45);
        //when
        assertThat(players)
                .isNotEmpty()
                .allMatch(player -> player.getTeam().getId() == 1);
    }

    @Test
    void shouldThrowExceptionWhenPlayerNotFound() {
        //given then when
        assertThatThrownBy(() -> playerService.findById(99999))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid player Id");
    }
}