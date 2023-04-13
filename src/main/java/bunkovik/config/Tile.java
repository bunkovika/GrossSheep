package bunkovik.config;

import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Tile  {
    private final Image image;
    private final boolean passable;
    private final double x;
    private final double y;

    public Tile(String imagePath, boolean passable, double x, double y) {
        this.passable = passable;
        this.image = new Image(imagePath);
        this.x = x;
        this.y = y;
    }

    public Image getImage() {
        return image;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(image, x, y);
    }

    public boolean isPassable() {
        return passable;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
