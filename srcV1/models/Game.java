package models;

<<<<<<< HEAD
public class Game
 {
=======
public class Game {
>>>>>>> 3bb161a (Post rendu 1, code non cohérent avec UML)
    private int _nbRounds = 3;
    private Round[] _rounds;

    public Game(int nbRounds) {
        System.out.println("Game created");
        this._nbRounds = nbRounds;
        this._rounds = new Round[nbRounds];
    }

    public void nextRound(int nbAttempts, int nbColorsInCombination, GameMode gameMode) {
        int _currentRoundNb = getCurrentRoundNb() + 1;
        if (isGameOver()) {
            System.out.println("Game over");
            endGame();
            return;
        } else {
            System.out.println("Round " + _currentRoundNb + " started");
            createNextRound(nbAttempts, nbColorsInCombination, gameMode);
        }
        System.out.println("Round " + _currentRoundNb + " ended");
    }

    private boolean isGameOver() {
        return getCurrentRoundNb() == _nbRounds;
    }

    private void createNextRound(int nbAttempts, int nbColorsInCombination, GameMode gameMode) {
        System.out.println("Round " + getCurrentRoundNb() + " launched");
        _rounds[getCurrentRoundNb()] = new Round(nbAttempts, nbColorsInCombination, gameMode);
    }

    public Round getCurrentRound() {
        return _rounds[getCurrentRoundNb() - 1];
    }

    private int getCurrentRoundNb() {
        for (int i = 0; i < _rounds.length; i++) {
            if (_rounds[i] == null) {
                return i;
            }
        }
        return 0;
    }

    private void endGame() {
        return;
    }
}