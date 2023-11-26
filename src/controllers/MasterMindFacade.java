package controllers;

import models.ColorGenerator;
import models.GameMode;
import models.MasterMind;
import views.GameWindowView;
import views.SettingsWindowView;

// NEW
import java.util.List;
import java.awt.Color;
// END NEW

public class MasterMindFacade {
    private MasterMind masterMind;
    public MasterMindFacade(MasterMind masterMind) {
        this.masterMind = masterMind;
    }

    public void quitMasterMind() {
        System.exit(0);
    }

    public void openSettingsWindow() {
        SettingsWindowView settingsWindow = new SettingsWindowView(this);
    }

    // NEW
    public void saveSettings(GameMode gameMode, int nbRounds, int nbColors, int nbColorsInSolution, int nbAttempts) {
        masterMind.setGameMode(gameMode);
        masterMind.setNbRounds(nbRounds);
        masterMind.setNbColors(nbColors);
        masterMind.setNbColorsInSolution(nbColorsInSolution);
        masterMind.setNbAttempts(nbAttempts);
    }

    public void startGame() {
        GameWindowView gameWindowView = new GameWindowView(masterMind);
    }

    public List<Color> generateDistinctColors(int nbColors ) {
        return ColorGenerator.generateDistinctColors(nbColors);
    }
    // END NEW
}