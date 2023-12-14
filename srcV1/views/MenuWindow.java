package views;

import controllers.GameController;
import models.GameMode;

import javax.swing.*;

public class MenuWindow extends JFrame {
    public MenuWindow(GameController gameController) {
        super("Menu");
        setSize(900, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        JTextField usernameTextField = new JTextField("Anonymous");
        JComboBox<GameMode> gameModeComboBox = new JComboBox<>(GameMode.values());
        JSlider nbRoundsSlider = new JSlider(JSlider.HORIZONTAL, 3, 5, 3);
        JSlider nbColorsSlider = new JSlider(JSlider.HORIZONTAL, 8, 12, 8);
        JSlider nbColorsInCombinationSlider = new JSlider(JSlider.HORIZONTAL, 4, 6, 4);
        JSlider nbAttemptsSlider = new JSlider(JSlider.HORIZONTAL, 10, 12, 10);

        JButton startButton = new JButton("Start");

        startButton.addActionListener(e -> {
            String username = usernameTextField.getText();
            GameMode gameMode = (GameMode) gameModeComboBox.getSelectedItem();
            int nbRounds = nbRoundsSlider.getValue();
            int nbColors = nbColorsSlider.getValue();
            int nbColorsInCombination = nbColorsInCombinationSlider.getValue();
            int nbAttempts = nbAttemptsSlider.getValue();
            gameController.startGame(username, gameMode, nbRounds, nbColors, nbColorsInCombination, nbAttempts);
        });

        JLabel usernameLabel = new JLabel("Username");
        usernameTextField.setAlignmentX(CENTER_ALIGNMENT);

        JLabel gameModeLabel = new JLabel("Game mode");
        gameModeComboBox.setAlignmentX(CENTER_ALIGNMENT);

        JLabel nbRoundsLabel = new JLabel("Number of rounds");
        nbRoundsSlider.setMajorTickSpacing(1);
        nbRoundsSlider.setPaintTicks(true);
        nbRoundsSlider.setPaintLabels(true);
        nbRoundsSlider.setSnapToTicks(true);
        nbRoundsSlider.setAlignmentX(CENTER_ALIGNMENT);

        JLabel nbColorsLabel = new JLabel("Number of colors");
        nbColorsSlider.setMajorTickSpacing(1);
        nbColorsSlider.setPaintTicks(true);
        nbColorsSlider.setPaintLabels(true);
        nbColorsSlider.setSnapToTicks(true);
        nbColorsSlider.setAlignmentX(CENTER_ALIGNMENT);

        JLabel nbColorsInCombinationLabel = new JLabel("Number of colors in the solution");
        nbColorsInCombinationSlider.setMajorTickSpacing(1);
        nbColorsInCombinationSlider.setPaintTicks(true);
        nbColorsInCombinationSlider.setPaintLabels(true);
        nbColorsInCombinationSlider.setSnapToTicks(true);
        nbColorsInCombinationSlider.setAlignmentX(CENTER_ALIGNMENT);

        JLabel nbAttemptsLabel = new JLabel("Number of attempts");
        nbAttemptsSlider.setMajorTickSpacing(1);
        nbAttemptsSlider.setPaintTicks(true);
        nbAttemptsSlider.setPaintLabels(true);
        nbAttemptsSlider.setSnapToTicks(true);
        nbAttemptsSlider.setAlignmentX(CENTER_ALIGNMENT);

        startButton.setAlignmentX(CENTER_ALIGNMENT);

        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        this.add(startButton);
        this.add(usernameLabel);
        this.add(usernameTextField);
        this.add(gameModeLabel);
        this.add(gameModeComboBox);
        this.add(nbRoundsLabel);
        this.add(nbRoundsSlider);
        this.add(nbColorsLabel);
        this.add(nbColorsSlider);
        this.add(nbColorsInCombinationLabel);
        this.add(nbColorsInCombinationSlider);
        this.add(nbAttemptsLabel);
        this.add(nbAttemptsSlider);

        setVisible(true);
    }
}
