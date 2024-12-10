package service;

import java.util.Collections;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.entity.Team;
import repository.TeamRepository;

public class TeamService {
    private static final Logger logger = Logger.getLogger(TeamService.class.getName());
    private TeamRepository repository;

    public TeamService(TeamRepository repository) {
        this.repository = repository;
    }

    public void addTeam(Team team) {
        if (!repository.getAllTeams().containsValue(team)) {
            repository.addTeam(team);
            logger.log(Level.INFO, () -> "Time adicionado com sucesso: " + team);
        } else {
            logger.log(Level.WARNING, () -> "Time já cadastrado: " + team);
        }
    }

    public Map<Integer, Team> getAllTeams() {
        if (!repository.getAllTeams().isEmpty()) {
            return repository.getAllTeams();
        } else {
            logger.log(Level.WARNING, () -> "Não há times cadastrados no momento.");
            return Collections.emptyMap();
        }
    }

    public Team getTeamFromId(int id) {
        if (repository.getAllTeams().containsKey(id)) {
            Team team = repository.getTeamFromId(id);
            logger.log(Level.INFO, () -> "Time encontrado com sucesso: " + team);
            return team;
        } else {
            logger.log(Level.WARNING, () -> "Não há times cadastrados no momento.");
            return null;
        }
    }

    public void updateTeam(int id, Team updatedTeam) {
        if (repository.getAllTeams().containsKey(id)) {
            repository.updateTeam(id, updatedTeam);
            logger.log(Level.INFO, () -> "Time alterado com sucesso: " + updatedTeam);
        } else {
            logger.log(Level.WARNING, () -> "Não foi possível encontrar o time: " + updatedTeam.getName());
        }
    }

    public void deleteTeam(int id) {
        if (repository.getAllTeams().containsKey(id)) {
            repository.deleteTeam(id);
            logger.log(Level.INFO, () -> "Time removido com sucesso.");
        } else {
            logger.log(Level.WARNING, () -> "Não foi possível encontrar o time com ID: " + id);
        }
    }
}
