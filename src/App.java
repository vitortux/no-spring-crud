import java.util.Map;

import model.dto.TeamDTO;
import model.entity.Team;
import repository.TeamRepository;
import service.TeamService;

public class App {
    public static void main(String[] args) throws Exception {
        TeamService service = new TeamService(new TeamRepository());

        TeamDTO lakers = new TeamDTO("Los Angeles Lakers", "Los Angeles", "Darvin Ham", "Crypto.com Arena",
                "Jeanie Buss",
                17);
        TeamDTO bulls = new TeamDTO("Chicago Bulls", "Chicago", "Billy Donovan", "United Center", "Jerry Reinsdorf", 6);
        TeamDTO warriors = new TeamDTO("Golden State Warriors", "San Francisco", "Steve Kerr", "Chase Center",
                "Joe Lacob",
                7);
        TeamDTO celtics = new TeamDTO("Boston Celtics", "Boston", "Joe Mazzulla", "TD Garden", "Wyc Grousbeck", 17);
        TeamDTO nets = new TeamDTO("Brooklyn Nets", "Brooklyn", "Jacque Vaughn", "Barclays Center", "Joe Tsai", 2);

        service.addTeam(lakers);
        service.addTeam(bulls);
        service.addTeam(warriors);
        service.addTeam(celtics);
        service.deleteTeam(3);
        service.addTeam(nets);

        Map<Integer, TeamDTO> teams = service.getAllTeams();
        for (Map.Entry<Integer, TeamDTO> entry : teams.entrySet()) {
            System.out.println(entry.getKey() + ", " + entry.getValue().toString());
        }
    }
}