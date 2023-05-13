package bunkovik.model.component;

import bunkovik.model.entity.Item.equipment.Weapon;
import bunkovik.model.entity.Sheep;

import java.util.Observable;
import java.util.logging.Logger;
/**
 * The type Equipment.
 * <p>
 * A class that represents a player's equipment (equipped items) and their management.
 */
public class Equipment extends Observable {
    // Logger
    private static Logger log = Logger.getLogger(Sheep.class.getName());
    private Weapon weapon;

    public void setEquipment(Weapon item, Inventory inventory) {
        inventory.removeItem(item);

        if (item instanceof Weapon) {
            if (weapon != null) {
                inventory.addItem(weapon);
            }

            weapon = item;
            log.info("Weapon \"" + item.getName() + "\" was equipped.");
        }
        setChanged();
        notifyObservers();
    }

    public void unsetEquipment(Weapon item, Inventory inventory) {
        if (item instanceof Weapon) {
            if (weapon == null) return;
            if (weapon.equals(item)) {
                if (inventory.addItem(item)) {
                    weapon = null;
                    log.info("Weapon \"" + item.getName() + "\" was unequipped.");
                }
            }
        }

        setChanged();
        notifyObservers();
    }

    public Weapon getWeapon() {
        return weapon;
    }

}
