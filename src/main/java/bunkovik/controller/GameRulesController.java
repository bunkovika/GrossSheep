package bunkovik.controller;

import bunkovik.core.StateManager;
import bunkovik.view.GameRules;
import javafx.scene.input.KeyEvent;

public class GameRulesController extends Controller{
    @Override
    public void init() {
        if (isOpened) return;
        isOpened = true;
        view = new GameRules(this);
        view.init();
    }
    public void keyPressedHandler(KeyEvent e) {
        String code = e.getCode().toString();

        if (code.equals("ESCAPE")) {
            if(StateManager.isIsStarted())
            {
               StateManager.continueGame();
            }
            else {
                StateManager.goToMainMenu();
            }
        }
    }
        @Override
    public void tick(double delta) {

    }
}
