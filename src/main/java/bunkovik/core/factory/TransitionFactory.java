package bunkovik.core.factory;

import bunkovik.model.entity.Transition;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TransitionFactory {
    public static Transition getTransition(int id) {
        try {

            File file = new File("src/main/resources/transition/" + id + "/config.json");
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            JSONObject config = new JSONObject(content);
            // Creating Transition to new level
            Transition transition = new Transition(
                    id,
                    config.getInt("locationId"),
                    config.getInt("playerX"),
                    config.getInt("playerY")
            );
            transition.setImage("transition/" + id + "/image.png");

            return transition;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
