package bunkovik.controller;

import bunkovik.config.StateManager;
import bunkovik.view.GameMenu;
import javafx.scene.input.MouseEvent;

public class GameMenuController extends Controller {
    public void init() {
        if (isOpened) return;
        isOpened = true;
        view = new GameMenu(this);
        view.init();
    }
    public void gameContinueButtonClickHandler(MouseEvent e) {
        StateManager.toContinueGame();
    }
    public void saveButtonClickHandler(MouseEvent e) {
        StateManager.toMainMenu();
    }
    public void toGameRulesButtonClickHandler(MouseEvent e) {
        StateManager.toGameField();
    }

}
