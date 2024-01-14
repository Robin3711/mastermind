package models;

/**
 * Represents a round in the MasterMind game.
 */
public class Round
{
    private final GameMode _gameMode;
    private final Solution _solution;
    private Attempt[] _attempts;
    private boolean _isWon = false;
    public boolean _forfeited = false;

    /**
     * Constructor for the Round class.
     * @param nbAttempts The number of attempts allowed in the round.
     * @param nbColorsInCombination The number of colors in the combination for the round.
     * @param gameMode The game mode for the round.
     */
    public Round(int nbAttempts, int nbColorsInCombination, GameMode gameMode)
    {
        _gameMode = gameMode;
        _solution = new Solution(nbColorsInCombination);
        _attempts = new Attempt[nbAttempts];
    }

    /**
     * Checks if the round is over.
     * @return True if the round is over, false otherwise.
     */
    boolean isRoundOver()
    {
        if(getCurrentAttempt() == null)
        {
            return true;
        }
        // A round is over if we have reached the last attempt or if the solution has been found.
        return getCurrentAttemptNb() == _attempts.length || _solution.isSolutionFound(getCurrentAttempt().getClues()) || _forfeited ;
    }

    /**
     * Gets the current attempt in the round.
     * @return The current attempt or null if no attempts have been made.
     */
    public Attempt getCurrentAttempt()
    {
        if(getCurrentAttemptNb()!= 0)
        {
            return _attempts[getCurrentAttemptNb() - 1];
        }
        return null;
    }

    /**
     * Gets the number of the current attempt in the round.
     * @return The number of the current attempt.
     */
    public int getCurrentAttemptNb()
    {
        for (int i = 0; i < _attempts.length; i++)
        {
            if (_attempts[i] == null)
            {
                return i;
            }
        }
        return _attempts.length;
    }

    /**
     * Gets whether the round is won.
     * @return True if the round is won, false otherwise.
     */
    public boolean getIsWon()
    {
        return _isWon;
    }

    /**
     * Calculates the score for the round.
     * @return The score for the round.
     */
    public int calculateScoreRound()
    {
        if(getCurrentAttempt() == null)
        {
            return 0;
        }
        // Score calculation based on the player's last attempt
        int[] numericClues = _attempts[getCurrentAttemptNb()-1].getNumericClues();
        int score = 0;
        score += (3*numericClues[0]);
        score+=(numericClues[1]);

        if(_gameMode == GameMode.NUMERIC || _gameMode == GameMode.CLASSIC)  // Bonus if not using easy mode
        {
            score += 4;
        }
        return score;
    }

    /**
     * Submits a combination to the round.
     * @param combination The combination to be submitted.
     * @return The attempt made in the round.
     */
    public Attempt submitCombination(Combination combination)
    {
        Clue[] clues = _solution.compareWithCombination(combination);
        Attempt attempt = new Attempt(combination, clues);

        if (_gameMode == GameMode.CLASSIC)
        {
            // By default, the array is sorted in the order of pawns (easy mode) so it needs to be rearranged
            attempt.sortClues();
        }

        _attempts[getCurrentAttemptNb()] = attempt;

        if (_solution.isSolutionFound(clues))
        {
            _isWon = true;
        }
        return attempt;
    }
}
