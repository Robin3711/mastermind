package views;

import models.MasterMind;
import models.MasterMindObserver;

import javax.swing.*;
import java.awt.*;

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



        JPanel gamePanel = new JPanel();

        // gamePanel has nbAttempts rows and nbColorsInSolution columns
        gamePanel.setLayout(new GridLayout(masterMind.getNbAttempts(), masterMind.getNbColorsInSolution()));

        // Put a button in all the cells of the gamePanel
        // Each button will have a label with the coordinates of the cell
        for (int i = 0; i < masterMind.getNbAttempts(); i++) {
            for (int j = 0; j < masterMind.getNbColorsInSolution(); j++) {
                JButton button = new JButton(i + "," + j);
                gamePanel.add(button);
            }
        }

        // Add black borders to the gamePanel to make it easier to see
        gamePanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLACK));

        this.add(buttonsPanel, BorderLayout.NORTH);
        this.add(gamePanel, BorderLayout.CENTER);

        setVisible(true);
    }
    // END NEW
}