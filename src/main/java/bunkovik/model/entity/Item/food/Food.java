package bunkovik.model.entity.Item.food;

import bunkovik.model.entity.Item.Item;
import bunkovik.model.entity.Sheep;

import java.util.logging.Logger;

public class Food extends Item {
    // Logger
    private static Logger log = Logger.getLogger(Sheep.class.getName());
    private final double health;
    public Food(int id, String name, double health) {
        super(id, name);
        this.health = health;
    }

    public void use(Sheep sheep) {
        sheep.inHealth(this);
        log.info("Sheep eat food");
    }

    public double getHealth() {
        return health;
    }
}
