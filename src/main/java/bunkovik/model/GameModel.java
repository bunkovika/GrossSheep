package bunkovik.model;

import bunkovik.core.SheepConfig;
import bunkovik.core.factory.ItemFactory;
import bunkovik.core.location.Location;
import bunkovik.core.location.LocationManager;
import bunkovik.core.sprite.SpriteManager;
import bunkovik.core.tile.TileMap;
import bunkovik.model.entity.Item.Item;
import bunkovik.model.entity.Item.equipment.Weapon;
import bunkovik.model.entity.Sheep;
import bunkovik.model.entity.Transition;
import bunkovik.model.entity.Wolf;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.logging.Logger;


public class GameModel {
    // Logger
    private static Logger log = Logger.getLogger(Sheep.class.getName());

    private static GameModel instance;
    private Sheep sheep;
    private Location currentLocation;
    private LocationManager locationManager;
    private int currentLocationId;

    public void init(boolean fromSave) {
        currentLocationId++;
        // Loading Player Config
        JSONObject playerConfig = SheepConfig.getPlayerConfig(fromSave, getIdOfCurrentLocation());
        locationManager = new LocationManager();

        // Setting characteristics
        sheep = new Sheep(
                playerConfig.getString("name"),
                playerConfig.getDouble("health"),
                playerConfig.getDouble("damage"),
                playerConfig.getDouble("damageRadius"));

        sheep.getHP().setMaxHealth(playerConfig.getDouble("maxHealth"));

        // Setting Equipped Items
        int equippedWeaponId = playerConfig.getInt("equippedWeaponId");
        if (equippedWeaponId != -1) {
            sheep.setEquipment((Weapon) ItemFactory.getItem(equippedWeaponId));
        }


        // Setting items in the inventory
        for (Object item : playerConfig.getJSONArray("inventory")) {
            sheep.getInventory().addItem(ItemFactory.getItem((int) item));
        }

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

    public Sheep getPlayer() {
        return sheep;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }
    public int getIdOfCurrentLocation(){
        return currentLocationId;
    }
    public void setIdOfCurrentLocation(){
       currentLocationId = 0;
    }

    public TileMap getTileMap() {
        return currentLocation.getTileMap();
    }

    public ArrayList<Wolf> getMonsters() {
        return currentLocation.getMonsters();
    }

    public ArrayList<Item> getItems() {
        return currentLocation.getItems();
    }

    public ArrayList<Transition> getPortals() {
        return currentLocation.getPortals();
    }

    public SpriteManager getSpriteManager() {
        return currentLocation.getSpriteManager();
    }
}
