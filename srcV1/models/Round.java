package models;

public class Round {
    private static int _nbAttempts = 10;
    private int _currentAttemptNb = 0;
    private final Solution _solution;

    public Round() {
        _solution = new Solution();
        startRound();
    }
    public static void setNbAttempts(int nbAttempts) {
        _nbAttempts = nbAttempts;
    }

    public int getNbAttempts() {
        return _nbAttempts;
    }

    private void startRound() {
        System.out.println("Round started");
        nextAttempt();
    }

    private void nextAttempt() {
        if (isRoundOver()) {
            System.out.println("Round over");
            endRound();
            return;
        } else {
            System.out.println("Attempt " + _currentAttemptNb + " started");
            launchNextAttempt();
        }
        System.out.println("Attempt " + _currentAttemptNb + " ended");
        _currentAttemptNb++;
    }

    private boolean isRoundOver() {
        return _currentAttemptNb == _nbAttempts;
    }

    private void launchNextAttempt() {
        System.out.println("Attempt " + _currentAttemptNb + " launched");
    }

    // TODO: ajouter à l'UML
    public void submitCombination(Combination combination) {
        Clue[] clues = _solution.compareWithCombination(combination);
        if (_solution.isSolutionFound(clues)) {
            System.out.println("Solution found");
            endRound();
        } else {
            System.out.println("Solution not found");
            nextAttempt();
        }
    }

    private void endRound() {
        return;
    }

    // TODO: supprimer, c'est pour les tests
    public Solution getSolution() {
        return _solution;
    }
}