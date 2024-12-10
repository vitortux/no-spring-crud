package repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.entity.Team;

public class TeamRepository {
    private static final Logger logger = Logger.getLogger(TeamRepository.class.getName());
    private static final String FILE_PATH = "./src/db/team.txt";
    private Map<Integer, Team> teams;
    private int lastUsedId = 0;

    public TeamRepository() {
        this.teams = new HashMap<>();
        createTable();
        loadData();
    }

    public void addTeam(Team team) {
        int newId = getNewId();
        teams.put(newId, team);
        saveData();
    }

    public Map<Integer, Team> getAllTeams() {
        return new HashMap<>(teams);
    }

    public Team getTeamFromId(int id) {
        return teams.get(id);
    }

    public void updateTeam(int id, Team updatedTeam) {
        teams.put(id, updatedTeam);
        saveData();
    }

    public void deleteTeam(int id) {
        teams.remove(id);
        saveData();
    }

    private int getNewId() {
        return lastUsedId++;
    }

    private void createTable() {
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

    private void saveData() {
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

    private void loadData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                parseTeam(line);
            }
            logger.info("Dados da tabela \"" + FILE_PATH + "\" recuperados com sucesso!");
        } catch (IOException e) {
            logger.severe("Erro ao carregar dados do arquivo: " + FILE_PATH + ". Detalhes do erro: " + e.getMessage());
        }
    }

    private void parseTeam(String line) {
        String[] entry = line.split(",", 2);

        if (entry.length < 2) {
            logger.log(Level.WARNING, () -> "Linha malformada (faltando dados): " + line);
            return;
        }

        try {
            int id = Integer.parseInt(entry[0]);
            Team team = Team.fromCsv(entry[1]);
            teams.put(id, team);

            if (id > lastUsedId) {
                lastUsedId = id;
            }
        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, () -> "Erro ao processar linha (ID inválido): " + line);
        }
    }
}
