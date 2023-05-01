package bunkovik.config.location;

import bunkovik.model.entity.Sheep;

import java.util.HashMap;
import java.util.logging.Logger;

public class LocationManager {
    // Logger
    private static Logger log = Logger.getLogger(Sheep.class.getName());

    private final HashMap<Integer, Location> locations = new HashMap<>();

    public Location getLocation(int locationId) {
        if (locations.containsKey(locationId)) {
            return locations.get(locationId);
        }

        locations.put(locationId, new Location(locationId));
        return locations.get(locationId);
    }
}
