package dev.bunkovik;

import dev.bunkovik.view.GameOver;
import dev.bunkovik.view.MainMenuView;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
//        MainMenuView mainMenuView = new MainMenuView();
//        mainMenuView.init();
        GameOver GameOverView = new GameOver();
        GameOverView.init();
        stage.setTitle("Gross Sheep");
        stage.setScene(GameOverView.getScene());
        stage.show();
    }
}
