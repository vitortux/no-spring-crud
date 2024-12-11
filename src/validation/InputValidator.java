package validation;

import javax.swing.JOptionPane;

public class InputValidator {

    public static String getValidInput(String message) {
        String input = JOptionPane.showInputDialog(null, message, "Entrada", JOptionPane.PLAIN_MESSAGE);

        while (input == null || input.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Este campo não pode estar vazio ou nulo.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            input = JOptionPane.showInputDialog(null, message, "Entrada", JOptionPane.PLAIN_MESSAGE);
        }

        return input;
    }

    public static String getValidInput(String message, String defaultValue) {
        String input = JOptionPane
                .showInputDialog(null, message, "Entrada", JOptionPane.PLAIN_MESSAGE, null, null, defaultValue)
                .toString();

        while (input == null || input.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Este campo não pode estar vazio ou nulo.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            input = JOptionPane
                    .showInputDialog(null, message, "Entrada", JOptionPane.PLAIN_MESSAGE, null, null, defaultValue)
                    .toString();
        }

        return input;
    }

    public static int getValidIntegerInput(String message) {
        String input = JOptionPane.showInputDialog(null, message, "Entrada", JOptionPane.PLAIN_MESSAGE);

        while (input == null || input.trim().isEmpty() || !input.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Por favor, insira um número válido.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            input = JOptionPane.showInputDialog(null, message, "Entrada", JOptionPane.PLAIN_MESSAGE);
        }

        return Integer.parseInt(input);
    }

    public static int getValidIntegerInput(String message, int defaultValue) {
        String input = JOptionPane.showInputDialog(null, message, "Entrada", JOptionPane.PLAIN_MESSAGE, null, null,
                Integer.toString(defaultValue)).toString();

        while (input == null || input.trim().isEmpty() || !input.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Por favor, insira um número válido.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            input = JOptionPane.showInputDialog(null, message, "Entrada", JOptionPane.PLAIN_MESSAGE, null, null,
                    Integer.toString(defaultValue)).toString();
        }

        return Integer.parseInt(input);
    }
}
