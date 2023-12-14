package models;

public class Game {
    //private final int _nbRoundsMax = 3;
    private Round[] _rounds;

    public Game(int nbRounds) {
        System.out.println("Game created");
        /*if (nbRounds > _nbRoundsMax)
        {
            nbRounds = _nbRoundsMax;
        }*/
        this._rounds = new Round[nbRounds];
    }

    public void nextRound(int nbAttempts, int nbColorsInCombination, GameMode gameMode)
    {
        int _currentRoundNb = getCurrentRoundNb() + 1;
        if (isGameOver())
        {
            System.out.println("Game over");
            endGame();
            return;
        } else
        {
            System.out.println("Round " + _currentRoundNb + " started");
            createNextRound(nbAttempts, nbColorsInCombination, gameMode);
        }
        System.out.println("Round " + _currentRoundNb + " ended");
    }

    private boolean isGameOver()
    {
        return getCurrentRoundNb() == this._rounds.length - 1;
    }

    private void createNextRound(int nbAttempts, int nbColorsInCombination, GameMode gameMode) {
        System.out.println("Round " + getCurrentRoundNb() + " launched");
        _rounds[getCurrentRoundNb()] = new Round(nbAttempts, nbColorsInCombination, gameMode);
    }

    public Round getCurrentRound() {
        return _rounds[getCurrentRoundNb() - 1];
    }

    private int getCurrentRoundNb()
    {
        for (int i = 0; i < _rounds.length; i++)
        {
            if (_rounds[i] == null)
            {
                return i;
            }
        }
        return 0;
    }

    private void endGame() {
        return;
    }

    public int CalculateScoreGame()
    {
        int score = 0;
        for(int i = 0; i < _rounds.length; i++)
        {
            score += _rounds[i].calculateScoreRound();
        }
        return score;
    }
}