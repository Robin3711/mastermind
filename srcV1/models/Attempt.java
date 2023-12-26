package models;

public class Attempt {
    private final Combination _combinationSubmitted;
    private Clue[] _clues;

    public Attempt(Combination combinationSubmitted, Clue[] clues) {
        _combinationSubmitted = combinationSubmitted;
        _clues = clues;
    }

    public Combination getCombinationSubmitted() {
        return _combinationSubmitted;
    }

    public Clue[] getClues() {
        return _clues;
    }

    private int getNbColorsInCombination() {
        return _combinationSubmitted.getNbColorsInCombination();
    }

    public void sortClues() {
        int nbColorsInCombination = getNbColorsInCombination();
        Clue[] sortedClues = new Clue[nbColorsInCombination];
        int nbWellPlaced = 0;
        int nbMisplaced = 0;
        int nbWrong = 0;
        for (int i = 0; i < nbColorsInCombination; i++) {
            if (_clues[i] == Clue.WELL_PLACED) {
                nbWellPlaced++;
            } else if (_clues[i] == Clue.MISPLACED) {
                nbMisplaced++;
            } else {
                nbWrong++;
            }
        }
        for (int i = 0; i < nbWellPlaced; i++) {
            sortedClues[i] = Clue.WELL_PLACED;
        }
        for (int i = nbWellPlaced; i < nbWellPlaced + nbMisplaced; i++) {
            sortedClues[i] = Clue.MISPLACED;
        }
        for (int i = nbWellPlaced + nbMisplaced; i < nbWellPlaced + nbMisplaced + nbWrong; i++) {
            sortedClues[i] = Clue.WRONG;
        }
        _clues = sortedClues;
    }
}
