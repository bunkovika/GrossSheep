package bunkovik.config;

import bunkovik.model.entity.Sprite;
import javafx.scene.canvas.GraphicsContext;
import java.util.ArrayList;
public class SpriteManager {
    private final ArrayList<Sprite> sprites;
    public SpriteManager() {
        sprites = new ArrayList<>();
    }

    public void render(GraphicsContext gc) {
        for (Sprite sprite : sprites) {
            sprite.render(gc);
        }
    }
    public void addSprite(Sprite sprite) {
        sprites.add(sprite);
    }
    public void removeSprite(Sprite sprite) {
        sprites.remove(sprite);
    }

}