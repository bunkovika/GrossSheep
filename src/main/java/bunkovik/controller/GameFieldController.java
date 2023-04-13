package bunkovik.controller;

import bunkovik.config.StateManager;
import bunkovik.view.GameField;
import javafx.scene.input.KeyEvent;
import java.util.ArrayList;

public class GameFieldController extends Controller {
    // Inputs
    private ArrayList<String> input = new ArrayList<>();

    public void init() {
        if (isOpened) return;

        // Init View
        view = new GameField(this);
        view.init();

        // Setting Up State
        isOpened = true;
    }
//    event by the key
    public void keyPressedHandler(KeyEvent e) {
        String code = e.getCode().toString();

        if (!input.contains(code)) {
            input.add(code);
        }

        // Game Menu
        if (code.equals("M")) {
            input = new ArrayList<>();
            StateManager.toGameMenu();
        }

        //Game Field
        if (code.equals("ESCAPE")) {
            input = new ArrayList<>();
            StateManager.toGameField();
        }

        // Inventory
        if (code.equals("I")) {
            input = new ArrayList<>();
            StateManager.toInventory();
        }
    }
    public void keyReleasedHandler(KeyEvent e) {
        String code = e.getCode().toString();
        input.remove(code);
    }
}