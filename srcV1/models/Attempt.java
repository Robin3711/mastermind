package models;

<<<<<<< HEAD
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
=======
public class Attempt {
    private final Combination _combinationSubmitted;
    private final Clue[] _clues;

    public Attempt(Combination combinationSubmitted, Clue[] clues) {
        _combinationSubmitted = combinationSubmitted;
        _clues = clues;
    }

    public Combination getCombinationSubmitted() {
        return _combinationSubmitted;
    }

    public Clue[] getClues() {
        return _clues;
>>>>>>> 3bb161a (Post rendu 1, code non cohérent avec UML)
    }
}
