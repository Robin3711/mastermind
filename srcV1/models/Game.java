package models;

import views.GameWindow;

import java.util.ArrayList;
import java.util.List;

public class Game {
    //private final int _nbRoundsMax = 3;
    private Round[] _rounds;
    private List<GameObserver> _observers;

    public Game(int nbRounds) {
        System.out.println("Game created");
        /*if (nbRounds > _nbRoundsMax)
        {
            nbRounds = _nbRoundsMax;
        }*/
        this._rounds = new Round[nbRounds];
        _observers = new ArrayList<>();
    }

    public void nextRound(int nbAttempts, int nbColorsInCombination, GameMode gameMode)
    {
        int _currentRoundNb = getCurrentRoundNb() + 1;
        if (isGameOver())
        {
            System.out.println("Game over");
            endGame();
            return;
        } else
        {
            System.out.println("Round " + _currentRoundNb + " started");
            createNextRound(nbAttempts, nbColorsInCombination, gameMode);
        }
        System.out.println("Round " + _currentRoundNb + " ended");
    }

    private boolean isGameOver()
    {
        return getCurrentRoundNb() == this._rounds.length - 1;
    }

    private void createNextRound(int nbAttempts, int nbColorsInCombination, GameMode gameMode) {
        System.out.println("Round " + getCurrentRoundNb() + " launched");
        _rounds[getCurrentRoundNb()] = new Round(nbAttempts, nbColorsInCombination, gameMode);
    }

    public Round getCurrentRound() {
        return _rounds[getCurrentRoundNb() - 1];
    }

    private int getCurrentRoundNb()
    {
        for (int i = 0; i < _rounds.length; i++)
        {
            if (_rounds[i] == null)
            {
                return i;
            }
        }
        return 0;
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

    public void submitCombination(Combination combination) {
        Round currentRound = getCurrentRound();
        Attempt attempt = currentRound.submitCombination(combination);
        notifyAttemptPerformed(attempt);
        if (currentRound.isRoundOver()) {
            if (isGameOver()) {
                notifyGameFinished();
            } else {
                notifyRoundFinished();
            }
        }
    }

    private void notifyGameFinished() {
        for (GameObserver gameObserver: _observers) {
            gameObserver.onGameFinished();
        }
    }
}