import controllers.GameController;
import models.*;
import views.MenuWindow;

public class MasterMindApp {
    public static void main(String[] args) throws Exception {
        /*
        // Define parameters of the game
        int nbAttempts = 10;
        int nbRounds = 3;
        int nbColorsInCombination = 4;
        GameMode gameMode = GameMode.CLASSIC;
        //Round.setNbAttempts(10);
        //Game.setNbRounds(3);
        //Combination.setNbColorsInCombination(4);

        // Launch
        Game game = new Game(nbRounds);

        // Create the solution
        //Combination solution = new Solution(nbColorsInCombination);

        // Launch the next round
        game.nextRound(nbAttempts, nbColorsInCombination, gameMode);

        // Display the solution
        for (PawnColor pawnColor: game.getCurrentRound().getSolution().getPawns()) {
            System.out.println(pawnColor);
        }

        // Create a PawnColor array
        PawnColor[] pawnColors = new PawnColor[4];
        pawnColors[0] = PawnColor.BLUE;
        pawnColors[1] = PawnColor.RED;
        pawnColors[2] = PawnColor.GREEN;
        pawnColors[3] = PawnColor.YELLOW;

        // Create a combination
        Combination combination = new Combination(nbColorsInCombination);
        combination.setPawns(pawnColors);

        // Submit the combination, round has the submitCombination method
        game.getCurrentRound().submitCombination(combination);

        // Create a PawnColor array
        PawnColor[] pawnColors2 = new PawnColor[4];
        pawnColors2[0] = PawnColor.BLUE;
        pawnColors2[1] = PawnColor.PINK;
        pawnColors2[2] = PawnColor.GREEN;
        pawnColors2[3] = PawnColor.LIGHT_GRAY;

        // Create a combination
        Combination combination2 = new Combination(nbColorsInCombination);
        combination2.setPawns(pawnColors2);

        // Submit the combination, round has the submitCombination method
        game.getCurrentRound().submitCombination(combination2);

        // Create a PawnColor array
        PawnColor[] pawnColors3 = new PawnColor[4];
        pawnColors3[0] = PawnColor.PINK;
        pawnColors3[1] = PawnColor.BLUE;
        pawnColors3[2] = PawnColor.GREEN;
        pawnColors3[3] = PawnColor.LIGHT_GRAY;

        // Create a combination
        Combination combination3 = new Combination(nbColorsInCombination);
        combination3.setPawns(pawnColors3);

        // Submit the combination, round has the submitCombination method
        game.getCurrentRound().submitCombination(combination3);

        // Create a PawnColor array which is the solution
        PawnColor[] pawnColors4 = new PawnColor[4];
        for (int i = 0; i < nbColorsInCombination; i++) {
            pawnColors4[i] = game.getCurrentRound().getSolution().getPawns()[i];
        }

        // Create a combination
        Combination combination4 = new Combination(nbColorsInCombination);
        combination4.setPawns(pawnColors4);

        // Submit the combination, round has the submitCombination method
        game.getCurrentRound().submitCombination(combination4);
        game.getCurrentRound().submitCombination(combination4);
        game.getCurrentRound().submitCombination(combination4);

        // Create a PawnColor array
        PawnColor[] pawnColors5 = new PawnColor[4];
        pawnColors5[0] = PawnColor.PINK;
        pawnColors5[1] = PawnColor.LIGHT_GRAY;
        pawnColors5[2] = PawnColor.GREEN;
        pawnColors5[3] = PawnColor.BLUE;

        // Create a combination
        Combination combination5 = new Combination(nbColorsInCombination);
        combination5.setPawns(pawnColors5);

        // Submit the combination, round has the submitCombination method
        game.getCurrentRound().submitCombination(combination5);
        */
        GameController gameController = new GameController();
        MenuWindow menuWindow = new MenuWindow(gameController);
    }
}