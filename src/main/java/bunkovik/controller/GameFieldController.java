package bunkovik.controller;

import bunkovik.config.StateManager;
import bunkovik.view.GameField;
import javafx.scene.input.KeyEvent;
import java.util.ArrayList;

public class GameFieldController extends Controller{
    private ArrayList<String> input = new ArrayList<>();
    public void init() {
        if (isOpened) return;
        isOpened = true;
        view = new GameField(this);
        view.init();
    }


    public void keyPressedHandler(KeyEvent e) {
        String code = e.getCode().toString();

        if (!input.contains(code)) {
            input.add(code);
        }

        // Main Menu
        if (code.equals("M")) {
            input = new ArrayList<>();
            StateManager.toGameMenu();
        }

        // Go to Inventory
        if (code.equals("I")) {
            StateManager.toInventory();
        }
    }
    public void keyReleasedHandler(KeyEvent e) {
        String code = e.getCode().toString();
        input.remove(code);
    }
}
