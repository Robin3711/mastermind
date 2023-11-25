package models;

import java.util.ArrayList;
import java.util.List;

public class MasterMind {
    private List<MasterMindObserver> ObserverList = new ArrayList<>();
    public void addObserver(MasterMindObserver observer) {
        ObserverList.add(observer);
    }
}