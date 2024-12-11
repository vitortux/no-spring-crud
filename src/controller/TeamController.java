package controller;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import exception.NoTeamsException;
import model.dto.TeamDTO;
import service.TeamService;

public class TeamController {
    private static final Logger logger = Logger.getLogger(TeamController.class.getName());

    private TeamService service;

    public TeamController(TeamService service) {
        this.service = service;
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
                    "Erro ao tentar recuperar os times:\n" + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
