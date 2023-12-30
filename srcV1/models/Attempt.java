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

        // Tri par insertion
        for (int i = 1; i < nbColorsInCombination; ++i) {
            Clue key = _clues[i];
            int j = i - 1;

            while (j >= 0 && _clues[j].ordinal() > key.ordinal()) {
                _clues[j + 1] = _clues[j];
                j = j - 1;
            }
            _clues[j + 1] = key;
        }
    }
}
