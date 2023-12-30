package models;

public class Round {
    //private final int _nbAttemptsMAX = 12;
    private final GameMode _gameMode;   // peut etre soumis a de futures modifications
    private final Solution _solution;
    private Attempt[] _attempts;

    public Round(int nbAttempts, int nbColorsInCombination, GameMode gameMode) {
        _solution = new Solution(nbColorsInCombination);
        /*if(nbAttempts > _nbAttemptsMAX);
        {
            nbAttempts = _nbAttemptsMAX;
        }*/
        _gameMode = gameMode;
        _attempts = new Attempt[nbAttempts];
    }

    private void nextAttempt() {
        int _currentAttemptNb = getCurrentAttemptNb() + 1;
        if (isRoundOver()) {
            System.out.println("Round over");
            endRound(); // il y avait loseRound ici avant
            return;
        }
        System.out.println("Attempt " + _currentAttemptNb + " ended");
    }

    // package private
    boolean isRoundOver() {
        // A round is over when the current attempt is the last attempt or when the solution is found
        return getCurrentAttemptNb() == _attempts.length || _solution.isSolutionFound(getCurrentAttempt().getClues());
    }

    public Attempt submitCombination(Combination combination) {
        Clue[] clues = _solution.compareWithCombination(combination);
        Attempt attempt = new Attempt(combination, clues);
        if (_gameMode == GameMode.CLASSIC) {
            attempt.sortClues();
        }
        _attempts[getCurrentAttemptNb()] = attempt;
        if (_solution.isSolutionFound(clues)) {
            System.out.println("Solution found");

        }
        return attempt;
    }

    public Attempt getCurrentAttempt() {
        return _attempts[getCurrentAttemptNb() - 1];
    }

    public int getCurrentAttemptNb() {
        for (int i = 0; i < _attempts.length; i++)
        {
            if (_attempts[i] == null) {
                return i;
            }
        }
        return _attempts.length;
    }

    private void endRound() {
        return;
    }
    public  int calculateScoreRound()
    {
        return 0;
    }

    // Uniquement pour les tests, pas sur l'UML
    public Solution getSolution()
    {
        return _solution;
    }
}