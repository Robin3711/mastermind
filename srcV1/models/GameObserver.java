package models;

/**
 * Interface for the GameObserver class.
 */
public interface GameObserver {
    /**
     * Called when a new attempt is performed.
     * @param attempt The attempt performed.
     */
    void onAttemptPerformed(Attempt attempt);
    /**
     * Called when a round is finished.
     */
    void onRoundFinished();
    /**
     * Called when the game is finished.
     */
    void onGameFinished();
}
