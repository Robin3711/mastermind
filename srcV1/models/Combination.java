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

    public void setPawns(PawnColor[] pawns) throws Exception {
        // Verify that the number of pawns is correct
        if (pawns.length != getNbColorsInCombination()) {
            throw new Exception("The number of pawns is incorrect");
        }
        this._pawns = pawns;
    }

    // Pour les tests
    public PawnColor[] getPawns() {
        return _pawns;
    }
}