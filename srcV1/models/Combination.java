package models;

public class Combination {
    private static int _nbColorsInCombination = 4;
    protected PawnColor[] _pawns;

    protected Combination() {
        _pawns = new PawnColor[getNbColorsInCombination()];
    }

    public Combination(PawnColor[] pawns) {
        this._pawns = pawns;
    }

    public static void setNbColorsInCombination(int nbColorsInCombination) {
        _nbColorsInCombination = nbColorsInCombination;
    }

    public int getNbColorsInCombination() {
        return _nbColorsInCombination;
    }
}