package bunkovik.controller;

import bunkovik.core.StateManager;
import bunkovik.model.GameModel;
import bunkovik.model.component.Inventory;
import bunkovik.model.entity.Item.Item;
import bunkovik.model.entity.Item.equipment.Weapon;
import bunkovik.model.entity.Item.food.Food;
import bunkovik.model.entity.Sheep;
import bunkovik.view.InventoryView;
import bunkovik.view.component.inventory.ItemView;
import javafx.scene.input.KeyEvent;
public class InventoryController extends Controller {

    public void init() {
        if (isOpened) return;
        isOpened = true;
        view = new InventoryView(this);
        view.init();
    }

    public void keyPressedHandler(KeyEvent e) {
        Sheep sheep = GameModel.getInstance().getPlayer();
        Inventory inventory = sheep.getInventory();
        String code = e.getCode().toString();

        if (code.equals("ESCAPE") || code.equals("I")) {
            StateManager.continueGame();
        }

        if (code.equals("E")) {
            if (e.getTarget() instanceof ItemView) {
                Item item = ((ItemView) e.getTarget()).getItem();

                if (item instanceof Weapon) {
                    if (inventory.isInInventory(item)) {
                        ((Weapon) item).equip(sheep);
                    } else {
                        ((Weapon) item).unEquip(sheep);
                    }
                }
            }
        }
        if (code.equals("C")) {
            if (e.getTarget() instanceof ItemView) {
                Item item = ((ItemView) e.getTarget()).getItem();

                if (item instanceof Food) {
                    ((Food) item).use(sheep);
                }
            }

        }
    }
    @Override
    public void tick(double delta) {

    }
}
