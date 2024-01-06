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
        setSize(900, 900);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        this.setLayout(new GridBagLayout());

        //main Panel

        String endMessage = "vous avez gagné " + gameController.getNbRoundsWon() + "round(s) sur " + gameController.getNbRounds() + ".";
        _mainPanel.add(new Label(endMessage));


        //buttons Panel

        _buttonsPanel.setLayout(new BoxLayout(_buttonsPanel, BoxLayout.Y_AXIS));

        JButton closeAppButton = new JButton("fermer l'application");
        closeAppButton.addActionListener(e -> {
            dispose();
        });

        JButton restartGameButton = new JButton("rejouer");
        restartGameButton.addActionListener(e -> {
            gameController.startGame(gameController.getUsername(),gameController.getGameMode(),gameController.getNbRounds(),gameController.getNbColors(),gameController.getNbColorsInCombination(),gameController.getNbAttempts());
        });

        _buttonsPanel.add(closeAppButton);
        _buttonsPanel.add(restartGameButton);


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
            scorePanelLabel[i+2].setText(String.valueOf(gameController.getGame().getRoundScore(i%3)));
        }

        _mainPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLUE));
        _buttonsPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.RED));
        _scorePanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.GREEN));

        add(_mainPanel);
        add(_buttonsPanel);
        add(_scorePanel);
        setVisible(true);
    }
}
