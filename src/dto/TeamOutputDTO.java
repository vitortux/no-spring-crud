package dto;

import model.Team;

public record TeamOutputDTO(String name, String city, String coach, String arena, String owner, int championships) {

    public TeamOutputDTO(Team team) {
        this(team.getName(), team.getCity(), team.getCoach(), team.getArena(), team.getOwner(),
                team.getChampionships());
    }
}