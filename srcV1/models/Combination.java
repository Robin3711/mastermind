package models;

/**
 * Represents the combination of pawn colors in the MasterMind game.
 */
public class Combination {
    private int _nbColorsInCombination = 4;
    protected PawnColor[] _pawns;

    /**
     * Constructor for the Combination class.
     * @param nbColorsInCombination The number of colors in the combination.
     */
    public Combination(int nbColorsInCombination)
    {
        this._nbColorsInCombination = nbColorsInCombination;
    }

    /**
     * Gets the number of colors in the combination.
     * @return The number of colors in the combination.
     */
    public int getNbColorsInCombination()
    {
        return _nbColorsInCombination;
    }

    /**
     * Sets the pawn colors for the combination.
     * @param pawns An array of PawnColor representing the pawn colors.
     */
    public void setPawns(PawnColor[] pawns)
    {
        // Verify that the number of pawns is correct
        if (pawns.length != getNbColorsInCombination())
        {
            return;
        }
        this._pawns = pawns;
    }

    /**
     * Overrides the toString method to provide a string representation of the combination.
     * @return A string representation of the combination.
     */
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
