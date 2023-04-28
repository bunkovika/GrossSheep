package bunkovik.controller;

import bunkovik.config.StateManager;
import bunkovik.view.GameOver;
import javafx.scene.input.MouseEvent;

public class GameOverController extends Controller {
    public void init() {
        if (isOpened) return;
        isOpened = true;
        view = new GameOver(this);
        view.init();
    }

    public void toMainMenuButtonClickHandler(MouseEvent e) {
        StateManager.toMainMenu();
    }
    @Override
    public void tick(double delta){

    }

}
