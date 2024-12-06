package controller;

import java.util.logging.Logger;

import model.Team;
import repository.TeamRepository;

public class TeamController {
    private static final Logger logger = Logger.getLogger(TeamController.class.getName());
    private TeamRepository teamRepository;

    public TeamController() {
        this.teamRepository = new TeamRepository();
    }

    public void addTeam(Team team) {
        if (isTeamExists(team)) {
            logger.severe("Erro: o time \"" + team.getName() + "\" já foi cadastrado.");
        } else {
            int newId = teamRepository.getNewId();
            teamRepository.getTeams().put(newId, team);
            saveTeams();
            logger.info("Time \"" + team.getName() + "\" adicionado ao repositório.");
        }
    }

    private boolean isTeamExists(Team team) {
        for (Team existingTeam : teamRepository.getTeams().values()) {
            if (existingTeam.getName().equals(team.getName())) {
                return true;
            }
        }
        return false;
    }

    private void saveTeams() {
        teamRepository.saveData();
    }
}
