package views;

import controllers.GameController;
import models.*;

import javax.swing.*;
import java.awt.*;

public class EndWindow extends JFrame
{

    //private GameController _gameController;
    JPanel _mainPanel = new JPanel();
    JPanel _scorePanel = new JPanel();
    JPanel _buttonsPanel = new JPanel();

    public EndWindow(GameController gameController)
    {
        super("Game");
        setSize(450, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //main Panel

        String endMessage = "vous avez gagné " + gameController.getNbRoundsWon() + "round(s) sur " + gameController.getNbRounds() + ". votre score final est de "+ gameController.getGame().CalculateScoreGame();
        _mainPanel.add(new Label(endMessage));
        _mainPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLUE));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(_mainPanel, gbc);


        //buttons Panel

        _buttonsPanel.setLayout(new BoxLayout(_buttonsPanel, BoxLayout.Y_AXIS));

        JButton closeAppButton = new JButton("fermer l'application");
        closeAppButton.addActionListener(e -> {
            dispose();
        });

        JButton restartGameButton = new JButton("rejouer");
        restartGameButton.addActionListener(e -> {
            gameController.startGame(gameController.getUsername(),gameController.getGameMode(),gameController.getNbRounds(),gameController.getNbColors(),gameController.getNbColorsInCombination(),gameController.getNbAttempts());
            dispose();
        });

        _buttonsPanel.add(closeAppButton);
        _buttonsPanel.add(restartGameButton);
        _buttonsPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.RED));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(_buttonsPanel, gbc);


        // score panel

        _scorePanel.setLayout(new GridLayout(gameController.getNbRounds(),3));

        for(int i = 0; i < gameController.getNbRounds(); i++)
        {
            for(int j = 0; j < 3; j++)
            {
                JLabel label = new JLabel();
                label.setOpaque(true);
                label.setBackground(Color.WHITE);
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                _scorePanel.add(label);
            }
        }

        Component[] scorePanelComponent = _scorePanel.getComponents();
        JLabel[] scorePanelLabel = new JLabel[scorePanelComponent.length];

        for(int i = 0; i < scorePanelComponent.length; i++)
        {
            scorePanelLabel[i] = (JLabel)scorePanelComponent[i];
        }

        for(int i = 0; i < scorePanelComponent.length; i=i+3)
        {
            scorePanelLabel[i].setText("round " + (i/3));
            scorePanelLabel[i+1].setText(gameController.getGame().getIsRoundWon(i/3) ? "gagné" :"perdu");
            scorePanelLabel[i+2].setText(String.valueOf(gameController.getGame().getRoundScore(i/3)));
        }

        _scorePanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.GREEN));
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(_scorePanel, gbc);

        setVisible(true);
    }
}
