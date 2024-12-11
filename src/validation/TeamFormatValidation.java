package validation;

import exception.TeamValidationException;
import model.entity.Team;

public class TeamFormatValidation implements TeamValidation {

    @Override
    public void validate(Team team) {
        if (team.getName() == null || team.getCity() == null || team.getCoach() == null ||
                team.getArena() == null || team.getOwner() == null) {
            throw new TeamValidationException("Um ou mais campos do time estão nulos.");
        }
    }
}
