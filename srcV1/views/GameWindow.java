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
    int _attemptIndex = 1;

    public GameWindow(GameController gameController) {
        super("Game");
        setSize(900, 900);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

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

        JPanel cluesPanel = new JPanel();
        // next to each line of boardPanel are the indices corresponding to the line stored in cluesPanel
        // clues can be in the form of dots of different colors or numbers
        cluesPanel.setLayout(new GridLayout(10, 3));

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
                pawnColors[i] = PawnColor.valueOf(((JButton) _combinationPanel.getComponent(i)).getText());
            }

            // Submit pawnColors
            gameController.submitCombination(pawnColors);
        });

        buttonsPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLUE));
        cluesPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.RED));
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
        this.add(cluesPanel, gbc);

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

        setVisible(true);
    }

    private Component getComponentByName(Container container, String name) {
        for (Component component : container.getComponents()) {
            if (component.getName().equals(name)) {
                return component;
            }
        }
        return null;
    }

    @Override
    public void onAttemptPerformed(Attempt attempt) {
        // Remove the colors of the combinationPanel
        for (Component component : _combinationPanel.getComponents()) {
            JButton button = (JButton) component;
            button.setText("");
            button.setBackground(Color.WHITE);
        }

        // Get the JLabels of the actual row of boardPanel
        Component[] components = _boardPanel.getComponents();
        JLabel[] labels = new JLabel[4];
        for (int i = 0; i < 4; i++) {
            labels[i] = (JLabel) components[components.length - 4 * _attemptIndex + i];
        }

        // Set the background color of the JLabels to the background color of the buttons of the combinationPanel
        for (int i = 0; i < 4; i++) {
            labels[i].setBackground(_combinationPanel.getComponent(i).getBackground());
        }

        _attemptIndex++;
    }

    @Override
    public void onRoundFinished() {

    }
}
