package controller;

import service.TeamService;

public class TeamController {
    private TeamService service;

    public TeamController(TeamService service) {
        this.service = service;
    }
}
