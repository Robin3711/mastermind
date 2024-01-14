package models;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the solution in the MasterMind game.
 */
public class Solution extends Combination {

    /**
     * Constructor for the Solution class.
     * @param nbColorsInCombination The number of colors in the combination.
     */
    public Solution(int nbColorsInCombination) {
        super(nbColorsInCombination);
        generateSolution();
    }

    /**
     * Generates a random solution.
     */
    public void generateSolution() {
        _pawns = new PawnColor[getNbColorsInCombination()];
        for (int i = 0; i < getNbColorsInCombination(); i++) {
            _pawns[i] = PawnColor.values()[(int) (Math.random() * PawnColor.values().length)];
        }

        // Display the generated solution
        for (int i = 0; i < getNbColorsInCombination(); i++) {
            System.out.print(_pawns[i] + " ");
        }
    }

    /**
     * Compares the solution with a combination and provides clues.
     * @param combination The combination to be compared.
     * @return An array of clues indicating the result of the comparison.
     */
    public Clue[] compareWithCombination(Combination combination) {
        int nbColorsInCombination = getNbColorsInCombination();
        Clue[] clues = new Clue[nbColorsInCombination];
        Map<PawnColor, Integer> nbOccurrencesInSolution = new HashMap<>();

        for (int i = 0; i < nbColorsInCombination; i++) {
            if (_pawns[i] == combination._pawns[i]) {
                clues[i] = Clue.WELL_PLACED;
            } else {
                nbOccurrencesInSolution.put(_pawns[i], nbOccurrencesInSolution.getOrDefault(_pawns[i], 0) + 1);
            }
        }

        for (int i = 0; i < nbColorsInCombination; i++) {
            if (clues[i] == null) {
                if (nbOccurrencesInSolution.getOrDefault(combination._pawns[i], 0) > 0) {
                    clues[i] = Clue.MISPLACED;
                    nbOccurrencesInSolution.put(combination._pawns[i], nbOccurrencesInSolution.get(combination._pawns[i]) - 1);
                } else {
                    clues[i] = Clue.WRONG;
                }
            }
        }

        return clues;
    }

    /**
     * Checks if the solution is found based on the provided clues.
     * @param clues The clues from the comparison.
     * @return True if the solution is found, false otherwise.
     */
    public boolean isSolutionFound(Clue[] clues) {
        for (int i = 0; i < getNbColorsInCombination(); i++) {
            if (clues[i] != Clue.WELL_PLACED) {
                return false;
            }
        }
        return true;
    }
}
