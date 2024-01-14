package views;

import controllers.GameController;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Label;

/**
 * Represents the end window of the MasterMind game.
 */
public class EndWindow extends JFrame {

    private JPanel _mainPanel = new JPanel();
    private JPanel _scorePanel = new JPanel();
    private JPanel _buttonsPanel = new JPanel();

    /**
     * Constructor for the EndWindow class.
     * @param gameController The GameController associated with the game.
     */
    public EndWindow(GameController gameController) {
        super("Game");
        setSize(450, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Main Panel
        String endMessage = "You won " + gameController.getNbRoundsWon() + " round(s) out of " + gameController.getNbRounds() +
                ". Your final score is " + gameController.getGame().CalculateScoreGame();
        _mainPanel.add(new Label(endMessage));
        _mainPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(_mainPanel, gbc);

        // Buttons Panel
        _buttonsPanel.setLayout(new BoxLayout(_buttonsPanel, BoxLayout.Y_AXIS));

        JButton closeAppButton = new JButton("Close Application");
        closeAppButton.addActionListener(e -> {
            dispose();
        });

        JButton restartGameButton = new JButton("Play Again");
        restartGameButton.addActionListener(e -> {
            gameController.startGame(gameController.getUsername(), gameController.getGameMode(),
                    gameController.getNbRounds(), gameController.getNbColors(),
                    gameController.getNbColorsInCombination(), gameController.getNbAttempts());
            dispose();
        });

        _buttonsPanel.add(closeAppButton);
        _buttonsPanel.add(restartGameButton);
        _buttonsPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(_buttonsPanel, gbc);

        // Score Panel
        _scorePanel.setLayout(new GridLayout(gameController.getNbRounds(), 3));

        for (int i = 0; i < gameController.getNbRounds(); i++) {
            for (int j = 0; j < 3; j++) {
                JLabel label = new JLabel();
                label.setOpaque(true);
                label.setBackground(Color.WHITE);
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                _scorePanel.add(label);
            }
        }

        Component[] scorePanelComponent = _scorePanel.getComponents();
        JLabel[] scorePanelLabel = new JLabel[scorePanelComponent.length];

        for (int i = 0; i < scorePanelComponent.length; i++) {
            scorePanelLabel[i] = (JLabel) scorePanelComponent[i];
        }

        for (int i = 0; i < scorePanelComponent.length; i = i + 3) {
            scorePanelLabel[i].setText("Round " + ((i / 3) + 1));
            scorePanelLabel[i + 1].setText(gameController.getGame().getIsRoundWon(i / 3) ? "Won" : "Lost");
            scorePanelLabel[i + 2].setText(String.valueOf(gameController.getGame().getRoundScore(i / 3)));
        }

        _scorePanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(_scorePanel, gbc);

        setVisible(true);
    }
}
