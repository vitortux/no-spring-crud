package validations;

import model.entity.Team;
import repository.TeamRepository;

public class DuplicateTeamValidation implements TeamValidation {
    private TeamRepository repository;

    public void validate(Team team) {
        if (repository.getAllTeams().containsValue(team)) {
            throw new IllegalStateException("Time já está presente no banco!");
        }
    }
}
