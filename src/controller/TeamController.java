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
import validation.InputValidator;

public class TeamController {
    private static final Logger logger = Logger.getLogger(TeamController.class.getName());

    private TeamService service;

    public TeamController(TeamService service) {
        this.service = service;
    }

    public void addTeam() {
        try {
            String name = InputValidator.getValidInput("Nome do time:");
            String city = InputValidator.getValidInput("Cidade do time:");
            String coach = InputValidator.getValidInput("Treinador do time:");
            String arena = InputValidator.getValidInput("Arena do time:");
            String owner = InputValidator.getValidInput("Dono do time:");
            int championships = InputValidator.getValidIntegerInput("Número de campeonatos:");

            TeamDTO newTeam = new TeamDTO(name, city, coach, arena, owner, championships);
            service.addTeam(newTeam);

            logger.log(Level.INFO, () -> "Time \"" + newTeam.name() + "\" adicionado com sucesso.");
            JOptionPane.showMessageDialog(null, "Time adicionado com sucesso!", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (DuplicateTeamException e) {
            logger.log(Level.SEVERE, () -> "Erro ao adicionar time: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao adicionar o time:\n" + e.getMessage(), "Erro",
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

            logger.log(Level.INFO, () -> "Times recuperados do banco com sucesso.");
            JOptionPane.showMessageDialog(null, message.toString(), "Times", JOptionPane.INFORMATION_MESSAGE);
        } catch (NoTeamsException e) {
            logger.log(Level.INFO, () -> "Não foi possível retornar a lista de times: " + e.getMessage());
            JOptionPane.showMessageDialog(null,
                    "Não há times cadastrados no momento.",
                    "Listar TImes",
                    JOptionPane.INFORMATION_MESSAGE);

        }
    }

    public void getTeamById() {
        try {
            int id = InputValidator.getValidIntegerInput("Digite o ID:");

            TeamDTO team = service.getTeamById(id);

            logger.log(Level.INFO, () -> "Time \"" + team.name() + "\" recuperado com sucesso.");
            JOptionPane.showMessageDialog(null, team.toString(), "Informações do Time",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (TeamNotFoundException e) {
            logger.log(Level.WARNING, () -> "Erro ao retornar time: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao recuperar o time:\n" + e.getMessage(), "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateTeam() {
        try {
            int id = InputValidator.getValidIntegerInput("Digite o ID:");
            TeamDTO team = service.getTeamById(id);

            String name = InputValidator.getValidInput("Nome do time:", team.name());
            String city = InputValidator.getValidInput("Cidade do time:", team.city());
            String coach = InputValidator.getValidInput("Treinador do time:", team.coach());
            String arena = InputValidator.getValidInput("Arena do time:", team.arena());
            String owner = InputValidator.getValidInput("Dono do time:", team.owner());
            int championships = InputValidator.getValidIntegerInput("Número de campeonatos:", team.championships());

            TeamDTO updatedTeam = new TeamDTO(name, city, coach, arena, owner, championships);

            service.updateTeam(id, updatedTeam);

            logger.log(Level.INFO, () -> "Time com ID " + id + " foi atualizado com sucesso.");
            JOptionPane.showMessageDialog(null, "Time atualizado com sucesso!", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (TeamNotFoundException | DuplicateTeamException e) {
            logger.log(Level.WARNING, () -> "Erro ao retornar time: " + e.getMessage());
            JOptionPane.showMessageDialog(null,
                    "Erro ao recuperar o time:\n" + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteTeam() {
        try {
            String idInput = JOptionPane.showInputDialog(null, "Digite o ID:", "Deletar Time",
                    JOptionPane.PLAIN_MESSAGE);
            int id = Integer.parseInt(idInput);

            service.deleteTeam(id);

            logger.log(Level.INFO, () -> "Time deletado (ID: " + id + ") com sucesso.");
            JOptionPane.showMessageDialog(null, "Time deletado com sucesso!", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (TeamNotFoundException e) {
            logger.log(Level.WARNING, () -> "Erro ao deletar time: " + e.getMessage());
            JOptionPane.showMessageDialog(null,
                    "Erro ao deletar o time:\n" + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
