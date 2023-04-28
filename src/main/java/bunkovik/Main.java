package bunkovik;

import bunkovik.config.Config;
import bunkovik.config.StateManager;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

//public class Main  {
//
//    public static void main(String[] args) {
//        File file = new File("tileMap");
//        System.out.println(file);
//    }
//}
public class Main extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        // loading main config
        Config.init("config.json");

        // setting up stage
        stage.setTitle(Config.getWindowName());
        stage.setResizable(false);
        stage.centerOnScreen();
        StateManager.init(stage);
        stage.getIcons().add(new Image("icon/sheep_icon.png"));
    }
}
