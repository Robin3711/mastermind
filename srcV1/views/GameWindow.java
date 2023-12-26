package views;

import controllers.GameController;
import models.*;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class GameWindow extends JFrame implements GameObserver {
    // dictionary mapping PawnColor to Color
    Map<PawnColor, Color> _pawnColorToColor = new HashMap<>() {{
        put(PawnColor.RED, Color.RED);
        put(PawnColor.GREEN, Color.GREEN);
        put(PawnColor.BLUE, Color.BLUE);
        put(PawnColor.YELLOW, Color.YELLOW);
        put(PawnColor.ORANGE, Color.ORANGE);
        put(PawnColor.PINK, Color.PINK);
        put(PawnColor.LIGHT_GRAY, Color.LIGHT_GRAY);
        put(PawnColor.MAGENTA, Color.MAGENTA);
    }};
    JPanel _combinationPanel = new JPanel();
    JPanel _boardPanel = new JPanel();
    JPanel _cluesPanel = new JPanel();
    int _attemptIndex = 1;
    private GameController _gameController;

    public GameWindow(GameController gameController) {
        super("Game");
        setSize(900, 900);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        _gameController = gameController;

        JButton forfeitRoundButton = new JButton("Forfeit round");
        JButton forfeitGameButton = new JButton("Forfeit game");
        JButton resetButton = new JButton("Reset combination");
        JButton submitButton = new JButton("Submit combination");

        this.setLayout(new GridBagLayout());

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));

        JPanel colorsPanel = new JPanel();
        colorsPanel.setLayout(new FlowLayout());

        // gamePanel has nbAttempts rows and nbColorsInSolution columns, stocker infos dans variables ppour réutiliser
        _boardPanel.setLayout(new GridLayout(10, 4));

        // next to each line of boardPanel are the indices corresponding to the line stored in cluesPanel
        // clues can be in the form of dots of different colors or numbers
        _cluesPanel.setLayout(new GridLayout(10, 4));

        //JPanel combinationPanel = new JPanel();
        // combinationPanel has 1 row and nbColorsInSolution columns
        _combinationPanel.setLayout(new GridLayout(1, 4));

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
            PawnColor[] pawnColors = new PawnColor[4];
            for (int i = 0; i < 4; i++) {
                System.out.println("Prout");
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

        buttonsPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLUE));
        _cluesPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.RED));
        _combinationPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.GREEN));
        colorsPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.PINK));
        _boardPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLACK));

        // I'd like a 9x9 grid so that each cell is 100x100
        // I'd like boardPanel to be top right and 600x600
        // I'd like cluesPanel to be to the right of boardPanel and 600x100.
        // I'd like combinationPanel to be below boardPanel and 200x600.
        // I'd like colorsPanel to be below combinationPanel and 100x600.
        // I'd like buttonsPanel to be to the right of cluesPanel and 900x200.

        GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(new GridBagLayout());

        // Paramètres communs pour tous les composants
        gbc.fill = GridBagConstraints.BOTH;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Prend deux colonnes
        gbc.gridheight = 3; // Prend trois rangées
        gbc.weightx = 0.6;
        gbc.weighty = 0.7;
        this.add(_boardPanel, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1; // Prend une colonne
        gbc.gridheight = 3; // Augmente la hauteur pour être égal à boardPanel
        gbc.weightx = 0.2;
        gbc.weighty = 0.7;
        this.add(_cluesPanel, gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridwidth = 1; // Prend une colonne
        gbc.gridheight = 4; // S'étend sur toute la hauteur de la fenêtre
        gbc.weightx = 0.2;
        gbc.weighty = 0;
        this.add(buttonsPanel, gbc);

        // Assurez-vous que la largeur maximale des boutons est égale à la largeur du panel
        //forfeitRoundButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, forfeitRoundButton.getMinimumSize().height));
        //forfeitGameButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, forfeitGameButton.getMinimumSize().height));
        //resetButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, resetButton.getMinimumSize().height));
        //submitButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, submitButton.getMinimumSize().height));

        //forfeitRoundButton.setAlignmentX(CENTER_ALIGNMENT);
        //forfeitGameButton.setAlignmentX(CENTER_ALIGNMENT);
        //resetButton.setAlignmentX(CENTER_ALIGNMENT);
        //submitButton.setAlignmentX(CENTER_ALIGNMENT);

        // Ajoutez un peu d'espace vertical entre les boutons
        //buttonsPanel.add(Box.createVerticalGlue());

        //buttonsPanel.add(forfeitRoundButton);
        //buttonsPanel.add(forfeitGameButton);
        //buttonsPanel.add(resetButton);
        //buttonsPanel.add(submitButton);

        // Ajoutez un autre espace vertical pour pousser les boutons vers le haut
        //buttonsPanel.add(Box.createVerticalGlue());

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // S'étend sur les deux colonnes sous le boardPanel
        gbc.gridheight = 1; // Prend une rangée
        gbc.weightx = 0.6;
        gbc.weighty = 0.2;
        this.add(_combinationPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2; // S'étend sur les deux colonnes sous le combinationPanel
        gbc.gridheight = 1; // Prend une rangée
        gbc.weightx = 0.6;
        gbc.weighty = 0.1;
        this.add(colorsPanel, gbc);

        buttonsPanel.add(forfeitRoundButton);
        buttonsPanel.add(forfeitGameButton);
        buttonsPanel.add(resetButton);
        buttonsPanel.add(submitButton);

        // Remplis boardPanel avec des JLabels
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 4; j++) {

                JLabel label = new JLabel();
                label.setOpaque(true);
                label.setBackground(Color.WHITE);
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                _boardPanel.add(label);
            }
        }

        // Remplis combinationPanel avec des JButton
        for (int i = 0; i < 4; i++) {
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

        // Remplis colorsPanel avec des JButton
        for (PawnColor pawnColor : _pawnColorToColor.keySet()) {
            JButton button = new JButton();
            button.setOpaque(true);
            button.setBackground(_pawnColorToColor.get(pawnColor));
            button.setPreferredSize(new Dimension(50, 50));
            button.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            colorsPanel.add(button);
        }

        // Remplis cluesPanel avec des JLabels
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 4; j++) {

                JLabel label = new JLabel();
                label.setOpaque(true);
                label.setBackground(Color.WHITE);
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                _cluesPanel.add(label);
            }
        }

        setVisible(true);
    }

    @Override
    public void onAttemptPerformed(Attempt attempt) {
        // Get the JLabels of the actual row of boardPanel
        Component[] boardPanelComponents = _boardPanel.getComponents();
        JLabel[] boardPanelLabels = new JLabel[4];
        for (int i = 0; i < 4; i++) {
            boardPanelLabels[i] = (JLabel) boardPanelComponents[boardPanelComponents.length - 4 * _attemptIndex + i];
        }

        // Set the background color of the JLabels to the background color of the buttons of the combinationPanel
        for (int i = 0; i < 4; i++) {
            boardPanelLabels[i].setBackground(_combinationPanel.getComponent(i).getBackground());
            System.out.println(_combinationPanel.getComponent(i).getBackground());
        }

        // Remove the colors of the combinationPanel
        for (Component component : _combinationPanel.getComponents()) {
            JButton button = (JButton) component;
            button.setText("");
            button.setBackground(Color.WHITE);
        }

        // Get the JLabels of the actual row of cluesPanel
        Component[] cluesPanelComponents = _cluesPanel.getComponents();
        JLabel[] cluesPanelLabels = new JLabel[4];
        for (int i = 0; i < 4; i++) {
            cluesPanelLabels[i] = (JLabel) cluesPanelComponents[cluesPanelComponents.length - 4 * _attemptIndex + i];
        }

        // Attempt has a getClues method which returns an array of Clue
        // Clue is an enum with 3 values: WELL_PLACED, MISPLACED, WRONG
        // If the value is WELL_PLACED, the JLabel should contain a red dot
        // If the value is MISPLACED, the JLabel should contain a blue dot
        // If the value is WRONG, the JLabel should contain a green dot
        for (int i = 0; i < 4; i++) {
            switch (attempt.getClues()[i]) {
                case WELL_PLACED:
                    cluesPanelLabels[i].setBackground(Color.RED);
                    break;
                case MISPLACED:
                    cluesPanelLabels[i].setBackground(Color.BLUE);
                    break;
                case WRONG:
                    cluesPanelLabels[i].setBackground(Color.GREEN);
                    break;
            }
        }

        System.out.println("debug1");

        _attemptIndex++;
    }

    @Override
    public void onRoundFinished() {
        System.out.println("debug2");
        // Display a message to the user
        JOptionPane.showMessageDialog(this, "Round finished");
        // When the user clicks OK, the round should be reset
        resetRound();
    }

    private void resetRound() {
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

    @Override
    public void onGameFinished() {
        // Display a message to the user
        JOptionPane.showMessageDialog(this, "Game finished");

        // When the user clicks OK, the game should be disposed and the menu window should be displayed
        new MenuWindow(_gameController);
        dispose();
    }
}
