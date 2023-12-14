package models;

public class Round {
    //private final int _nbAttemptsMAX = 12;
    private final GameMode _gameMode;   // peut etre soumis a de futurs modifications
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

    private boolean isRoundOver() {
        return getCurrentAttemptNb() == this._attempts.length-1;
    }

    public void submitCombination(Combination combination) {
        Clue[] clues = _solution.compareWithCombination(combination);
        Attempt attempt = new Attempt(combination, clues);
        _attempts[getCurrentAttemptNb()] = attempt;
        if (_solution.isSolutionFound(clues)) {
            System.out.println("Solution found");
            endRound(); // il y avait winRound ici avant
        } else {
            System.out.println("Solution not found");
            nextAttempt();
        }
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
        return 0;
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