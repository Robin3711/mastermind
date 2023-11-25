package controllers;

import models.MasterMind;
import views.SettingsWindowView;

public class MasterMindFacade {
    private MasterMind masterMind;
    public MasterMindFacade(MasterMind masterMind) {
        this.masterMind = masterMind;
    }

    public void quitMasterMind() {
        System.exit(0);
    }

    public void openSettingsWindow() {
        SettingsWindowView settingsWindow = new SettingsWindowView(this);
    }
}