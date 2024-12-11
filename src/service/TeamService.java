package service;

import java.util.Collections;
import java.util.Map;

import model.entity.Team;
import repository.TeamRepository;

public class TeamService {
    private TeamRepository repository;

    public TeamService(TeamRepository repository) {
        this.repository = repository;
    }

    public boolean addTeam(Team team) {
        if (!repository.getAllTeams().containsValue(team)) {
            repository.addTeam(team);
            return true;
        }
        return false;
    }

    public Map<Integer, Team> getAllTeams() {
        return repository.getAllTeams().isEmpty() ? Collections.emptyMap() : repository.getAllTeams();
    }

    public Team getTeamFromId(int id) {
        return repository.getAllTeams().containsKey(id) ? repository.getTeamFromId(id) : null;
    }

    public boolean updateTeam(int id, Team updatedTeam) {
        if (repository.getAllTeams().containsKey(id)) {
            repository.updateTeam(id, updatedTeam);
            return true;
        }
        return false;
    }

    public boolean deleteTeam(int id) {
        if (repository.getAllTeams().containsKey(id)) {
            repository.deleteTeam(id);
            return true;
        }
        return false;
    }
}
