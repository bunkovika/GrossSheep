package bunkovik;

import bunkovik.core.Config;
import bunkovik.core.StateManager;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Game extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        // loading main config
        Config.init("config.json");

        // setting up stage
        stage.setTitle(Config.getWindowName());
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.getIcons().add(new Image("icon/sheep_Icon.png"));

        // init state manager
        StateManager.init(stage);
    }
}
