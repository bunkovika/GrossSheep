package bunkovik.model;
import bunkovik.config.PlayerConfig;
import bunkovik.config.SpriteManager;
import bunkovik.config.location.Location;
import bunkovik.config.location.LocationManager;
import bunkovik.config.tile.TileMap;
import bunkovik.model.entity.Sheep;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * The type Game model.
 * <p>
 * The main class responsible for the state of the game (game objects).
 */
public class GameModel {
    // Logger
    private static Logger log = Logger.getLogger(Sheep.class.getName());

    private static GameModel instance;
    private Sheep sheep;
    private Location currentLocation;
    private LocationManager locationManager;
    private int currentLocationId = 1;
    public void init(boolean fromSave) {
        // Loading Player Config
        JSONObject playerConfig = PlayerConfig.getPlayerConfig(fromSave, getIdOfCurrentLocation());
        System.out.println(getIdOfCurrentLocation());
        locationManager = new LocationManager();

        // Setting characteristics
        sheep = new Sheep(
                playerConfig.getString("name"),
                playerConfig.getDouble("damage"),
                playerConfig.getDouble("damageRadius"));



        sheep.setPosition(
                TileMap.convertTileToPixel(playerConfig.getInt("positionX")),
                TileMap.convertTileToPixel(playerConfig.getInt("positionY")));

        // Setting Up Location
        currentLocationId = playerConfig.getInt("locationId");
        setLocation(currentLocationId);

    }
    public static GameModel getInstance() {
        if (instance == null) {
            instance = new GameModel();
        }
        return instance;
    }
    public void setLocation(int locationId) {
        if (currentLocation != null) {
            currentLocation.unsetPlayer();

        }
        currentLocation = locationManager.getLocation(locationId);
        currentLocation.init();
        currentLocation.setPlayer(sheep);

        log.info("The location \"" + currentLocation.getName() + "\" was set.");
    }

    public Sheep getSheep() {
        return sheep;
    }
    public Location getCurrentLocation() {
        return currentLocation;
    }
    public int getIdOfCurrentLocation(){
        return currentLocationId;
    }
    public TileMap getTileMap() {
        return currentLocation.getTileMap();
    }

    public SpriteManager getSpriteManager() {
        return currentLocation.getSpriteManager();
    }
}
