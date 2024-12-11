package controller;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import exception.DuplicateTeamException;
import exception.NoTeamsException;
import exception.TeamNotFoundException;
import model.dto.TeamDTO;
import service.TeamService;

public class TeamController {
    private static final Logger logger = Logger.getLogger(TeamController.class.getName());

    private TeamService service;

    public TeamController(TeamService service) {
        this.service = service;
    }

    public void addTeam() {
        try {
            String name = JOptionPane.showInputDialog(null, "Nome do time:", "Adicionar Time",
                    JOptionPane.PLAIN_MESSAGE);
            String city = JOptionPane.showInputDialog(null, "Cidade do time:", "Adicionar Time",
                    JOptionPane.PLAIN_MESSAGE);
            String coach = JOptionPane.showInputDialog(null, "Treinador do time:", "Adicionar Time",
                    JOptionPane.PLAIN_MESSAGE);
            String arena = JOptionPane.showInputDialog(null, "Arena do time:", "Adicionar Time",
                    JOptionPane.PLAIN_MESSAGE);
            String owner = JOptionPane.showInputDialog(null, "Dono do time:", "Adicionar Time",
                    JOptionPane.PLAIN_MESSAGE);
            String championshipsInput = JOptionPane.showInputDialog(null, "Número de campeonatos:", "Adicionar Time",
                    JOptionPane.PLAIN_MESSAGE);
            int championships = Integer.parseInt(championshipsInput);

            TeamDTO newTeam = new TeamDTO(name, city, coach, arena, owner, championships);
            service.addTeam(newTeam);

            JOptionPane.showMessageDialog(null, "Time adicionado com sucesso!", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (DuplicateTeamException e) {
            logger.log(Level.SEVERE, () -> "Erro ao adicionar time: " + e.getMessage());
            JOptionPane.showMessageDialog(null,
                    "Erro ao adicionar o time:\n" + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    public void getAllTeams() {
        try {
            Map<Integer, TeamDTO> allTeams = service.getAllTeams();

            StringBuilder message = new StringBuilder("Times disponíveis:\n");
            for (Map.Entry<Integer, TeamDTO> entry : allTeams.entrySet()) {
                int id = entry.getKey();
                TeamDTO team = entry.getValue();
                message.append("id=").append(id).append(" ").append(team.toString()).append("\n");
            }

            JOptionPane.showMessageDialog(null, message.toString(), "Times", JOptionPane.INFORMATION_MESSAGE);
        } catch (NoTeamsException e) {
            logger.log(Level.WARNING, () -> "Erro ao retornar times: " + e.getMessage());
            JOptionPane.showMessageDialog(null,
                    "Erro ao recuperar os times:\n" + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void getTeamById() {
        try {
            String idInput = JOptionPane.showInputDialog(null, "Número de campeonatos:", "Buscar Time",
                    JOptionPane.PLAIN_MESSAGE);
            int id = Integer.parseInt(idInput);

            TeamDTO team = service.getTeamById(id);
            JOptionPane.showMessageDialog(null, team.toString(), "Informações do Time",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (TeamNotFoundException e) {
            logger.log(Level.WARNING, () -> "Erro ao retornar time: " + e.getMessage());
            JOptionPane.showMessageDialog(null,
                    "Erro ao recuperar o time:\n" + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
