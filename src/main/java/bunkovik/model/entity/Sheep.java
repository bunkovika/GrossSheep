package bunkovik.model.entity;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.logging.Logger;

public class Sheep extends Sprite {
    // Logger
    private static Logger log = Logger.getLogger(Sheep.class.getName());

    private final HashMap<Direction, Image> images = new HashMap<>();

    private Direction currentDirection;
    private boolean isDead;
    private final String name;
    public Sheep(String name) {
        super();
        this.name = name;
        this.isDead = false;

        // Image of the different direction
        images.put(Direction.TOP, new Image("sheep/sheep_bottom.png"));
        images.put(Direction.RIGHT, new Image("sheep/sheep_bottom.png"));
        images.put(Direction.BOTTOM, new Image("sheep/sheep_bottom.png"));
        images.put(Direction.LEFT, new Image("sheep/sheep_bottom.png"));

        // Setting Up Default Image
        currentDirection = Direction.BOTTOM;
        setImage(images.get(currentDirection));

        log.info("Sheep was created!");
    }
    public String getName() {
        return name;
    }
    public boolean isDead() {
        return isDead;
    }
}