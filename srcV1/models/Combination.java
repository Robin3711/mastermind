package models;

public class Combination {
    private int _nbColorsInCombination = 4;
    protected PawnColor[] _pawns;

    public Combination(int nbColorsInCombination)
    {
        this._nbColorsInCombination = nbColorsInCombination;
    }

    public int getNbColorsInCombination()
    {
        return _nbColorsInCombination;
    }

    public void setPawns(PawnColor[] pawns)
    {
        // Verify that the number of pawns is correct
        if (pawns.length != getNbColorsInCombination())
        {
            return;
        }
        this._pawns = pawns;
    }

    public PawnColor[] getPawns()
    {
        return _pawns;
    }

    @Override
    public String toString()
    {
        StringBuilder value = new StringBuilder(" ");
        for(int i = 0; i < this.getNbColorsInCombination(); i++)
        {
            value.append(this._pawns[i].name());
            value.append(" ");
        }
        return value.toString();
    }
}