package controllers;

import models.Combination;
import models.Game;
import models.GameMode;
import models.PawnColor;
import views.EndWindow;
import views.GameWindow;

public class GameController {
    private Game _game;
    private String _username;
    private GameMode _gameMode;
    private int _nbRounds;
    private int _nbColors;
    private int _nbColorsInCombination;
    private int _nbAttempts;

    public void startGame(String username, GameMode gameMode, int nbRounds, int nbColors, int nbColorsInCombination, int nbAttempts) {
        this._username = username;
        this._gameMode = gameMode;
        this._nbRounds = nbRounds;
        this._nbColors = nbColors;
        this._nbColorsInCombination = nbColorsInCombination;
        this._nbAttempts = nbAttempts;
        this._game = new Game(nbRounds);

        // Crée la vue du jeu
        GameWindow gameWindow = new GameWindow(this);

        _game.addObserver(gameWindow);

        nextRound();

        // Affiche les paramètres de la partie
        System.out.println("Username: " + this._username);
        System.out.println("Game mode: " + this._gameMode);
        System.out.println("Number of rounds: " + this._nbRounds);
        System.out.println("Number of colors: " + this._nbColors);
        System.out.println("Number of colors in the solution: " + this._nbColorsInCombination);
        System.out.println("Number of attempts: " + this._nbAttempts);
    }

    // getters
    public Game getGame() {
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

    public void submitCombination(PawnColor[] pawnColors) {
        Combination combination = new Combination(_nbColorsInCombination);
        combination.setPawns(pawnColors);
        _game.submitCombination(combination);
    }

    public void nextRound()
    {
        System.out.println("nextround");
        if(_game.isGameOver())
        {
            System.out.println("isGameOver == true");
            // game notify round over
            endGame();
        }
        else
        {
            _game.nextRound(_nbAttempts, _nbColorsInCombination, _gameMode);
            System.out.println("isGameOver == false");
        }

    }
    // calcul du score; peut etre a faire dans une class a part
    public int getNbRoundsWon()
    {
        return _game.getNbRoundsWon();
    }

    private void endGame()
    {
        // trouver un moyen de fermer la game windows
        EndWindow endWindow = new EndWindow(this);
        System.out.println("fin");
    }
}
