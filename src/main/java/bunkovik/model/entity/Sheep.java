package bunkovik.model.entity;

import bunkovik.core.StateManager;
import bunkovik.core.sprite.Sprite;
import bunkovik.model.component.Equipment;
import bunkovik.model.component.HP;
import bunkovik.model.component.Inventory;
import bunkovik.model.entity.Item.Item;
import bunkovik.model.entity.Item.equipment.Weapon;
import bunkovik.model.entity.Item.food.Food;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

import java.util.logging.Logger;


public class Sheep extends Sprite {
    // Logger
    private static Logger log = Logger.getLogger(Sheep.class.getName());


    private boolean isDead;
    private boolean isWinner;
    private double lastAttack;

    // Characteristics
    private final String name;
    private final HP health;
    private final double basicDamage;
    private final double damageRadius;
    private double speed = 160; // px per second
    private double attackSpeed = 300; // in ms

    // Inventory
    private final Inventory inventory;

    // Equipped Items
    private final Equipment equipment;


    public Sheep(String name, double health, double damage, double damageRadius) {
        super();
        this.name = name;
        this.health = new HP(health);
        this.basicDamage = damage;
        this.damageRadius = damageRadius;
        this.inventory = new Inventory(8);
        this.equipment = new Equipment();
        this.isDead = false;

        // Setting Up Direction Images
        images.put(Direction.TOP, new Image("player/player_top.png"));
        images.put(Direction.RIGHT, new Image("player/player_right.png"));
        images.put(Direction.BOTTOM, new Image("player/player_bottom.png"));
        images.put(Direction.LEFT, new Image("player/player_left.png"));

        // Setting Up Default Image
        currentDirection = Direction.BOTTOM;
        setImage(images.get(currentDirection));

        log.info("Player was created!");
    }

    public void attack(Wolf wolf) {
        if ((System.currentTimeMillis() - lastAttack) < attackSpeed) {
            return;
        }

        lastAttack = System.currentTimeMillis(); // cooldown

        if (!wolf.isDead()) {
            wolf.inAttack(this);
        }
    }
    public void inAttack(Wolf wolf) {
        double incomingDamage = wolf.getDamage() * 0.5; // Given the character's armor.

        log.info("Player was attacked by monster \"" + wolf.getName() + "\"." + "Incoming damage is " + incomingDamage + ".");
        health.reduceHealth(incomingDamage);
        if (health.getHealth() == 0) {
            isDead = true;
            log.info("Player is dead!");

            StateManager.gameOver();
        }
    }
    public void inHealth(Food food) {
        if (!inventory.isInInventory(food)) return;
        health.addHealth(food.getHealth());
        inventory.removeItem(food);

        log.info("The potion \"" + food.getName() + "\" was used. " + food.getHealth() + " health has been added.");
    }
    public void setEquipment(Weapon item) {
        equipment.setEquipment(item, inventory);
    }
    public void unsetEquipment(Weapon item) {
        equipment.unsetEquipment(item, inventory);
    }

    public Inventory getInventory() {
        return inventory;
    }
    public Equipment getEquipment() {
        return equipment;
    }

    public double getDamage() {
        if (equipment.getWeapon() == null) return basicDamage;
        return basicDamage + equipment.getWeapon().getDamage();
    }

    public double getBasicDamage() {
        return basicDamage;
    }

    public double getDamageRadius() {
        if (equipment.getWeapon() == null) return basicDamage;
        return damageRadius + equipment.getWeapon().getRadius();
    }

    public double getBasicDamageRadius() {
        return damageRadius;
    }

    public String getName() {
        return name;
    }

    public double getHealth() {
        return health.getHealth();
    }

    public HP getHP() {
        return health;
    }

    public Item getCurrentWeapon() {
        return equipment.getWeapon();
    }

    public double getSpeed() {
        return speed;
    }

    public boolean isDead() {
        return isDead;
    }

    // Intersections

    public boolean intersectsAttackBox(Sprite s) {
        return s.getCollisionBox().intersects(this.getAttackCollisionBox());
    }

    public boolean intersectsMoveBox(Sprite s) {
        return s.getCollisionBox().intersects(this.getMoveBox());
    }

    public Rectangle2D getAttackCollisionBox() {
        switch (currentDirection) {
            case TOP:
                return new Rectangle2D(positionX, positionY - getDamageRadius(), width, getDamageRadius());
            case RIGHT:
                return new Rectangle2D(positionX + width, positionY, getDamageRadius(), height);
            case BOTTOM:
                return new Rectangle2D(positionX, positionY + height, width, getDamageRadius());
            case LEFT:
                return new Rectangle2D(positionX - getDamageRadius(), positionY, getDamageRadius(), height);
        }

        return new Rectangle2D(positionX, positionY - getDamageRadius(), width, getDamageRadius());
    }

    public Rectangle2D getMoveBox() {
        return new Rectangle2D(positionX, positionY + height - 16, width, 16);
    }

}