package bunkovik.core.factory;

import bunkovik.model.entity.Wolf;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WolfFactory {

    public static Wolf getWolf(int id) {
        try {
            File file = new File("src/main/resources/wolf/" + id + "/config.json");
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            JSONObject config = new JSONObject(content);

            // Characteristics
            String name = config.getString("name");
            double health = config.getDouble("health");
            double damage = config.getDouble("damage");
            double damageRadius = config.getDouble("damageRadius");
            double viewingRadius = config.getDouble("viewingRadius");

            double attackSpeed = config.getDouble("attackSpeed");
            String[] images = new String[7];

            for (int i = 0; i < 7; i++) {
                images[i] = "wolf/" + id + "/fixedPosition/image" + i + ".png";
            }
            // Creating Wolf
            Wolf wolf = new Wolf(name, health, damage, damageRadius, viewingRadius, attackSpeed, images);

            return wolf;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
