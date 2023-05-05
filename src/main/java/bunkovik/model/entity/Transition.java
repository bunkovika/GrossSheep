package bunkovik.model.entity;

import bunkovik.core.sprite.Sprite;
import bunkovik.model.GameModel;

import java.util.logging.Logger;

public class Transition extends Sprite {
    // Logger
    private static Logger log = Logger.getLogger(Sheep.class.getName());

    private int portalId;
    private final int locationId;
    private final int playerX;
    private final int playerY;
    private int teleportationCounter = 0;

    public Transition(int portalId, int locationId, int playerX, int playerY) {
        this.portalId = portalId;
        this.locationId = locationId;
        this.playerX = playerX;
        this.playerY = playerY;
    }

    public void activate() {
        teleportationCounter++;
        GameModel gameModel = GameModel.getInstance();
        gameModel.init(false);
        gameModel.setLocation(locationId);
    }

    public int getId() {
        return portalId;
    }

    public void setId(int id) {
        portalId = id;
    }

}
