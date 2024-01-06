package models;

import views.GameWindow;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Round[] _rounds;
    private List<GameObserver> _observers;
    private int _currentRound;

    public Game(int nbRounds) {
        System.out.println("Game created");
        this._rounds = new Round[nbRounds];
        _observers = new ArrayList<>();
        _currentRound = 0;
    }

    public void nextRound(int nbAttempts, int nbColorsInCombination, GameMode gameMode) {
        System.out.println("Round " + (_currentRound + 1) + " launched");
        _rounds[_currentRound] = new Round(nbAttempts, nbColorsInCombination, gameMode);
        _currentRound++;
    }

    public Round getCurrentRound() {
        return _rounds[_currentRound - 1];
    }

    private void endGame() {
        return;
    }

    public int CalculateScoreGame() {
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
        for (GameObserver gameObserver : _observers) {
            gameObserver.onAttemptPerformed(attempt);
        }
    }

    private void notifyRoundFinished() {
        for (GameObserver gameObserver : _observers) {
            gameObserver.onRoundFinished();
        }
    }

    public void submitCombination(Combination combination) {
        Round currentRound = getCurrentRound();
        Attempt attempt = currentRound.submitCombination(combination);
        notifyAttemptPerformed(attempt);
        if (currentRound.isRoundOver()) {
            /*if (isGameOver()) {
                notifyGameFinished();
            } else {*/
            notifyRoundFinished();
        }
    }

    private void notifyGameFinished() {
        for (GameObserver gameObserver : _observers) {
            gameObserver.onGameFinished();
        }
    }

    public int getNbRoundsWon() {
        int nbRoundsWon = 0;

        for (int i = 0; i < _currentRound; i++) {
            if (_rounds[i].getIsWon()) {
                nbRoundsWon++;
            }
        }
        return nbRoundsWon;
    }

    public boolean isGameOver() {
        return (_currentRound == _rounds.length && getCurrentRound().isRoundOver());
    }

    public boolean getIsRoundWon(int x)
    {
        return _rounds[x].getIsWon();
    }

    public int getRoundScore(int x)
    {
        return _rounds[x].calculateScoreRound();
    }
}
