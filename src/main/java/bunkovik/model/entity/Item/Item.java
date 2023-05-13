package bunkovik.model.entity.Item;

import bunkovik.core.sprite.Sprite;
import bunkovik.model.entity.Sheep;

public abstract class Item extends Sprite {

    protected final int id;

    protected final String name;

    public Item(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public boolean take(Sheep sheep) {
        return sheep.getInventory().addItem(this);
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
