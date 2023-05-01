package bunkovik.model.entity;

import bunkovik.view.component.game.HPBox;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.logging.Logger;

public class Sheep extends Sprite {
    // Logger
    private static Logger log = Logger.getLogger(Sheep.class.getName());

    private final HashMap<Direction, Image> images = new HashMap<>();

    private Direction currentDirection;
    private boolean isDead;
    private final String name;
    private final double basicDamage;
    private final double damageRadius;

    public Sheep(String name, double damage, double damageRadius) {
        super();
        this.name = name;
        this.basicDamage = damage;
        this.damageRadius = damageRadius;
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
    public String getName() {
        return name;
    }

}