package views;

import controllers.MasterMindFacade;
import models.MasterMindObserver;

import javax.swing.*;

public class MenuWindowView extends JFrame implements MasterMindObserver {

    public MenuWindowView(MasterMindFacade masterMindFacade) {
        super("Menu");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        JButton startButton = new JButton("Start");
        JButton settingsButton = new JButton("Settings");
        JButton quitButton = new JButton("Quit");

        startButton.setAlignmentX(CENTER_ALIGNMENT);
        settingsButton.setAlignmentX(CENTER_ALIGNMENT);
        quitButton.setAlignmentX(CENTER_ALIGNMENT);

        // NEW
        startButton.addActionListener(e -> {
            masterMindFacade.startGame();
        });
        // END NEW

        settingsButton.addActionListener(e -> {
            masterMindFacade.openSettingsWindow();
        });

        quitButton.addActionListener(e -> {
            masterMindFacade.quitMasterMind();
        });

        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        this.add(startButton);
        this.add(settingsButton);
        this.add(quitButton);

        setVisible(true);
    }
}