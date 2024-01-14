import controllers.GameController;
import views.MenuWindow;

public class MasterMindApp {
    public static void main(String[] args) {
        GameController gameController = new GameController();
        new MenuWindow(gameController);
    }
}