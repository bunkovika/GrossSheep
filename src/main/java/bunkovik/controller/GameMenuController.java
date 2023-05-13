package bunkovik.controller;

import bunkovik.core.SheepConfig;
import bunkovik.core.StateManager;
import bunkovik.core.location.LocationManager;
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
        StateManager.continueGame();
    }
    public void gameSaveButtonClickHandler(MouseEvent e) {
        SheepConfig.savePlayerConfig();
        LocationManager.savedLocation();
    }

    public void toMainMenuButtonClickHandler(MouseEvent e) {
        StateManager.goToMainMenu();
    }
    public void toGameRulesButtonClickHandler(MouseEvent e) {
        StateManager.goToGameRules();
    }
    @Override
    public void tick(double delta) { }
}
