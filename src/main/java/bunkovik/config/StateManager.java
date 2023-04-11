package bunkovik.config;

import bunkovik.controller.*;
import javafx.stage.Stage;
import java.util.HashMap;
public class StateManager {
    private static HashMap<String, Controller> states = new HashMap<>();
    private static Controller currentController;
    private static Stage stage;

    public static void init(Stage stage) {
        StateManager.stage = stage;
        WindowConfig.setFullScreenSize();


        states.put("MAIN_MENU", new MainMenuController());
        states.put("GAME_MENU", new GameMenuController());
        states.put("GAME_FIELD", new GameFieldController());
        states.put("INVENTORY", new InventoryController());
        states.put("GAME_OVER", new GameOverController());
        toMainMenu();

        // Open and Start game
        stage.show();

    }

    public static void startGame(boolean fromSave) {
        currentController = states.get("GAME_FIELD");
        // Reset All Controllers and Views (in case we start a new game after the game has already been played)
        states.forEach((key, value) -> {
            value.reset();
        });

        // Init Controller
        currentController.init();

        // Setting Scene
        stage.setScene(currentController.getView().getScene());
    }
    public static void toGameField() {
        currentController = states.get("GAME_FIELD");
        currentController.init();
        stage.setScene(currentController.getView().getScene());
    }
    public static void toContinueGame() {
        currentController = states.get("GAME_FIELD");
        currentController.init();
        stage.setScene(currentController.getView().getScene());
    }
    public static void toMainMenu() {
        currentController = states.get("MAIN_MENU");
        currentController.init();
        stage.setScene(currentController.getView().getScene());
    }
    public static void toGameMenu() {
        currentController = states.get("GAME_MENU");
        currentController.init();
        stage.setScene(currentController.getView().getScene());
    }
    public static void toInventory() {
        currentController = states.get("INVENTORY");
        currentController.init();
        stage.setScene(currentController.getView().getScene());
    }
    public static void toGameOver() {
        currentController = states.get("GAME_OVER");
        currentController.init();
        stage.setScene(currentController.getView().getScene());
    }
    public static void toGameRules() {
        currentController = states.get("GAME_RULES");
        currentController.init();
        stage.setScene(currentController.getView().getScene());
    }
    public static Controller getCurrentState() {
        return currentController;
    }
}

