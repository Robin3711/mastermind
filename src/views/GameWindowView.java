package views;

import models.MasterMind;
import models.MasterMindObserver;

import javax.swing.*;
import java.awt.*;

// NEW
import java.util.List;
// END NEW

public class GameWindowView extends JFrame implements MasterMindObserver {

    // NEW
    public GameWindowView(MasterMind masterMind) {
        super("Game");
        setSize(600, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        JButton forfeitRoundButton = new JButton("Forfeit this round");
        JButton forfeitGameButton = new JButton("Forfeit this game");
        JButton quitButton = new JButton("Quit MasterMind");

        this.setLayout(new BorderLayout());

        JPanel buttonsPanel = new JPanel();

        JPanel colorsPanel = new JPanel();

        JPanel gamePanel = new JPanel();

        // gamePanel has nbAttempts rows and nbColorsInSolution columns
        gamePanel.setLayout(new GridLayout(masterMind.getNbAttempts(), masterMind.getNbColorsInSolution()));

        // Put a button in all the cells of the gamePanel
        // Each button will have a label with the coordinates of the cell
        // List<Color> colors = masterMindFacade.generateDistinctColors(masterMind.getNbColors());

        for (int i = 0; i < masterMind.getNbAttempts(); i++) {
            for (int j = 0; j < masterMind.getNbColorsInSolution(); j++) {
                JButton button = new JButton(i + "," + j);

                // When a button is clicked, it will display a color palette to choose a color in the colorsPanel
                button.addActionListener(e -> {
                    // Remove all the components of the colorsPanel
                    colorsPanel.removeAll();

                    // Add a button for each color in the colorsPanel
                    for (int k = 0; k < masterMind.getNbColors(); k++) {
                        JButton colorButton = new JButton();
                        colorButton.setBackground(Color.RED);
                        colorButton.setPreferredSize(new Dimension(50, 50));

                        // When a colorButton is clicked, it will change the background color of the button that was clicked in the gamePanel
                        colorButton.addActionListener(e1 -> {
                            button.setBackground(colorButton.getBackground());
                        });

                        colorsPanel.add(colorButton);
                    }

                    // Repaint the colorsPanel to make the changes visible
                    colorsPanel.revalidate();
                    colorsPanel.repaint();
                });

                gamePanel.add(button);
            }
        }

        // Add black borders to the gamePanel to make it easier to see
        gamePanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLACK));

        this.add(buttonsPanel, BorderLayout.EAST);
        this.add(colorsPanel, BorderLayout.SOUTH);
        this.add(gamePanel, BorderLayout.CENTER);

        setVisible(true);
    }
    // END NEW
}