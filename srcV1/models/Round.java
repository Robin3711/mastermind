package models;

public class Round
{

    private final GameMode _gameMode;
    private final Solution _solution;
    private Attempt[] _attempts;
    private boolean _isWon = false;

    public boolean _forfeited = false;

    public Round(int nbAttempts, int nbColorsInCombination, GameMode gameMode)
    {
        _gameMode = gameMode;
        _solution = new Solution(nbColorsInCombination);
        _attempts = new Attempt[nbAttempts];
    }

    boolean isRoundOver()
    {
        if(getCurrentAttempt() == null)
        {
            return true;
        }
        // un round est fini si on a atteint la derniere tentative ou si la solution a été trouvé
        return getCurrentAttemptNb() == _attempts.length || _solution.isSolutionFound(getCurrentAttempt().getClues()) || _forfeited ;
    }

    public Attempt getCurrentAttempt()
    {
        if(getCurrentAttemptNb()!= 0)
        {
            return _attempts[getCurrentAttemptNb() - 1];
        }
        return null;
    }

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

    public boolean getIsWon()
    {
        return _isWon;
    }

    public  int calculateScoreRound()
    {
        if(getCurrentAttempt() == null)
        {
            return 0;
        }
        // le calcul du score, basé sur la dernière tentative du joueur ( donc tjrs 12 en cas de victoire )
        int[] numericClues = _attempts[getCurrentAttemptNb()-1].getNumericClues();
        int score = 0;
        score += (3*numericClues[0]);
        score+=(numericClues[1]);

        if(_gameMode == GameMode.NUMERIC || _gameMode == GameMode.CLASSIC)  // bonus si non-utilisation du mode facile
        {
            score += 4;
        }
        return score;
    }

    public Attempt submitCombination(Combination combination)
    {
        // la solution rempli le tableau d'indice en se comparant avec la combinaison
        Clue[] clues = _solution.compareWithCombination(combination);
        Attempt attempt = new Attempt(combination, clues);

        if (_gameMode == GameMode.CLASSIC)
        {
            // Par défaut, le tableau est triée dans l'ordre des pions ( donc le mode facile ) il faut donc le réorganiser
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
