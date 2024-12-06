package service;

import model.Team;
import repository.TeamRepository;

public class TeamService {
    private TeamRepository teamRepository;

    public TeamService() {
        this.teamRepository = new TeamRepository();
    }

    public boolean isTeamExists(Team team) {
        for (Team existingTeam : teamRepository.getTeams().values()) {
            if (existingTeam.getName().equals(team.getName())) {
                return true;
            }
        }
        return false;
    }

    public void addTeam(Team team) {
        if (!isTeamExists(team)) {
            int newId = teamRepository.getNewId();
            teamRepository.getTeams().put(newId, team);
            teamRepository.saveData();
        }
    }
}
