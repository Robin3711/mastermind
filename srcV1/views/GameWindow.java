package views;

import controllers.GameController;
import models.GameObserver;
import models.PawnColor;
import models.Attempt;
import models.Clue;
import models.GameMode;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents the game window of the MasterMind game.
 */
public class GameWindow extends JFrame implements GameObserver {
    // Dictionary mapping PawnColor to Color
    Map<PawnColor, Color> _pawnColorToColor = new HashMap<>() {{
        put(PawnColor.RED, Color.RED);
        put(PawnColor.GREEN, Color.GREEN);
        put(PawnColor.BLUE, Color.BLUE);
        put(PawnColor.YELLOW, Color.YELLOW);
        put(PawnColor.CYAN, Color.CYAN);
        put(PawnColor.PINK, Color.PINK);
        put(PawnColor.LIGHT_GRAY, Color.LIGHT_GRAY);
        put(PawnColor.MAGENTA, Color.MAGENTA);
    }};
    JPanel _combinationPanel = new JPanel();
    JPanel _boardPanel = new JPanel();
    JPanel _cluesPanel = new JPanel();
    int _attemptIndex = 1;
    private final GameController _gameController;

    boolean _isNumeric = false; // true if game mode is numeric

    /**
     * Constructor for the GameWindow class.
     * @param gameController The GameController associated with the game.
     */
    public GameWindow(GameController gameController) {
        super("Game");
        setSize(900, 900);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        _gameController = gameController;

        JButton forfeitRoundButton = new JButton("Forfeit round");
        forfeitRoundButton.addActionListener(e -> {
            _gameController.forfeitCurrentRound();
            onRoundFinished();
        });

        JButton forfeitGameButton = new JButton("Forfeit game");
        forfeitGameButton.addActionListener(e -> {
            onGameFinished();
        });

        JButton resetButton = new JButton("Reset combination");
        JButton submitButton = new JButton("Submit combination");

        this.setLayout(new GridBagLayout());

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));

        JPanel colorsPanel = new JPanel();
        colorsPanel.setLayout(new FlowLayout());

        // gamePanel has nbAttempts rows and nbColorsInSolution columns, stocker infos dans variables ppour réutiliser
        _boardPanel.setLayout(new GridLayout(gameController.getNbAttempts(), gameController.getNbColorsInCombination()));

        if(gameController.getGameMode() == GameMode.NUMERIC)
        {
            _cluesPanel.setLayout(new GridLayout(gameController.getNbAttempts(), 3));
            _isNumeric = true;
        }
        else
        {
            _cluesPanel.setLayout(new GridLayout(gameController.getNbAttempts(), gameController.getNbColorsInCombination()));
            _isNumeric = false;
        }

        _combinationPanel.setLayout(new GridLayout(1, gameController.getNbColorsInCombination()));

        resetButton.addActionListener(e -> {
            // Remove the colors of the combinationPanel
            for (Component component : _combinationPanel.getComponents()) {
                JButton button = (JButton) component;
                button.setText("");
                button.setBackground(Color.WHITE);
            }
        });

        submitButton.addActionListener(e -> {
            // Create a PawnColor array
            PawnColor[] pawnColors = new PawnColor[gameController.getNbColorsInCombination()];
            for (int i = 0; i < pawnColors.length; i++) {
                // If the button has no text, it means that the user didn't choose a color for this pawn
                if (((JButton) _combinationPanel.getComponent(i)).getText().equals("")) {
                    JOptionPane.showMessageDialog(this, "You must choose a color for each pawn.");
                    return;
                }

                pawnColors[i] = PawnColor.valueOf(((JButton) _combinationPanel.getComponent(i)).getText());
            }

            // Submit pawnColors
            gameController.submitCombination(pawnColors);
        });

        GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(new GridBagLayout());

        // Common parameters for all the components
        gbc.fill = GridBagConstraints.BOTH;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Fit two columns
        gbc.gridheight = 3; // Fit three rows
        gbc.weightx = 0.6;
        gbc.weighty = 0.7;
        this.add(_boardPanel, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 3;
        gbc.weightx = 0.2;
        gbc.weighty = 0.7;
        this.add(_cluesPanel, gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 4;
        gbc.weightx = 0.2;
        gbc.weighty = 0;
        this.add(buttonsPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 0.6;
        gbc.weighty = 0.2;
        this.add(_combinationPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 0.6;
        gbc.weighty = 0.1;
        this.add(colorsPanel, gbc);
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        buttonsPanel.add(forfeitRoundButton,CENTER_ALIGNMENT);
        buttonsPanel.add(forfeitGameButton,CENTER_ALIGNMENT);
        buttonsPanel.add(resetButton,CENTER_ALIGNMENT);
        buttonsPanel.add(submitButton,CENTER_ALIGNMENT);

        // Fill boardPanel with JLabels
        for (int i = 0; i < gameController.getNbAttempts(); i++) {
            for (int j = 0; j < gameController.getNbColorsInCombination(); j++)
            {
                JLabel label = new JLabel();
                label.setOpaque(true);
                label.setBackground(Color.WHITE);
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                _boardPanel.add(label);
            }
        }

        // Fill combinationPanel with JButtons
        for (int i = 0; i < gameController.getNbColorsInCombination(); i++) {
            JButton button = new JButton();
            button.setOpaque(true);
            button.setBackground(Color.WHITE);
            button.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            // When a button is clicked, it will display a color palette to choose a color in the colorsPanel
            button.addActionListener(e -> {
                // Remove all the components of the colorsPanel
                colorsPanel.removeAll();

                // Add a button for each color in the colorsPanel
                for (PawnColor color : _pawnColorToColor.keySet()) {
                    JButton colorButton = new JButton();
                    colorButton.setBackground(_pawnColorToColor.get(color));
                    colorButton.setPreferredSize(new Dimension(50, 50));

                    // When a colorButton is clicked, it will change the background color of the button that was clicked in the gamePanel
                    colorButton.addActionListener(e1 -> {
                        button.setText(color.toString());
                        button.setBackground(colorButton.getBackground());
                    });

                    colorsPanel.add(colorButton);
                }

                // Repaint the colorsPanel to make the changes visible
                colorsPanel.revalidate();
                colorsPanel.repaint();
            });

            _combinationPanel.add(button);
        }

        // Fill colorsPanel with JButtons
        for (PawnColor pawnColor : _pawnColorToColor.keySet()) {
            JButton button = new JButton();
            button.setOpaque(true);
            button.setBackground(_pawnColorToColor.get(pawnColor));
            button.setPreferredSize(new Dimension(50, 50));
            button.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            colorsPanel.add(button);
        }

        // Fill cluesPanel with JLabels
        for (int i = 0; i < gameController.getNbAttempts(); i++) {
            for (int j = 0; j < (_isNumeric ? 3 : gameController.getNbColorsInCombination()); j++) {

                JLabel label = new JLabel();
                label.setOpaque(true);
                label.setBackground(Color.WHITE);
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                _cluesPanel.add(label);
            }
        }

        setVisible(true);
    }

    /**
     * Updates the view when an attempt is performed.
     * @param attempt The attempt that was performed.
     */
    @Override
    public void onAttemptPerformed(Attempt attempt) {
        // Get the JLabels of the actual row of boardPanel
        Component[] boardPanelComponents = _boardPanel.getComponents();
        JLabel[] boardPanelLabels = new JLabel[_gameController.getNbColorsInCombination()];
        for (int i = 0; i < boardPanelLabels.length; i++) {
            boardPanelLabels[i] = (JLabel) boardPanelComponents[boardPanelComponents.length - boardPanelLabels.length * _attemptIndex + i];
        }

        // Set the background color of the JLabels to the background color of the buttons of the combinationPanel
        for (int i = 0; i < boardPanelLabels.length; i++) {
            boardPanelLabels[i].setBackground(_combinationPanel.getComponent(i).getBackground());
        }

        // Remove the colors of the combinationPanel
        for (Component component : _combinationPanel.getComponents()) {
            JButton button = (JButton) component;
            button.setText("");
            button.setBackground(Color.WHITE);
        }

        // Get the JLabels of the actual row of cluesPanel
        Component[] cluesPanelComponents = _cluesPanel.getComponents();
        JLabel[] cluesPanelLabels = new JLabel[(_isNumeric ? 3 : _gameController.getNbColorsInCombination())];
        for (int i = 0; i < cluesPanelLabels.length; i++) {
            cluesPanelLabels[i] = (JLabel) cluesPanelComponents[cluesPanelComponents.length - cluesPanelLabels.length * _attemptIndex + i];
        }

        if (_gameController.getGameMode() == GameMode.NUMERIC) {
            // Display numeric clues
            for (int i = 0; i < Clue.values().length; i++) {
                int numericClue = attempt.getNumericClues()[i];
                // Display numeric clues in the JLabels
                cluesPanelLabels[i].setText(Integer.toString(numericClue));
            }
        } else {
            // Display colored clues
            for (int i = 0; i < _gameController.getNbColorsInCombination(); i++) {
                switch (attempt.getClues()[i]) {
                    case WELL_PLACED:
                        cluesPanelLabels[i].setBackground(Color.RED);
                        break;
                    case MISPLACED:
                        cluesPanelLabels[i].setBackground(Color.GRAY);
                        break;
                    case WRONG:
                        cluesPanelLabels[i].setBackground(Color.WHITE);
                        break;
                }
            }
        }

        _attemptIndex++;
    }

    /**
     * Updates the view when a round is finished.
     */
    @Override
    public void onRoundFinished() {
        System.out.println("roundFini");
        JOptionPane.showMessageDialog(this, "Round finished");
        // When the user clicks OK, the round should be reset
        resetRound();
    }

    /**
     * Resets the round.
     */
    private void resetRound() {
        System.out.println("resetRound");
        // Reset the attempt index
        _attemptIndex = 1;

        // Reset the background color of the JLabels of the boardPanel
        Component[] boardPanelComponents = _boardPanel.getComponents();
        for (int i = 0; i < boardPanelComponents.length; i++) {
            JLabel label = (JLabel) boardPanelComponents[i];
            label.setBackground(Color.WHITE);
        }

        // Reset the background color of the JLabels of the cluesPanel
        Component[] cluesPanelComponents = _cluesPanel.getComponents();
        for (int i = 0; i < cluesPanelComponents.length; i++) {
            JLabel label = (JLabel) cluesPanelComponents[i];
            label.setBackground(Color.WHITE);
        }

        // Notify the controller that the round has been reset
        _gameController.nextRound();
    }

    /**
     * Updates the view when the game is finished.
     */
    @Override
    public void onGameFinished() {
        dispose();
    }
}

