package bunkovik.core.sprite;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;

public abstract class Sprite {

    protected double positionX;

    protected double positionY;
    protected double width;
    protected double height;

    protected Image image;
    protected enum Direction {
        /**
         * Top direction.
         */
        TOP,
        /**
         * Right direction.
         */
        RIGHT,
        /**
         * Bottom direction.
         */
        BOTTOM,
        /**
         * Left direction.
         */
        LEFT
    }

    protected final HashMap<Direction, Image> images = new HashMap<>();

    // State
    protected Direction currentDirection;


    public Sprite() {
        positionX = 0;
        positionY = 0;
    }

    public void setImage(Image image) {
        this.image = image;
        width = image.getWidth();
        height = image.getHeight();
    }

    public void setImage(String filename) {
        Image image = new Image(filename);
        setImage(image);
    }

    public void setPosition(double x, double y) {
        positionX = x;
        positionY = y;
    }

    public void update(double delta) {

    };

    public void render(GraphicsContext gc) {
        gc.drawImage(image, positionX, positionY);
    }

    public Rectangle2D getCollisionBox() {
        return new Rectangle2D(positionX, positionY, width, height);
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }


    public double getX() {
        return positionX;
    }

    public double getY() {
        return positionY;
    }


    public Image getImage() {
        return image;
    }

    public void moveUp(double path) {
        if (currentDirection != Direction.TOP) {
            currentDirection = Direction.TOP;
            setImage(images.get(currentDirection));
        }
        positionY -= path;
    }

    public void moveRight(double path) {
        if (currentDirection != Direction.RIGHT) {
            currentDirection = Direction.RIGHT;
            setImage(images.get(currentDirection));
        }
        positionX += path;
    }

    public void moveDown(double path) {
        if (currentDirection != Direction.BOTTOM) {
            currentDirection = Direction.BOTTOM;
            setImage(images.get(currentDirection));
        }
        positionY += path;
    }

    public void moveLeft(double path) {
        if (currentDirection != Direction.LEFT) {
            currentDirection = Direction.LEFT;
            setImage(images.get(currentDirection));
        }
        positionX -= path;
    }
}

