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
    private Sheep player;
    private Location currentLocation;
    private LocationManager locationManager;
    private int currentLocationId;

    public void init(boolean fromSave) {
        currentLocationId++;
        // Loading Player Config
        JSONObject playerConfig = SheepConfig.getPlayerConfig(fromSave);
        locationManager = new LocationManager();

        // Setting characteristics
        player = new Sheep(
                playerConfig.getString("name"),
                playerConfig.getDouble("health"),
                playerConfig.getDouble("damage"),
                playerConfig.getDouble("damageRadius"));

        player.getHP().setMaxHealth(playerConfig.getDouble("maxHealth"));


        int equippedWeaponId = playerConfig.getInt("equippedWeaponId");
        if (equippedWeaponId != -1) {
            player.setEquipment((Weapon) ItemFactory.getItem(equippedWeaponId));
        }
        // Setting items in the inventory
        for (Object item : playerConfig.getJSONArray("inventory")) {
            player.getInventory().addItem(ItemFactory.getItem((int) item));
        }

        player.setPosition(
                TileMap.convertTileToPixel(playerConfig.getInt("positionX")),
                TileMap.convertTileToPixel(playerConfig.getInt("positionY")));

        // Setting Up Location

            setLocation(playerConfig.getInt("locationId"),fromSave);

    }
    public static GameModel getInstance() {
        if (instance == null) {
            instance = new GameModel();
        }
        return instance;
    }
    public void setLocation(int locationId, boolean fromSaved) {
        if (currentLocation != null) {
            currentLocation.unsetPlayer();
        }
        if(fromSaved){
            currentLocation = locationManager.getLocation(locationId, true);
        }else{
            currentLocation = locationManager.getLocation(locationId,false);
        }
        currentLocation.init();
        currentLocation.setPlayer(player);

        log.info("The location \"" + currentLocation.getName() + "\" was set.");
    }
//    public void setLocation(int locationId) {
//        if (currentLocation != null) {
//            currentLocation.unsetPlayer();
//        }
//
//        currentLocation = locationManager.getLocation(locationId,false);
//        currentLocation.init();
//        currentLocation.setPlayer(player);
//
//        log.info("The location \"" + currentLocation.getName() + "\" was set.");
//    }
    public void setIdOfCurrentLocation(){
        currentLocationId = 0;
    }

    public Sheep getPlayer() {
        return player;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public TileMap getTileMap() {
        return currentLocation.getTileMap();
    }

    public ArrayList<Wolf> getWolves() {
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

    public int getCurrentLocationId() {
        return currentLocationId;
    }
}
