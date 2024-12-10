import java.util.Map;

import model.entity.Team;
import repository.TeamRepository;
import service.TeamService;

public class App {
    public static void main(String[] args) throws Exception {
        TeamService service = new TeamService(new TeamRepository());

        Team lakers = new Team("Los Angeles Lakers", "Los Angeles", "Darvin Ham", "Crypto.com Arena", "Jeanie Buss",
                17);
        Team bulls = new Team("Chicago Bulls", "Chicago", "Billy Donovan", "United Center", "Jerry Reinsdorf", 6);
        Team warriors = new Team("Golden State Warriors", "San Francisco", "Steve Kerr", "Chase Center", "Joe Lacob",
                7);
        Team celtics = new Team("Boston Celtics", "Boston", "Joe Mazzulla", "TD Garden", "Wyc Grousbeck", 17);
        Team nets = new Team("Brooklyn Nets", "Brooklyn", "Jacque Vaughn", "Barclays Center", "Joe Tsai", 2);

        service.addTeam(lakers);
        service.addTeam(bulls);
        service.addTeam(warriors);
        service.addTeam(celtics);
        service.deleteTeam(3);
        service.addTeam(nets);

        Map<Integer, Team> teams = service.getAllTeams();
        for (Map.Entry<Integer, Team> entry : teams.entrySet()) {
            System.out.println(entry.getKey() + "," + entry.getValue().toString());
        }
    }
}