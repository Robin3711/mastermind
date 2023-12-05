package models;

public class Attempt
{
    Combination combination;
    Clue[] clues;
    public Attempt(Combination newCombination, Clue[] newClues)
    {
        this.clues = newClues;
        this.combination = newCombination;
    }

    public Combination getCombination() {
        return combination;
    }

    public Clue[] getClues() {
        return clues;
    }
}
