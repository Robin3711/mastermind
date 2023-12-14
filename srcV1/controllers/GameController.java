package controllers;

import models.Game;
import models.GameMode;
import views.GameWindow;

public class GameController {
    Game _game;
    String _username;
    GameMode _gameMode;
    int _nbRounds;
    int _nbColors;
    int _nbColorsInCombination;
    int _nbAttempts;

    public GameController() {

    }

    public void startGame(String username, GameMode gameMode, int nbRounds, int nbColors, int nbColorsInCombination, int nbAttempts) {
        this._username = username;
        this._gameMode = gameMode;
        this._nbRounds = nbRounds;
        this._nbColors = nbColors;
        this._nbColorsInCombination = nbColorsInCombination;
        this._nbAttempts = nbAttempts;
        this._game = new Game(nbRounds);

        // Crée la vue du jeu
        GameWindow gameWindow = new GameWindow();

        _game.addObserver(gameWindow);

        this._game.nextRound(nbAttempts, nbColorsInCombination, gameMode);

        // Affiche les paramètres de la partie
        System.out.println("Username: " + this._username);
        System.out.println("Game mode: " + this._gameMode);
        System.out.println("Number of rounds: " + this._nbRounds);
        System.out.println("Number of colors: " + this._nbColors);
        System.out.println("Number of colors in the solution: " + this._nbColorsInCombination);
        System.out.println("Number of attempts: " + this._nbAttempts);
    }
}
