package views;

import controllers.GameController;
import models.GameMode;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSlider;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComponent;
import javax.swing.border.EmptyBorder;

/**
 * Represents the menu window for configuring the MasterMind game.
 */
public class MenuWindow extends JFrame {

    /**
     * Constructor for the MenuWindow class.
     * @param gameController The GameController associated with the game.
     */
    public MenuWindow(GameController gameController) {
        super("Menu");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));

        JTextField usernameTextField = new JTextField("Anonymous");
        JComboBox<GameMode> gameModeComboBox = new JComboBox<>(GameMode.values());
        JSlider nbRoundsSlider = createSlider(1, 5, 3);
        JSlider nbColorsSlider = createSlider(4, 8, 8);
        JSlider nbColorsInCombinationSlider = createSlider(4, 6, 4);
        JSlider nbAttemptsSlider = createSlider(1, 12, 10);

        JButton startButton = new JButton("Start");

        startButton.addActionListener(e -> {
            String username = usernameTextField.getText();
            GameMode gameMode = (GameMode) gameModeComboBox.getSelectedItem();
            int nbRounds = nbRoundsSlider.getValue();
            int nbColors = nbColorsSlider.getValue();
            int nbColorsInCombination = nbColorsInCombinationSlider.getValue();
            int nbAttempts = nbAttemptsSlider.getValue();
            gameController.startGame(username, gameMode, nbRounds, nbColors, nbColorsInCombination, nbAttempts);
            dispose();
        });

        contentPane.add(createLabeledComponent("Username", usernameTextField));
        contentPane.add(Box.createVerticalStrut(20));
        contentPane.add(createLabeledComponent("Game mode", gameModeComboBox));
        contentPane.add(Box.createVerticalStrut(25));
        contentPane.add(createLabeledComponent("Number of rounds", nbRoundsSlider));
        contentPane.add(Box.createVerticalStrut(10));
        contentPane.add(createLabeledComponent("Number of colors", nbColorsSlider));
        contentPane.add(Box.createVerticalStrut(10));
        contentPane.add(createLabeledComponent("Number of colors in the solution", nbColorsInCombinationSlider));
        contentPane.add(Box.createVerticalStrut(10));
        contentPane.add(createLabeledComponent("Number of attempts", nbAttemptsSlider));
        contentPane.add(Box.createVerticalStrut(10));

        // Centering the Start button
        Box horizontalBox = Box.createHorizontalBox();
        horizontalBox.add(Box.createHorizontalGlue());
        horizontalBox.add(startButton);
        horizontalBox.add(Box.createHorizontalGlue());
        contentPane.add(horizontalBox);

        contentPane.setAlignmentY(CENTER_ALIGNMENT);
        contentPane.setAlignmentX(CENTER_ALIGNMENT);
        setContentPane(contentPane);
        setVisible(true);
    }

    /**
     * Creates a labeled component.
     * @param label The label for the component.
     * @param component The component.
     * @return The labeled component.
     */
    private JPanel createLabeledComponent(String label, JComponent component) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(new JLabel(label), BorderLayout.NORTH);
        panel.add(component, BorderLayout.CENTER);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return panel;
    }

    /**
     * Creates a slider.
     * @param min The minimum value for the slider.
     * @param max The maximum value for the slider.
     * @param initialValue The initial value for the slider.
     * @return The slider.
     */
    private JSlider createSlider(int min, int max, int initialValue) {
        JSlider slider = new JSlider(JSlider.HORIZONTAL, min, max, initialValue);
        slider.setMajorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setSnapToTicks(true);
        return slider;
    }
}