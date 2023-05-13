package bunkovik.controller;

import bunkovik.core.StateManager;
import bunkovik.view.GameWin;
import javafx.scene.input.MouseEvent;

public class GameWinController extends Controller {
    public void init() {
        if (isOpened) return;
        isOpened = true;
        view = new GameWin(this);
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
