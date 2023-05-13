package bunkovik.model.entity;

import bunkovik.core.sprite.Sprite;
import bunkovik.core.tile.TileMap;
import bunkovik.model.GameModel;

import java.util.logging.Logger;

public class Transition extends Sprite {
    // Logger
    private static Logger log = Logger.getLogger(Sheep.class.getName());
    private int portalId;
    private final int locationId;
    private final int playerX;
    private final int playerY;

    public Transition(int portalId, int locationId, int playerX, int playerY) {
        this.portalId = portalId;
        this.locationId = locationId;
        this.playerX = playerX;
        this.playerY = playerY;
    }

    public void activate() {

        GameModel gameModel = GameModel.getInstance();

        Sheep sheep = gameModel.getPlayer();

        gameModel.setLocation(locationId, false);

//        sheep.getInventory().resetInventory();
        sheep.setPosition(
                TileMap.convertTileToPixel((int)sheep.getX()/TileMap.getTileSize()),
                TileMap.convertTileToPixel((int)sheep.getY()/TileMap.getTileSize())
        );
    }
    public int getId() {
        return portalId;
    }


    public void setId(int id) {
        portalId = id;
    }
}
