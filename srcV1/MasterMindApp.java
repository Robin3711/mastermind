import models.Combination;
import models.Game;
import models.PawnColor;
import models.Round;

public class MasterMindApp {
    public static void main(String[] args) {
        // Define parameters of the game
        Round.setNbAttempts(10);
        Game.setNbRounds(3);
        Combination.setNbColorsInCombination(4);

        // Launch
        Game game = new Game();

        // Create a PawnColor array
        PawnColor[] pawnColors = new PawnColor[4];
        pawnColors[0] = PawnColor.BLUE;
        pawnColors[1] = PawnColor.RED;
        pawnColors[2] = PawnColor.GREEN;
        pawnColors[3] = PawnColor.YELLOW;

        // Create a combination
        Combination combination = new Combination(pawnColors);
    }
}