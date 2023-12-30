package models;

public class Combination {
    private int _nbColorsInCombination = 4;
    protected PawnColor[] _pawns;

    protected Combination() {
        _pawns = new PawnColor[getNbColorsInCombination()];
    }

    public Combination(int nbColorsInCombination) {
        this._nbColorsInCombination = nbColorsInCombination;
    }

    public int getNbColorsInCombination() {
        return _nbColorsInCombination;
    }

    public void setPawns(PawnColor[] pawns) {
        // Verify that the number of pawns is correct
        if (pawns.length != getNbColorsInCombination()) {
            return;
        }
        this._pawns = pawns;
    }

    public PawnColor[] getPawns() {
        return _pawns;
    }

    protected int getNbOccurences(PawnColor pawnColor) {
        int nbOccurences = 0;
        for (PawnColor pawn: _pawns) {
            if (pawn == pawnColor) {
                nbOccurences++;
            }
        }
        return nbOccurences;
    }
}