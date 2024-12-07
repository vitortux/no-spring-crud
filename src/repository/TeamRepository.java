package repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import model.Team;

public class TeamRepository {
    private static final Logger logger = Logger.getLogger(TeamRepository.class.getName());
    private static final String FILE_PATH = "./src/db/team.txt";

    private Map<Integer, Team> teams;

    public TeamRepository() {
        this.teams = new HashMap<>();
        createTable();
        loadData();
    }

    public void createTable() {
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();

                if (file.createNewFile()) {
                    logger.info("Tabela criada em: " + FILE_PATH);
                }
            } catch (IOException e) {
                logger.severe("Erro ao criar o arquivo \"" + FILE_PATH + "\" " + e.getMessage());
            }
        }
    }

    public Map<Integer, Team> getTeams() {
        return teams;
    }

    public int getNewId() {
        return teams.size() + 1;
    }

    public void saveData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Map.Entry<Integer, Team> entry : teams.entrySet()) {
                Team team = entry.getValue();
                writer.write(entry.getKey() + "," + team.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            logger.severe("Erro ao salvar os times no arquivo \"" + FILE_PATH + "\" " + e.getMessage());
        }
    }

    public void loadData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length == 7) {
                    int id = Integer.parseInt(data[0]);
                    String name = data[1];
                    String city = data[2];
                    String coach = data[3];
                    String arena = data[4];
                    String owner = data[5];
                    int championships = Integer.parseInt(data[6]);

                    Team team = new Team(name, city, coach, arena, owner, championships);
                    teams.put(id, team);
                }
            }

            logger.info("Dados da tabela \"" + FILE_PATH + "\" recuperados com sucesso!");
        } catch (IOException e) {
            logger.severe("Erro ao carregar dados do arquivo: " + FILE_PATH + ". Detalhes do erro: " + e.getMessage());
        }
    }
}
