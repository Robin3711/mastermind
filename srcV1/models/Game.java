package models;

import views.GameWindow;

import java.util.ArrayList;
import java.util.List;

public class Game
{
    private Round[] _rounds;
    private List<GameObserver> _observers;
    private int _currentRound;

    public Game(int nbRounds)
    {
        this._rounds = new Round[nbRounds];
        _observers = new ArrayList<>();
        _currentRound = 0;
    }

    public Round getCurrentRound()
    {
        if (_currentRound > 0)
        {
            return _rounds[_currentRound - 1];
        }
        else
        {
            return null;
        }
    }

    public int getNbRoundsWon()
    {
        int nbRoundsWon = 0;

        for (int i = 0; i < _currentRound; i++)
        {
            if (_rounds[i].getIsWon())
            {
                nbRoundsWon++;
            }
        }
        return nbRoundsWon;
    }

    public boolean isGameOver()
    {
        boolean over = (_currentRound == _rounds.length && getCurrentRound().isRoundOver());

        if (over)
        {
            notifyGameFinished();
        }
        return over;
    }

    public boolean getIsRoundWon(int x)
    {
        return _rounds[x].getIsWon();
    }

    public int getRoundScore(int x)
    {
        return _rounds[x].calculateScoreRound();
    }

    public int CalculateScoreGame()
    {
        int score = 0;
        for (Round round : _rounds)
        {
            score += round.calculateScoreRound();
        }
        return score;
    }

    public void nextRound(int nbAttempts, int nbColorsInCombination, GameMode gameMode)
    {
        _rounds[_currentRound] = new Round(nbAttempts, nbColorsInCombination, gameMode);
        _currentRound++;
    }

    public void submitCombination(Combination combination)
    {
        Round currentRound = getCurrentRound();
        Attempt attempt = currentRound.submitCombination(combination);
        notifyAttemptPerformed(attempt);

        if (currentRound.isRoundOver())
        {
            notifyRoundFinished();
        }
    }

    public void addObserver(GameObserver gameObserver)
    {
        _observers.add(gameObserver);
    }

    private void notifyAttemptPerformed(Attempt attempt)
    {
        for (GameObserver gameObserver : _observers)
        {
            gameObserver.onAttemptPerformed(attempt);
        }
    }

    private void notifyRoundFinished()
    {
        for (GameObserver gameObserver : _observers)
        {
            gameObserver.onRoundFinished();
        }
    }

    private void notifyGameFinished()
    {
        for (GameObserver gameObserver : _observers)
        {
            gameObserver.onGameFinished();
        }
    }
}
