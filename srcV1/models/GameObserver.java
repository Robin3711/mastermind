package models;

public interface GameObserver {
    void updateWinRound();
    void updateLoseRound(int score);
    void updateForfeitRound();
}
