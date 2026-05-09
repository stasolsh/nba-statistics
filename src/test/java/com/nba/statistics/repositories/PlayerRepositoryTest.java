package com.nba.statistics.repositories;

import com.nba.statistics.entities.Player;
import com.nba.statistics.entities.Team;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class PlayerRepositoryTest {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Test
    void shouldFindPlayersByNameLike() {
        //given then
        List<Player> players = playerRepository.findPlayersByNameLike("Stephen%");
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
        List<Player> players = playerRepository.findPlayersByTeamAndAgeBetween(team, 16, 45);
        //when
        assertThat(players)
                .isNotEmpty()
                .allMatch(player -> player.getTeam().getId() == 1)
                .allMatch(player -> player.getAge() >= 16 && player.getAge() <= 45);
    }
}