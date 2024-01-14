package controllers;

import models.Combination;
import models.Game;
import models.GameMode;
import models.PawnColor;
import views.EndWindow;
import views.GameWindow;

/**
 * Controller class for managing the MasterMind game.
 */
public class GameController {
    private Game _game;
    private String _username;
    private GameMode _gameMode;
    private int _nbRounds;
    private int _nbColors;
    private int _nbColorsInCombination;
    private int _nbAttempts;

    /**
     * Starts a new game with the specified parameters.
     * @param username The username of the player.
     * @param gameMode The game mode (CLASSIC, NUMERIC).
     * @param nbRounds The number of rounds in the game.
     * @param nbColors The total number of available colors.
     * @param nbColorsInCombination The number of colors in each combination.
     * @param nbAttempts The number of attempts allowed per round.
     */
    public void startGame(String username, GameMode gameMode, int nbRounds, int nbColors, int nbColorsInCombination, int nbAttempts) {
        this._username = username;
        this._gameMode = gameMode;
        this._nbRounds = nbRounds;
        this._nbColors = nbColors;
        this._nbColorsInCombination = nbColorsInCombination;
        this._nbAttempts = nbAttempts;
        this._game = new Game(nbRounds);

        // Create the game view and set up the observer
        GameWindow gameWindow = new GameWindow(this);
        _game.addObserver(gameWindow);

        // Start round 1
        nextRound();
    }

    // Getters

    /**
     * Gets the current game.
     * @return The current game.
     */
    public Game getGame() {
        return _game;
    }

    /**
     * Gets the username of the player.
     * @return The username of the player.
     */
    public String getUsername() {
        return _username;
    }

    /**
     * Gets the game mode.
     * @return The game mode.
     */
    public GameMode getGameMode() {
        return _gameMode;
    }

    /**
     * Gets the number of rounds in the game.
     * @return The number of rounds in the game.
     */
    public int getNbRounds() {
        return _nbRounds;
    }

    /**
     * Gets the total number of available colors.
     * @return The total number of available colors.
     */
    public int getNbColors() {
        return _nbColors;
    }

    /**
     * Gets the number of colors in each combination.
     * @return The number of colors in each combination.
     */
    public int getNbColorsInCombination() {
        return _nbColorsInCombination;
    }

    /**
     * Gets the number of attempts allowed per round.
     * @return The number of attempts allowed per round.
     */
    public int getNbAttempts() {
        return _nbAttempts;
    }

    /**
     * Gets the number of rounds won in the game.
     * @return The number of rounds won.
     */
    public int getNbRoundsWon() {
        return _game.getNbRoundsWon();
    }

    /**
     * Submits a combination to the current round in the game.
     * @param pawnColors The array of pawn colors representing the combination.
     */
    public void submitCombination(PawnColor[] pawnColors) {
        Combination combination = new Combination(_nbColorsInCombination);
        combination.setPawns(pawnColors);
        _game.submitCombination(combination);
    }

    /**
     * Moves to the next round in the game.
     */
    public void nextRound() {
        // The controller is responsible for checking if the game is over
        if (_game.isGameOver()) {
            endGame();
        } else {
            _game.nextRound(_nbAttempts, _nbColorsInCombination, _gameMode);
        }
    }

    /**
     * Handles the end of the game by creating the end menu.
     */
    private void endGame() {
        new EndWindow(this);
    }

    /**
     * Forfeits the current round in the game.
     */
    public void forfeitCurrentRound() {
        this._game.getCurrentRound()._forfeited = true;
    }
}
