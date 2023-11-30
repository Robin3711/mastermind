package models;

public class Solution extends Combination {
    public Solution() {
        generateSolution();
    }

    public void generateSolution() {
        _pawns = new PawnColor[getNbColorsInCombination()];
        for (int i = 0; i < getNbColorsInCombination(); i++) {
            _pawns[i] = PawnColor.values()[(int) (Math.random() * PawnColor.values().length)];
        }
    }

    public Clue[] compareWithCombination(Combination combination) {
        // If a pawn is at the same place in the solution and in the combination, it's well placed
        // If a pawn is in the solution but not at the same place in the combination, it's misplaced
        // If a pawn is not in the solution, it's wrong
        Clue[] clues = new Clue[getNbColorsInCombination()];
        for (int i = 0; i < getNbColorsInCombination(); i++) {
            if (_pawns[i] == combination._pawns[i]) {
                clues[i] = Clue.WELL_PLACED;
            } else if (isInSolution(combination._pawns[i])) {
                clues[i] = Clue.MISPLACED;
            } else {
                clues[i] = Clue.WRONG;
            }
        }
        return clues;
    }

    private boolean isInSolution(PawnColor pawnColor) {
        for (int i = 0; i < getNbColorsInCombination(); i++) {
            if (_pawns[i] == pawnColor) {
                return true;
            }
        }
        return false;
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