package bunkovik.core;

import bunkovik.core.tile.TileMap;
import bunkovik.model.GameModel;
import bunkovik.model.entity.Item.Item;
import bunkovik.model.entity.Sheep;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SheepConfig {
    // Logger
    private static Logger log = Logger.getLogger(Sheep.class.getName());

    public static JSONObject getPlayerConfig(boolean fromSave, int level_id) {
        String path = fromSave ? "save/savedLevel.json" : "sheepConfig/Level" + level_id + ".json";

        try {
            File file = new File(path);
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));

            return new JSONObject(content);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean isExistPlayerSave() {
        File f = new File("save/savedLevel.json");
        return f.isFile();
    }

    public static void savePlayerConfig() {
        JSONObject playerConfig = new JSONObject();
        GameModel gameModel = GameModel.getInstance();
        Sheep sheep = gameModel.getPlayer();

        playerConfig.put("name", sheep.getName());
        playerConfig.put("maxHealth", sheep.getHP().getMaxHealth());
        playerConfig.put("health", sheep.getHealth());
        playerConfig.put("damage", sheep.getBasicDamage());
        playerConfig.put("damageRadius", sheep.getBasicDamageRadius());
        playerConfig.put("locationId", gameModel.getCurrentLocation().getId());
        playerConfig.put("positionX", TileMap.convertPixelToTile(sheep.getX()));
        playerConfig.put("positionY", TileMap.convertPixelToTile(sheep.getY()));

        Item currentWeapon = sheep.getCurrentWeapon();
        if (currentWeapon != null) {
            playerConfig.put("equippedWeaponId", currentWeapon.getId());
        } else {
            playerConfig.put("equippedWeaponId", -1);
        }

        JSONArray inventory = new JSONArray();

        playerConfig.put("inventory", inventory);

        for (Item item : sheep.getInventory().getItems()) {
            inventory.put(item.getId());
        }

        try (
                BufferedWriter writer = new BufferedWriter(new FileWriter("save/savedLevel.json"))
        ) {
            writer.write(playerConfig.toString());
        } catch (IOException e) {
            log.log(Level.WARNING, e.getMessage(), e);
        }
    }
}
