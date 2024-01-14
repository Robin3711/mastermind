package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the MasterMind game.
 */
public class Game
{
    private Round[] _rounds;
    private List<GameObserver> _observers;
    private int _currentRound;

    /**
     * Constructor for the Game class.
     * @param nbRounds The number of rounds in the game.
     */
    public Game(int nbRounds)
    {
        this._rounds = new Round[nbRounds];
        _observers = new ArrayList<>();
        _currentRound = 0;
    }

    /**
     * Gets the current round of the game.
     * @return The current round or null if no rounds have been played.
     */
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

    /**
     * Gets the number of rounds won in the game.
     * @return The number of rounds won.
     */
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

    /**
     * Checks if the game is over.
     * @return True if the game is over, false otherwise.
     */
    public boolean isGameOver()
    {
        boolean over = (_currentRound == _rounds.length && getCurrentRound().isRoundOver());

        if (over)
        {
            notifyGameFinished();
        }
        return over;
    }

    /**
     * Gets whether a specific round is won.
     * @param x The round index.
     * @return True if the round is won, false otherwise.
     */
    public boolean getIsRoundWon(int x)
    {
        return _rounds[x].getIsWon();
    }

    /**
     * Gets the score for a specific round.
     * @param x The round index.
     * @return The score for the round.
     */
    public int getRoundScore(int x)
    {
        return _rounds[x].calculateScoreRound();
    }

    /**
     * Calculates the total score for the game.
     * @return The total score for the game.
     */
    public int CalculateScoreGame()
    {
        int score = 0;
        for (Round round : _rounds)
        {
            score += round.calculateScoreRound();
        }
        return score;
    }

    /**
     * Moves to the next round in the game.
     * @param nbAttempts The number of attempts allowed in the new round.
     * @param nbColorsInCombination The number of colors in the combination for the new round.
     * @param gameMode The game mode for the new round.
     */
    public void nextRound(int nbAttempts, int nbColorsInCombination, GameMode gameMode)
    {
        System.out.println("ROUND : "+_currentRound);
        _rounds[_currentRound] = new Round(nbAttempts, nbColorsInCombination, gameMode);
        _currentRound++;
    }

    /**
     * Submits a combination to the current round.
     * @param combination The combination to be submitted.
     */
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

    // Observer-related functions

    /**
     * Adds a GameObserver to the list of observers.
     * @param gameObserver The observer to be added.
     */
    public void addObserver(GameObserver gameObserver)
    {
        _observers.add(gameObserver);
    }

    /**
     * Notify the observers that an attempt has been performed.
     * @param attempt The attempt that has been performed.
     */
    private void notifyAttemptPerformed(Attempt attempt)
    {
        for (GameObserver gameObserver : _observers)
        {
            gameObserver.onAttemptPerformed(attempt);
        }
    }

    /**
     * Notify the observers that a round has been finished.
     */
    private void notifyRoundFinished()
    {
        for (GameObserver gameObserver : _observers)
        {
            gameObserver.onRoundFinished();
        }
    }

    /**
     * Notify the observers that the game has been finished.
     */
    private void notifyGameFinished()
    {
        for (GameObserver gameObserver : _observers)
        {
            gameObserver.onGameFinished();
        }
    }
}
