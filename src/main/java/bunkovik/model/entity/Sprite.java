package bunkovik.model.entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Sprite {
    protected double positionX;
    protected double positionY;
    protected double width;
    protected double height;
    protected Image image;
    protected boolean isCollision;
    protected enum Direction {
        TOP,
        RIGHT,
        BOTTOM,
        LEFT
    }

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
    public void render(GraphicsContext gc) {
        gc.drawImage(image, positionX, positionY);
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
}