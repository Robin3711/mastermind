import controllers.MasterMindFacade;
import models.MasterMind;
import views.MenuWindowView;
import views.TerminalView;

public class MasterMindApp {
    public static void main(String[] args) {
        MasterMind masterMind = new MasterMind();
        MasterMindFacade masterMindFacade = new MasterMindFacade(masterMind);

        TerminalView terminalView = new TerminalView();
        MenuWindowView menuWindowView = new MenuWindowView(masterMindFacade);

        masterMind.addObserver(terminalView);
        masterMind.addObserver(menuWindowView);
    }
}