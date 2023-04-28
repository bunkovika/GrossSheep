package bunkovik.controller;

import bunkovik.config.StateManager;
import bunkovik.view.GameRules;
import javafx.scene.input.MouseEvent;

public class GameRulesController extends Controller {
    public void init() {
        if (isOpened) return;
        isOpened = true;
        view = new GameRules(this);
        view.init();
    }
    public void toGameRulesButtonClickHandler(MouseEvent e) {
        StateManager.toGameRules();
    }
    @Override
    public void tick(double delta){

    }
}
