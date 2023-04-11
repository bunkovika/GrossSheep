package bunkovik.model.entity;

import javafx.scene.image.Image;

public class Sprite {
    protected double positionX;
    protected double positionY;
    protected double width;
    protected double height;
    protected Image image;

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
}
