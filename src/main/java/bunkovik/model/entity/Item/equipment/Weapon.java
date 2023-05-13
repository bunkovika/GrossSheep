package bunkovik.model.entity.Item.equipment;

import bunkovik.model.entity.Item.Item;
import bunkovik.model.entity.Sheep;

public class Weapon extends Item {
    private final double damage;
    private final double radius;
    public Weapon(int id, String name, double damage, double radius) {
        super(id, name);
        this.damage = damage;
        this.radius = radius;
    }

    public void equip(Sheep sheep) {
        sheep.setEquipment(this);
    }


    public void unEquip(Sheep sheep) {
        sheep.unsetEquipment(this);
    }

    public double getDamage() {
        return damage;
    }

    public double getRadius() {
        return radius;
    }
}
