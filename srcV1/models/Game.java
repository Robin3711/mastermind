package models;

import views.GameWindow;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Round[] _rounds;
    private List<GameObserver> _observers;

    public Game(int nbRounds) {
        System.out.println("Game created");
        this._rounds = new Round[nbRounds];
        _observers = new ArrayList<>();
    }

    public void nextRound(int nbAttempts, int nbColorsInCombination, GameMode gameMode) {
        if (!isGameOver()) {
            createNextRound(nbAttempts, nbColorsInCombination, gameMode);
        } else {
            notifyGameFinished();
        }
    }

    private void createNextRound(int nbAttempts, int nbColorsInCombination, GameMode gameMode) {
        int currentRoundIndex = getCurrentRoundNb();
        System.out.println("Round " + (currentRoundIndex) + " launched");
        _rounds[currentRoundIndex] = new Round(nbAttempts, nbColorsInCombination, gameMode);
    }

    public Round getCurrentRound() {
        return _rounds[getCurrentRoundNb()];
    }

    private int getCurrentRoundNb()
    {
        if(_rounds[0] == null)
        {
            System.out.println("aucun round ?!");
            return 0;
        }
        for(int i = 1; i < _rounds.length; i++)
        {
            if(_rounds[i]==null)
            {
                System.out.println("vous etes au round"+ (i-1));
                return i -1;
            }
        }

        System.out.println("vous etes au dernier round, le round "+ (_rounds.length-1));
        return _rounds.length-1;
    }


    private void endGame() {
        return;
    }

    public int CalculateScoreGame()
    {
        int score = 0;
        for (Round round : _rounds) {
            score += round.calculateScoreRound();
        }
        return score;
    }

    public void addObserver(GameObserver gameObserver) {
        _observers.add(gameObserver);
    }

    private void notifyAttemptPerformed(Attempt attempt) {
        for (GameObserver gameObserver: _observers) {
            gameObserver.onAttemptPerformed(attempt);
        }
    }

    private void notifyRoundFinished() {
        for (GameObserver gameObserver: _observers) {
            gameObserver.onRoundFinished();
        }
    }

    public void submitCombination(Combination combination)
    {
        Round currentRound = getCurrentRound();
        Attempt attempt = currentRound.submitCombination(combination);
        notifyAttemptPerformed(attempt);
        if (currentRound.isRoundOver())
        {
            /*if (isGameOver()) {
                notifyGameFinished();
            } else {*/
                notifyRoundFinished();
        }
    }

    private void notifyGameFinished() {
        for (GameObserver gameObserver: _observers) {
            gameObserver.onGameFinished();
        }
    }

    public int getNbRoundsWon()
    {
        int nbRoundsWon = 0;

        for(int i = 0; i < _rounds.length; i++)
        {
            if(_rounds[i].getIsWon())
            {
                nbRoundsWon++;
            }
        }
        return nbRoundsWon;
    }

    public boolean isGameOver()
    {
        return (getCurrentRoundNb() == _rounds.length-1 && getCurrentRound().isRoundOver());
    }
}