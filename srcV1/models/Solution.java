package models;

import java.util.HashMap;
import java.util.Map;

public class Solution extends Combination {

    public Solution(int nbColorsInCombination) {
        super(nbColorsInCombination);
        generateSolution();
    }

    public void generateSolution() {
        _pawns = new PawnColor[getNbColorsInCombination()];
        for (int i = 0; i < getNbColorsInCombination(); i++) {
            _pawns[i] = PawnColor.values()[(int) (Math.random() * PawnColor.values().length)];
        }
        // Display solution
        System.out.print("Solution: ");
        for (int i = 0; i < getNbColorsInCombination(); i++) {
            System.out.print(_pawns[i] + " ");
        }
    }

    public Clue[] compareWithCombination(Combination combination) {
        // If a pawn is at the same place in the solution and in the combination, it's well placed
        // If a pawn is in the solution but not at the same place in the combination, it's misplaced
        // If a pawn is not in the solution, it's wrong
        int nbColorsInCombination = getNbColorsInCombination();
        Clue[] clues = new Clue[nbColorsInCombination];
        HashMap<PawnColor, Integer> nbOccurencesInSolution = new HashMap<>();

        for (int i = 0; i < nbColorsInCombination; i++) {
            if (_pawns[i] == combination._pawns[i]) {
                clues[i] = Clue.WELL_PLACED;
            } else {
                nbOccurencesInSolution.put(_pawns[i], nbOccurencesInSolution.getOrDefault(_pawns[i], 0) + 1);
            }
        }

        for (int i = 0; i < nbColorsInCombination; i++) {
            if (clues[i] == null) {
                if (nbOccurencesInSolution.getOrDefault(combination._pawns[i], 0) > 0) {
                    clues[i] = Clue.MISPLACED;
                    nbOccurencesInSolution.put(combination._pawns[i], nbOccurencesInSolution.get(combination._pawns[i]) - 1);
                } else {
                    clues[i] = Clue.WRONG;
                }
            }
        }

        return clues;
    }

    public boolean isSolutionFound(Clue[] clues) {
        for (int i = 0; i < getNbColorsInCombination(); i++) {
            if (clues[i] != Clue.WELL_PLACED) {
                return false;
            }
        }
        return true;
    }
}