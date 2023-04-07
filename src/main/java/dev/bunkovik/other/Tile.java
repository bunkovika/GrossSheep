package dev.bunkovik.other;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Tile {
    private final Image image;
    private final boolean acceptable;

    public Tile(String imageLink, boolean acceptable) {
        image = new Image(imageLink);
        this.acceptable = acceptable;
    }
    public Image getImage() {
        return image;
    }
    void setTile(GraphicsContext gc, double x, double y){
        gc.drawImage(image, x, y);
    }
    public boolean isAcceptable() {
        return acceptable;
    }
}
