package dev.bunkovik;

import dev.bunkovik.view.GameMenu;
import dev.bunkovik.view.GameOver;
import dev.bunkovik.view.InventoryView;
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
        MainMenuView mainMenuView = new MainMenuView();
        mainMenuView.init();

        GameMenu GameMenu = new GameMenu();
        GameMenu.init();

        GameOver GameOverView = new GameOver();
        GameOverView.init();

        InventoryView inv = new InventoryView();
        inv.init();

        stage.setTitle("Gross Sheep");
//        stage.setScene(mainMenuView.getScene());
//        stage.setScene(GameMenu.getScene());
//        stage.setScene(GameOverView.getScene());
        stage.setScene(inv.getScene());

        stage.show();
    }
}
