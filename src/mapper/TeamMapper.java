package mapper;

import model.entity.Team;

public class TeamMapper {

    private TeamMapper() {
    }

    public static Team fromCsv(String csv) {
        String[] data = csv.split(",");
        return new Team(data[0], data[1], data[2], data[3], data[4], Integer.parseInt(data[5]));
    }

    public static String toCsv(Team team) {
        return team.getName() + "," +
                team.getCity() + "," +
                team.getCoach() + "," +
                team.getArena() + "," +
                team.getOwner() + "," +
                team.getChampionships();
    }
}
