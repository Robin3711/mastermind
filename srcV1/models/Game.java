package models;

public class Game {
    private static int _nbRounds = 3;
    private int _currentRoundNb = 0;
    public Game() {
        System.out.println("Game created");
        startGame();
    }
    public static void setNbRounds(int nbRounds) {
        _nbRounds = nbRounds;
    }

    public int getNbRounds() {
        return _nbRounds;
    }

    private void startGame() {
        System.out.println("Game started");
        nextRound();
    }

    public void nextRound() {
        if (isGameOver()) {
            System.out.println("Game over");
            endGame();
            return;
        } else {
            System.out.println("Round " + _currentRoundNb + " started");
            launchNextRound();
        }
        System.out.println("Round " + _currentRoundNb + " ended");
        _currentRoundNb++;
        nextRound();
    }

    private boolean isGameOver() {
        return _currentRoundNb == _nbRounds;
    }

    private void launchNextRound() {
        System.out.println("Round " + _currentRoundNb + " launched");
        new Round();
    }

    private void endGame() {
        return;
    }
}