package models;

import java.util.Arrays;

/**
 * Represents a player's attempt in the MasterMind game.
 */
public class Attempt {
    private final Combination _combinationSubmitted;
    private Clue[] _clues;

    /**
     * Constructor for the Attempt class.
     * @param combinationSubmitted The combination submitted by the player.
     * @param clues The clues provided for the submitted combination.
     */
    public Attempt(Combination combinationSubmitted, Clue[] clues)
    {
        _combinationSubmitted = combinationSubmitted;
        _clues = clues;
    }

    /**
     * Gets the clues provided for the submitted combination.
     * @return An array of clues.
     */
    public Clue[] getClues() {
        return _clues;
    }

    /**
     * Converts the array of clues into numeric representation.
     * @return An array of integers representing the count of each type of clue.
     */
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

        // Display the numeric representation of clues
        for(int i = 0; i < Clue.values().length; i++)
        {
            System.out.println(Clue.values()[i].name() +" " + numericClues[i]);
        }

        return numericClues;
    }

    /**
     * Gets the number of colors in the combination submitted by the player.
     * @return The number of colors in the combination.
     */
    private int getNbColorsInCombination() {
        return _combinationSubmitted.getNbColorsInCombination();
    }

    /**
     * Sorts the clues array in ascending order using insertion sort.
     */
    public void sortClues()
    {
        int nbColorsInCombination = getNbColorsInCombination();

        // Insertion sort algorithm
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
