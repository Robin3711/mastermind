package controllers;

import models.Combination;
import models.Game;
import models.GameMode;
import models.PawnColor;
import views.EndWindow;
import views.GameWindow;

public class GameController
{
    private Game _game;
    private String _username;
    private GameMode _gameMode;
    private int _nbRounds;
    private int _nbColors;
    private int _nbColorsInCombination;
    private int _nbAttempts;

    public void startGame(String username, GameMode gameMode, int nbRounds, int nbColors, int nbColorsInCombination, int nbAttempts)
    {
        this._username = username;
        this._gameMode = gameMode;
        this._nbRounds = nbRounds;
        this._nbColors = nbColors;
        this._nbColorsInCombination = nbColorsInCombination;
        this._nbAttempts = nbAttempts;
        this._game = new Game(nbRounds);

        // Crée la vue du jeu et mise en place de l'observeur
        GameWindow gameWindow = new GameWindow(this);
        _game.addObserver(gameWindow);

        // lance le tour 1
        nextRound();
    }

    // getters
    public Game getGame()
    {
        return _game;
    }

    public String getUsername() {
        return _username;
    }

    public GameMode getGameMode() {
        return _gameMode;
    }

    public int getNbRounds() {
        return _nbRounds;
    }

    public int getNbColors() {
        return _nbColors;
    }

    public int getNbColorsInCombination() {
        return _nbColorsInCombination;
    }

    public int getNbAttempts() {
        return _nbAttempts;
    }

    public int getNbRoundsWon()
    {
        return _game.getNbRoundsWon();
    }

    public void submitCombination(PawnColor[] pawnColors)
    {
        Combination combination = new Combination(_nbColorsInCombination);
        combination.setPawns(pawnColors);
        _game.submitCombination(combination);
    }

    public void nextRound()
    {
        // le controlleur est chargé de verifier si le jeu est fini
        if(_game.isGameOver())
        {
            endGame();
        }
        else
        {
            _game.nextRound(_nbAttempts, _nbColorsInCombination, _gameMode);
        }
    }

    private void endGame()
    {
        // créer le menu de fin
        new EndWindow(this);
    }
}
