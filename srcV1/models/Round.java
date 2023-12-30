package models;

public class Round {
    private final int _nbAttempts;
    private final GameMode _gameMode;
    private final Solution _solution;
<<<<<<< HEAD

    public Attempt[] attempts;
    public Round(int nbAttempts, int nbColorsInCombination) {
        _solution = new Solution(nbColorsInCombination);
        _nbAttempts = nbAttempts;
        //startRound();
    }

    private void nextAttempt(){
=======
    private Attempt[] _attempts;

    public Round(int nbAttempts, int nbColorsInCombination, GameMode gameMode) {
        _solution = new Solution(nbColorsInCombination);
        _nbAttempts = nbAttempts;
        _gameMode = gameMode;
        _attempts = new Attempt[nbAttempts];
    }

    private void nextAttempt() {
        int _currentAttemptNb = getCurrentAttemptNb() + 1;
>>>>>>> 3bb161a (Post rendu 1, code non cohérent avec UML)
        if (isRoundOver()) {
            System.out.println("Round over");
            loseRound();
            return;
        }
        System.out.println("Attempt " + _currentAttemptNb + " ended");
    }

    private boolean isRoundOver() {
        return getCurrentAttemptNb() == _nbAttempts;
    }

    public void submitCombination(Combination combination) {
        Clue[] clues = _solution.compareWithCombination(combination);
        Attempt attempt = new Attempt(combination, clues);
        _attempts[getCurrentAttemptNb()] = attempt;
        if (_solution.isSolutionFound(clues)) {
            System.out.println("Solution found");
            winRound();
        } else {
            System.out.println("Solution not found");
            nextAttempt();
        }
    }

    public Attempt getCurrentAttempt() {
        return _attempts[getCurrentAttemptNb() - 1];
    }

    public int getCurrentAttemptNb() {
        for (int i = 0; i < _attempts.length; i++) {
            if (_attempts[i] == null) {
                return i;
            }
        }
        return 0;
    }

    private void loseRound() {
        return;
    }

    private void winRound() {
        return;
    }

    private void endRound() {
        return;
    }

    // Uniquement pour les tests, pas sur l'UML
    public Solution getSolution() {
        return _solution;
    }
}