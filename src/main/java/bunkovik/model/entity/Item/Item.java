package bunkovik.model.entity.Item;

import bunkovik.core.sprite.Sprite;
import bunkovik.model.entity.Sheep;

/**
 * The type AItem.
 * <p>
 * An abstract class representing an element and basic methods for interacting with it.
 */
public abstract class Item extends Sprite {
    /**
     * The Id.
     */
    protected final int id;
    /**
     * The Name.
     */
    protected final String name;

    /**
     * Instantiates a new A item.
     *
     * @param id   the id
     * @param name the name
     */
    public Item(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Take boolean.
     * <p>
     * A method that allows a character to take it.
     *
     * @param sheep the player
     * @return the boolean
     */
    public boolean take(Sheep sheep) {
        return sheep.getInventory().addItem(this);
    }

    /**
     * Drop boolean.
     * <p>
     * A method that allows a character to drop an item.
     *
     * @param sheep the player
     * @return the boolean
     */
    public boolean drop(Sheep sheep) {
        return sheep.getInventory().removeItem(this);
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }
}
