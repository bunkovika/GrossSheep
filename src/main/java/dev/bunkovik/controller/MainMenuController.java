package dev.bunkovik.controller;

import dev.bunkovik.other.StateManager;
import dev.bunkovik.view.MainMenu;
import javafx.scene.input.MouseEvent;

public class MainMenuController extends Controller {
    public void init() {
        if (wasInitialized) return;
        wasInitialized = true;
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
        StateManager.startGame(false);
    }

}
