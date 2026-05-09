package com.nba.statistics.repositories;

import com.nba.statistics.entities.Team;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class TeamRepositoryTest {

    @Autowired
    private TeamRepository teamRepository;

    @Test
    void shouldFindTeamById() {
        //given then
        Team team = teamRepository.findById(1).orElseThrow();
        //when
        assertThat(team.getName()).isEqualTo("Golden State Warriors");
        assertThat(team.getTeam_abbr()).isEqualTo("GSW");
    }
}