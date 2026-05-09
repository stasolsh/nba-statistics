package com.nba.statistics.services;

import com.nba.statistics.entities.Team;
import com.nba.statistics.repositories.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Iterator;

@Service
@RequiredArgsConstructor
@Transactional
public class TeamService {
    private final TeamRepository teamRepository;

    public Page<Team> findAll(int pageNo, int pageSize) {
        return teamRepository.findAll(PageRequest.of(pageNo - 1, pageSize));
    }

    public Iterator<Team> findAll() {
        return teamRepository.findAll().iterator();
    }

    public Team save(Team team) {
        return teamRepository.save(team);
    }

    public Team findById(int id) {
        return teamRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid team Id:" + id));
    }

    public void delete(Team team) {
        teamRepository.delete(team);
    }
}
