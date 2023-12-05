package models;

import java.util.ArrayList;
import java.util.List;

public class Round {
    private int _nbAttempts = 10;
    private int _currentAttemptNb = 0;
    private final Solution _solution;

    public Attempt[] attempts;
    public Round(int nbAttempts, int nbColorsInCombination) {
        _solution = new Solution(nbColorsInCombination);
        _nbAttempts = nbAttempts;
        //startRound();
    }

    private void nextAttempt(){
        if (isRoundOver()) {
            System.out.println("Round over");
            loseRound();
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

    public void submitCombination(Combination combination) {
        Clue[] clues = _solution.compareWithCombination(combination);
        if (_solution.isSolutionFound(clues)) {
            System.out.println("Solution found");
            winRound();
        } else {
            System.out.println("Solution not found");
            nextAttempt();
        }
    }

    private void loseRound() {
        return;
    }

    private void winRound() {
        return;
    }

    private void forfeitRound() {
        return;
    }

    private void endRound() {
        return;
    }

    public Solution getSolution() {
        return _solution;
    }
}