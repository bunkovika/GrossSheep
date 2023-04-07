package dev.bunkovik;

import dev.bunkovik.other.StateManager;
import dev.bunkovik.view.*;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        StateManager.init(stage);
//        MainMenuView mainMenuView = new MainMenuView();
//        mainMenuView.init();
//
//        GameMenu GameMenu = new GameMenu();
//        GameMenu.init();
//
//        GameOverView GameOverView = new GameOverView();
//        GameOverView.init();
//
//        InventoryView inv = new InventoryView();
//        inv.init();
//
//        GameField field = new GameField();
//        field.init();
//
//        stage.setTitle("Gross Sheep");
////        stage.setScene(mainMenuView.getScene());
////        stage.setScene(GameMenu.getScene());
////        stage.setScene(GameOverView.getScene());
////        stage.setScene(inv.getScene());
//        stage.setScene(field.getScene());
////
//        stage.show();
    }
}
