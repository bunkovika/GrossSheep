package bunkovik.model.entity;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.logging.Logger;

public class Sheep extends Sprite {
    // Logger
    private static Logger log = Logger.getLogger(Sheep.class.getName());
    private final HashMap<Direction, Image> images = new HashMap<>();
    private String name;
    private enum Direction {
        TOP,
        RIGHT,
        BOTTOM,
        LEFT
    }
    private Direction currentDirection;
    public Sheep(String name) {
        super();
        this.name = name;

        // Setting Up Direction Images
        images.put(Direction.TOP, new Image("sheep/sheep_bottom.png"));
        images.put(Direction.RIGHT, new Image("sheep/sheep_bottom.png"));
        images.put(Direction.BOTTOM, new Image("sheep/sheep_bottom.png"));
        images.put(Direction.LEFT, new Image("sheep/sheep_bottom.png"));

        currentDirection = Direction.BOTTOM;
        setImage(images.get(currentDirection));

        log.info("Player was created!");
    }


}
