package bunkovik.core.location;

import bunkovik.core.factory.ItemFactory;
import bunkovik.core.factory.WolfFactory;
import bunkovik.core.factory.TransitionFactory;
import bunkovik.core.sprite.SpriteManager;
import bunkovik.core.tile.TileMap;
import bunkovik.core.tile.TileMapManager;
import bunkovik.model.entity.Item.Item;
import bunkovik.model.entity.Sheep;
import bunkovik.model.entity.Transition;
import bunkovik.model.entity.Wolf;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Logger;

public class Location {
    // Logger
    private static Logger log = Logger.getLogger(Sheep.class.getName());

    // Data
    private JSONObject config;
    private final int locationId;
    private final String name;

    // Links
    private Sheep sheep;
    private ArrayList<Wolf> wolves = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();
    private ArrayList<Transition> transitions = new ArrayList<>();
    private TileMap tileMap;
    private SpriteManager spriteManager;

    // State
    private boolean isOpened;

    public Location(int locationId, boolean fromSaved) {
        String path = fromSaved ? "save/savedLocation.json" : "src/main/resources/location/" + locationId + "/config.json";

        this.locationId = locationId;

        try {
            File file = new File(path);
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
        for (Object monsterConfig : config.getJSONArray("monsters")) {
            Wolf wolf = WolfFactory.getWolf(((JSONObject) monsterConfig).getInt("id"));
            wolf.setPosition(
                    TileMap.convertTileToPixel(((JSONObject) monsterConfig).getInt("positionX")),
                    TileMap.convertTileToPixel(((JSONObject) monsterConfig).getInt("positionY")));

            wolves.add(wolf);
            spriteManager.addSprite(wolf);
        }

        // Items Init
        for (Object itemConfig : config.getJSONArray("items")) {
            Item item = ItemFactory.getItem(((JSONObject) itemConfig).getInt("id"));
            item.setPosition(
                    TileMap.convertTileToPixel(((JSONObject) itemConfig).getInt("positionX")),
                    TileMap.convertTileToPixel(((JSONObject) itemConfig).getInt("positionY")));

            items.add(item);
            spriteManager.addSprite(item);
        }

        // Portals Init
        for (Object portalConfig : config.getJSONArray("portals")) {
            Transition transition = TransitionFactory.getTransition(((JSONObject) portalConfig).getInt("id"));

            transition.setPosition(
                    TileMap.convertTileToPixel(((JSONObject) portalConfig).getInt("positionX")),
                    TileMap.convertTileToPixel(((JSONObject) portalConfig).getInt("positionY")));

            transitions.add(transition);
            spriteManager.addSprite(transition);
        }

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

    public ArrayList<Wolf> getMonsters() {
        return wolves;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public ArrayList<Transition> getPortals() {
        return transitions;
    }

    public TileMap getTileMap() {
        return tileMap;
    }

    public SpriteManager getSpriteManager() {
        return spriteManager;
    }
}
