import javax.swing.JOptionPane;

import controller.TeamController;
import model.dto.TeamDTO;
import repository.TeamRepository;
import service.TeamService;
import validation.InputValidator;

public class App {
    public static void main(String[] args) throws Exception {
        menu();
    }

    private static void menu() {
        int option;
        do {
            String[] options = { "Times", "Sair" };
            option = JOptionPane.showOptionDialog(null,
                    "Escolha uma opção de gerenciamento:",
                    "Menu",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    null);

            switch (option) {
                case 0:
                    teamMenu();
                    break;
                case 1:
                    System.exit(0);
                    break;
                default:
                    break;
            }
        } while (option != -1);
    }

    private static void teamMenu() {
        TeamController controller = new TeamController(new TeamService(new TeamRepository()));

        int option;
        do {
            String[] options = { "Adicionar time", "Listar times", "Buscar time", "Atualizar time", "Deletar time",
                    "Voltar" };
            option = JOptionPane.showOptionDialog(null,
                    "Escolha uma opção:",
                    "Menu",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    null);

            switch (option) {
                case 0:
                    String name = InputValidator.getValidInput("Nome do time:");
                    String city = InputValidator.getValidInput("Cidade do time:");
                    String coach = InputValidator.getValidInput("Treinador do time:");
                    String arena = InputValidator.getValidInput("Arena do time:");
                    String owner = InputValidator.getValidInput("Dono do time:");
                    int championships = InputValidator.getValidIntegerInput("Número de campeonatos:");

                    TeamDTO newTeam = new TeamDTO(name, city, coach, arena, owner, championships);
                    controller.addTeam(newTeam);
                    break;
                case 1:
                    controller.getAllTeams();
                    break;
                case 2:
                    int getTeamId = InputValidator.getValidIntegerInput("Digite o ID:");
                    controller.getTeamById(getTeamId);
                    break;
                case 3:
                    int updateTeamId = InputValidator.getValidIntegerInput("Digite o ID:");
                    controller.updateTeam(updateTeamId);
                    break;
                case 4:
                    int deleteTeamId = InputValidator.getValidIntegerInput("Digite o ID:");
                    controller.deleteTeam(deleteTeamId);
                    break;
                default:
                    break;
            }
        } while (option != -1 && option != 5);
    }
}