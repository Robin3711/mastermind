package models;

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
    }
}
