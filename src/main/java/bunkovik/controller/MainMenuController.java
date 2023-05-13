package bunkovik.controller;

import bunkovik.core.StateManager;
import bunkovik.view.MainMenu;
import javafx.scene.input.MouseEvent;

public class MainMenuController extends Controller {
    public void init() {
        if (isOpened) return;
        isOpened = true;
        view = new MainMenu(this);
        view.init();
    }
    public void gameLoadButtonClickHandler(MouseEvent e) {
        StateManager.startGame(true);
    }
    public void gameStartButtonClickHandler(MouseEvent e) {
        StateManager.startGame(false);
    }
    public void gameRulesButtonClickHandler(MouseEvent e) {
        StateManager.goToGameRules();
    }
    @Override
    public void tick(double delta) {
        view.render();
    }
}
