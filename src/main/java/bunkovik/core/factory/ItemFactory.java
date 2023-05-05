package bunkovik.core.factory;

import bunkovik.model.entity.Item.Item;
import bunkovik.model.entity.Item.equipment.Weapon;
import bunkovik.model.entity.Item.food.Food;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ItemFactory {

    public static Item getItem(int id) {
        try {
            File file = new File("src/main/resources/item/" + id + "/config.json");
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            JSONObject config = new JSONObject(content);

            String itemType = config.getString("type");

            switch (itemType) {
                case "food":
                    return createBottle(config);
                case "weapon":
                    return createWeapon(config);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    private static Item createBottle(JSONObject config) {
        Food food = new Food(
                config.getInt("id"),
                config.getString("name"),
                config.getDouble("health"));
        food.setImage("item/" + config.getInt("id") + "/image.png");

        return food;
    }
    private static Item createWeapon(JSONObject config) {
        Weapon weapon = new Weapon(
                config.getInt("id"),
                config.getString("name"),
                config.getDouble("damage"),
                config.getDouble("radius"));
        weapon.setImage("item/" + config.getInt("id") + "/image.png");

        return weapon;
    }
}
