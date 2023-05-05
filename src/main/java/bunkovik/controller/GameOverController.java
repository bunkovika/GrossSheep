package bunkovik.controller;

import bunkovik.core.StateManager;
import bunkovik.view.GameOver;
import javafx.scene.input.MouseEvent;

public class GameOverController extends Controller {
    public void init() {
        if (isOpened) return;
        isOpened = true;
        view = new GameOver(this);
        view.init();
    }

    public void goToMainMenuButtonClickHandler(MouseEvent e) {
        StateManager.goToMainMenu();
    }

    @Override
    public void tick(double delta) {
        view.render();
    }
}
