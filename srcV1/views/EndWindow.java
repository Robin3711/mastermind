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
            // relancer le jeu
        });
    }
}
