package bunkovik.config;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class Tile {
    private final Image image;
    private int tileWidth;
    private int tileHeight;
    private final boolean acceptable;

    public int getTileWidth() {
        return tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public void setTileWidth(int tileWidth) {
        this.tileWidth = tileWidth;
    }

    public void setTileHeight(int tileHeight) {
        this.tileHeight = tileHeight;
    }

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
