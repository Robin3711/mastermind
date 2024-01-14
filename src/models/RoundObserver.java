package models;

public interface RoundObserver {
    void updateWinRound();
    void updateLoseRound(int score);
    void updateForfeitRound();
}
