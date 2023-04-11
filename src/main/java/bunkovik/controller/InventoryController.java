package bunkovik.controller;

import bunkovik.view.Inventory;

public class InventoryController extends Controller {
    public void init() {
        if (isOpened) return;
        isOpened = true;
        view = new Inventory(this);
        view.init();
    }
}
