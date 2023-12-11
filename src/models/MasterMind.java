package models;

import java.util.ArrayList;
import java.util.List;

public class MasterMind {
    private List<MasterMindObserver> ObserverList = new ArrayList<>();
    // NEW
    private GameMode gameMode = GameMode.CLASSIC;
    private int nbRounds = 3;
    private int nbColors = 8;
    private int nbColorsInSolution = 4;
    private int nbAttempts = 10;
    // END NEW
    public void addObserver(MasterMindObserver observer) {
        ObserverList.add(observer);
    }

    // NEW
    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    public void setNbRounds(int nbRounds) {
        this.nbRounds = nbRounds;
    }

    public void setNbColors(int nbColors) {
        this.nbColors = nbColors;
    }

    public void setNbColorsInSolution(int nbColorsInSolution) {
        this.nbColorsInSolution = nbColorsInSolution;
    }

    public void setNbAttempts(int nbAttempts) {
        this.nbAttempts = nbAttempts;
    }

    public int getNbAttempts() {
        return nbAttempts;
    }

    public int getNbColorsInSolution() {
        return nbColorsInSolution;
    }

    public int getNbColors() {
        return nbColors;
    }
    // END NEW
}