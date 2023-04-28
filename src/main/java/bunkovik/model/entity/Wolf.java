package bunkovik.model.entity;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.logging.Logger;

public class Wolf extends Sprite{
    private static Logger log = Logger.getLogger(Sheep.class.getName());

    private final HashMap<Direction, Image> images = new HashMap<>();

    private Direction currentDirection;
    private boolean isDead;
    private final String name;
    public Wolf(String name) {
        super();
        this.name = name;
        this.isDead = false;

    }
    public void attack(){

    }
}
