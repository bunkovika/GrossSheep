package bunkovik.controller;

import bunkovik.config.StateManager;
import bunkovik.view.Inventory;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public class InventoryController extends Controller {
    private ArrayList<String> input = new ArrayList<>();
    public void init() {
        if (isOpened) return;
        isOpened = true;
        view = new Inventory(this);
        view.init();
    }
    @Override
    public void tick(double delta){

    }
    public void keyPressedHandler(KeyEvent e) {
        String code = e.getCode().toString();

        if (!input.contains(code)) {
            input.add(code);
        }

        //Game Field
        if (code.equals("ESCAPE")) {
            input = new ArrayList<>();
            StateManager.toGameField();
        }
    }
    public void keyReleasedHandler(KeyEvent e) {
        String code = e.getCode().toString();
        input.remove(code);
    }
}
