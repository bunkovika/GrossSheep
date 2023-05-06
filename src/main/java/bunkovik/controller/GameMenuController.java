package bunkovik.controller;

import bunkovik.core.SheepConfig;
import bunkovik.core.StateManager;
import bunkovik.view.GameMenu;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class GameMenuController extends Controller {
    public void init() {
        if (isOpened) return;
        isOpened = true;
        view = new GameMenu(this);
        view.init();
    }
//
//    public void keyPressedHandler(KeyEvent e) {
//        String code = e.getCode().toString();
//
//        if (code.equals("ESCAPE")) {
//            StateManager.continueGame();
//        }
//    }

    public void gameContinueButtonClickHandler(MouseEvent e) {
        StateManager.continueGame();
    }
    public void gameSaveButtonClickHandler(MouseEvent e) {
        SheepConfig.savePlayerConfig();
    }
    public void toMainMenuButtonClickHandler(MouseEvent e) {
        StateManager.goToMainMenu();
    }
    @Override
    public void tick(double delta) { }
}
