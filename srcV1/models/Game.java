package models;

public class Game
 {
    private int _nbRounds = 3;
    private int _currentRoundNb = 0;
    Round _currentRound;

    public Game(int nbRounds) {
        System.out.println("Game created");
        this._nbRounds = nbRounds;
        //startGame();
    }

    public void updateWinRound() {
        System.out.println("Round won");
    }

    public void updateLoseRound(int score) {
        System.out.println("Round lost");
    }

    public void updateForfeitRound() {
        System.out.println("Round forfeited");
    }

    public void nextRound(int nbAttempts, int nbColorsInCombination) {
        if (isGameOver()) {
            System.out.println("Game over");
            endGame();
            return;
        } else {
            System.out.println("Round " + _currentRoundNb + " started");
            launchNextRound(nbAttempts, nbColorsInCombination);
        }
        System.out.println("Round " + _currentRoundNb + " ended");
        _currentRoundNb++;
    }

    private boolean isGameOver() {
        return _currentRoundNb == _nbRounds;
    }

    private void launchNextRound(int nbAttempts, int nbColorsInCombination) {
        System.out.println("Round " + _currentRoundNb + " launched");
        this._currentRound = new Round(nbAttempts, nbColorsInCombination);
    }

    public Round getCurrentRound() {
        return _currentRound;
    }

    private void endGame() {
        return;
    }
}