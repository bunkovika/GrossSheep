package bunkovik.core.location;

import bunkovik.core.factory.ItemFactory;
import bunkovik.core.factory.TransitionFactory;
import bunkovik.core.factory.WolfFactory;
import bunkovik.core.tile.TileMap;
import bunkovik.model.GameModel;
import bunkovik.model.entity.Item.Item;
import bunkovik.model.entity.Sheep;
import bunkovik.model.entity.Transition;
import bunkovik.model.entity.Wolf;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LocationManager {
    // Logger
    private static Logger log = Logger.getLogger(Sheep.class.getName());

    // Cached Locations
    private final HashMap<Integer, Location> locations = new HashMap<>();

    public Location getLocation(int locationId, boolean fromSaved) {
        if (locations.containsKey(locationId)) {
            return locations.get(locationId);
        }

        locations.put(locationId, new Location(locationId, fromSaved));
        return locations.get(locationId);
    }
    public static void savedLocation(){
        JSONObject locationConfig = new JSONObject();
        GameModel gameModel = GameModel.getInstance();
        Location location = gameModel.getCurrentLocation();

        locationConfig.put("name", location.getName());

        JSONArray monsterArray = new JSONArray();
        for (int i = 0; i < location.getMonsters().size(); i++) {
            JSONObject monsterConfig = new JSONObject();
            if(location.getMonsters().get(i).isDead()){
                monsterConfig.put("id", -1);
                monsterConfig.put("positionX", -1);
                monsterConfig.put("positionY", -1);
            } else {
                monsterConfig.put("id", i+1);
                monsterConfig.put("positionX", TileMap.convertPixelToTile(location.getMonsters().get(i).getX()));
                monsterConfig.put("positionY", TileMap.convertPixelToTile(location.getMonsters().get(i).getY()));
            }
            monsterArray.put(monsterConfig);
        }
        locationConfig.put("monsters", monsterArray);

        JSONArray itemsArray = new JSONArray();
        for (int i = 0; i < location.getItems().size(); i++) {
            JSONObject itemsConfig = new JSONObject();
            Item item = location.getItems().get(i);
            itemsConfig.put("id", i+1);
            itemsConfig.put("positionX", TileMap.convertPixelToTile(item.getX()));
            itemsConfig.put("positionY", TileMap.convertPixelToTile(item.getY()));
            itemsArray.put(itemsConfig);
        }
        locationConfig.put("items", itemsArray);

        JSONArray portalsArray = new JSONArray();
        for (int i = 0; i < location.getPortals().size(); i++) {
            JSONObject transitionConfig = new JSONObject();
            Transition portal = location.getPortals().get(i);
            transitionConfig.put("id", i+1);
            transitionConfig.put("positionX", TileMap.convertPixelToTile(portal.getX()));
            transitionConfig.put("positionY", TileMap.convertPixelToTile(portal.getY()));
            portalsArray.put(transitionConfig);
        }
        locationConfig.put("portals", portalsArray);

        try (
                BufferedWriter writer = new BufferedWriter(new FileWriter("save/savedLocation.json"))
        ) {
            writer.write(locationConfig.toString());
        } catch (IOException e) {
            log.log(Level.WARNING, e.getMessage(), e);
        }
    }
}
