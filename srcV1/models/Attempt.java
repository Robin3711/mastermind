package models;

import java.util.Arrays;

public class Attempt {
    private final Combination _combinationSubmitted;
    private Clue[] _clues;

    public Attempt(Combination combinationSubmitted, Clue[] clues)
    {
        _combinationSubmitted = combinationSubmitted;
        _clues = clues;
    }
    public Combination getCombinationSubmitted() {
        return _combinationSubmitted;
    }

    public Clue[] getClues() {
        return _clues;
    }

    public int[] getNumericClues()
    {
        int[] numericClues = new int[Clue.values().length];

        Arrays.fill(numericClues, 0);

        for(int i = 0; i < _clues.length; i++)
        {
            if(_clues[i] == Clue.WRONG)
            {
                numericClues[Clue.WRONG.ordinal()]++;
            }
            else if(_clues[i] == Clue.MISPLACED)
            {
                numericClues[Clue.MISPLACED.ordinal()]++;
            }
            else if(_clues[i] == Clue.WELL_PLACED)
            {
                numericClues[Clue.WELL_PLACED.ordinal()]++;
            }
        }

        for(int i = 0; i < Clue.values().length; i++)
        {
            System.out.println(Clue.values()[i].name() +" " + numericClues[i]);
        }

        return numericClues;
    }

    private int getNbColorsInCombination() {
        return _combinationSubmitted.getNbColorsInCombination();
    }

    public void sortClues()
    {
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
