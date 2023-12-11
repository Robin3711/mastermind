package views;

import controllers.MasterMindFacade;
import models.GameMode;
import models.MasterMindObserver;

import javax.swing.*;
import java.awt.*;

public class SettingsWindowView extends JFrame implements MasterMindObserver {

    public SettingsWindowView(MasterMindFacade masterMindFacade) {
        super("Settings");
        setSize(600, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        JButton backButton = new JButton("Back");
        backButton.setAlignmentX(CENTER_ALIGNMENT);

        // SettingsWindowView must be self-closing
        backButton.addActionListener(e -> {
            this.dispose();
        });

        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.add(backButton, BorderLayout.CENTER);

        // JComboBox which will be used to choose the game mode between "Classical", "Easy" and "Numeric" which are in the GameMode enum
        JComboBox<GameMode> gameModeComboBox = new JComboBox<>(GameMode.values());
        gameModeComboBox.setAlignmentX(CENTER_ALIGNMENT);

        // JSlider which will be used to choose the number of rounds between 3 and 5
        JSlider nbRoundsSlider = new JSlider(JSlider.HORIZONTAL, 3, 5, 3);
        nbRoundsSlider.setMajorTickSpacing(1);
        nbRoundsSlider.setPaintTicks(true);
        nbRoundsSlider.setPaintLabels(true);
        nbRoundsSlider.setSnapToTicks(true);
        nbRoundsSlider.setAlignmentX(CENTER_ALIGNMENT);

        // JSlider which will be used to choose the number of colors between 4 and 6
        JSlider nbColorsSlider = new JSlider(JSlider.HORIZONTAL, 4, 6, 4);
        nbColorsSlider.setMajorTickSpacing(1);
        nbColorsSlider.setPaintTicks(true);
        nbColorsSlider.setPaintLabels(true);
        nbColorsSlider.setSnapToTicks(true);
        nbColorsSlider.setAlignmentX(CENTER_ALIGNMENT);

        // JSlider which will be used to choose the number of colors in the solution between 8 and 12
        JSlider nbColorsInSolutionSlider = new JSlider(JSlider.HORIZONTAL, 8, 12, 8);
        nbColorsInSolutionSlider.setMajorTickSpacing(1);
        nbColorsInSolutionSlider.setPaintTicks(true);
        nbColorsInSolutionSlider.setPaintLabels(true);
        nbColorsInSolutionSlider.setSnapToTicks(true);
        nbColorsInSolutionSlider.setAlignmentX(CENTER_ALIGNMENT);

        // JSlider which will be used to choose the number of attempts between 10 and 12
        JSlider nbAttemptsSlider = new JSlider(JSlider.HORIZONTAL, 10, 12, 10);
        nbAttemptsSlider.setMajorTickSpacing(1);
        nbAttemptsSlider.setPaintTicks(true);
        nbAttemptsSlider.setPaintLabels(true);
        nbAttemptsSlider.setSnapToTicks(true);
        nbAttemptsSlider.setAlignmentX(CENTER_ALIGNMENT);

        // Button which will be used to save the settings
        JButton saveButton = new JButton("Save");
        saveButton.setAlignmentX(CENTER_ALIGNMENT);

        // NEW
        saveButton.addActionListener(e -> {
            // Test
            // Display the type of the selected item
            System.out.println(gameModeComboBox.getSelectedItem().getClass().getName());
            masterMindFacade.saveSettings((GameMode) gameModeComboBox.getSelectedItem(), nbRoundsSlider.getValue(), nbColorsSlider.getValue(), nbColorsInSolutionSlider.getValue(), nbAttemptsSlider.getValue());
            this.dispose();
        });
        // END NEW

        this.add(gameModeComboBox, BorderLayout.CENTER);
        this.add(nbRoundsSlider, BorderLayout.CENTER);
        this.add(nbColorsSlider, BorderLayout.CENTER);
        this.add(nbColorsInSolutionSlider, BorderLayout.CENTER);
        this.add(nbAttemptsSlider, BorderLayout.CENTER);
        this.add(saveButton, BorderLayout.CENTER);

        setVisible(true);
    }
}