package views;

import models.GameObserver;

import javax.swing.*;

public class GameWindow extends JFrame implements GameObserver {
    public GameWindow() {
        super("Game");
        setSize(900, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        setVisible(true);
    }
}
