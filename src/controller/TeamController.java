package controller;

import java.util.logging.Logger;

import model.Team;
import service.TeamService;

public class TeamController {
    private static final Logger logger = Logger.getLogger(TeamController.class.getName());
    private TeamService teamService;

    public TeamController() {
        this.teamService = new TeamService();
    }

    public void addTeam(Team team) {
        if (teamService.isTeamExists(team)) {
            logger.severe("Erro: o time \"" + team.getName() + "\" já foi cadastrado.");
        } else {
            teamService.addTeam(team);
            logger.info("Time \"" + team.getName() + "\" adicionado ao repositório.");
        }
    }
}
