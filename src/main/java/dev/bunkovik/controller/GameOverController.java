package dev.bunkovik.controller;

import dev.bunkovik.other.StateManager;
import dev.bunkovik.view.GameOver;
import javafx.scene.input.MouseEvent;

public class GameOverController extends Controller {
    public void init() {
        if (wasInitialized) return;
        wasInitialized = true;
        view = new GameOver(this);
        view.init();
    }
    public void goToMainMenuButtonClickHandler(MouseEvent e) {
        StateManager.goToMainMenu();
    }

}
