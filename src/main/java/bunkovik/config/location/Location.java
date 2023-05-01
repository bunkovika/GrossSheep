package bunkovik.config.location;

import bunkovik.config.SpriteManager;
import bunkovik.config.tile.TileMap;
import bunkovik.config.tile.TileMapManager;
import bunkovik.model.entity.Sheep;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * The type Location.
 * <p>
 * A class representing a specific location.
 */
public class Location {
    // Logger
    private static Logger log = Logger.getLogger(Sheep.class.getName());

    // Data
    private JSONObject config;
    private final int locationId;
    private final String name;

    private Sheep sheep;
    private TileMap tileMap;
    private SpriteManager spriteManager;

    // State
    private boolean isOpened;

    public Location(int locationId) {
        this.locationId = locationId;

        try {
            File file = new File("src/main/resources/location/" + locationId + "/config.json");
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            config = new JSONObject(content);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.name = config.getString("name");

        log.info("Location \"" + getName() + "\" was created.");
    }
    public void init() {
        if (isOpened) return;

        // Sprite Manager Init
        spriteManager = new SpriteManager();

        // Tile Map Init
        tileMap = TileMapManager.createTileMap("src/main/resources/location/" + locationId + "/map.txt", 64);

        // Monsters Init
//        to do

        // Items Init
//       to do

//        going to new level to add
        isOpened = true;

        log.info("Location \"" + getName() + "\" was initialized.");
    }


    public void setPlayer(Sheep sheep) {
        this.sheep = sheep;
        spriteManager.addSprite(sheep);
    }

    public void unsetPlayer() {
        spriteManager.removeSprite(sheep);
        this.sheep = null;
    }

    public int getId() {
        return locationId;
    }

    public String getName() {
        return name;
    }

    public Sheep getSheep() {
        return sheep;
    }
    public TileMap getTileMap() {
        return tileMap;
    }

    public SpriteManager getSpriteManager() {
        return spriteManager;
    }
}

