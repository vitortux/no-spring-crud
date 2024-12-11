import javax.swing.JOptionPane;

import controller.TeamController;
import repository.TeamRepository;
import service.TeamService;

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
                    teamController();
                    break;
                case 1:
                    System.exit(0);
                    break;
                default:
                    break;
            }
        } while (option != -1);
    }

    private static void teamController() {
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
                    // controller.addTeam();
                    break;
                case 1:
                    controller.getAllTeams();
                    break;
                case 2:
                    // controller.getTeamById();
                    break;
                case 3:
                    // controller.updateTeam();
                    break;
                case 4:
                    // controller.deleteTeam();
                    break;
                default:
                    break;
            }
        } while (option != -1 && option != 5);
    }
}