package models;

public interface GameObserver {
    void onAttemptPerformed(Attempt attempt);
    void onRoundFinished();
    void onGameFinished();
}
