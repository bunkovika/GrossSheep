package bunkovik.core;

import bunkovik.controller.*;
import bunkovik.model.GameModel;
import bunkovik.model.entity.Sheep;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.logging.Logger;


public class StateManager {
    // Logger
    private static Logger log = Logger.getLogger(Sheep.class.getName());

    private static HashMap<String, Controller> states = new HashMap<>();
    private static Controller currentController;
    private static Stage stage;
    private static GameLoop gameLoop;
    private static boolean isStarted;


    public static void init(Stage stage) {
        StateManager.stage = stage;

        // Init States
        states.put("MENU", new MainMenuController());
        states.put("GAME_MENU", new GameMenuController());
        states.put("GAME_RULES", new GameRulesController());
        states.put("GAME", new GameFieldController());
        states.put("INVENTORY", new InventoryController());
        states.put("GAME_OVER", new GameOverController());
        states.put("GAME_WIN", new GameWinController());

        // Init Game Loop
        StateManager.gameLoop = new GameLoop();

        // Initial State
        goToMainMenu();

        // Open and Start game
        stage.show();
        startLoop();
    }
    public static void startGame(boolean fromSave) {
        currentController = states.get("GAME");

        // Reset All Controllers and Views (in case we start a new game after the game has already been played)
        states.forEach((key, value) -> {
            value.reset();
        });

        // Init Game Model
        GameModel gameModel = GameModel.getInstance();
        gameModel.setIdOfCurrentLocation();
        gameModel.init(fromSave);

        // Init Controller
        currentController.init();

        // Setting Scene
        stage.setScene(currentController.getView().getScene());
        isStarted = true;
    }
    public static void continueGame() {
        currentController = states.get("GAME");
        currentController.init();
        stage.setScene(currentController.getView().getScene());

        log.info("Continue game.");
        isStarted = true;
    }

    public static void goToMainMenu() {
        currentController = states.get("MENU");
        currentController.init();
        stage.setScene(currentController.getView().getScene());

        log.info("Go to the main menu.");
        isStarted = false;
    }
    public static void goToGameMenu() {
        currentController = states.get("GAME_MENU");
        currentController.init();
        stage.setScene(currentController.getView().getScene());

        log.info("Go to the game menu.");
        isStarted = true;
    }
    public static void goToGameRules() {
        currentController = states.get("GAME_RULES");
        currentController.init();
        stage.setScene(currentController.getView().getScene());

        log.info("Go to the game rules.");
    }

    public static void goToInventory() {
        currentController = states.get("INVENTORY");
        currentController.init();
        stage.setScene(currentController.getView().getScene());

        log.info("Go to the inventory.");
//        isStarted = true;
    }

    public static void gameOver() {
        currentController = states.get("GAME_OVER");
        currentController.init();
        stage.setScene(currentController.getView().getScene());

        log.info("Game Over.");
    }
    public static void gameWin() {
        currentController = states.get("GAME_WIN");
        currentController.init();
        stage.setScene(currentController.getView().getScene());

        log.info("Game Win.");
    }

    public static boolean isIsStarted() {
        return isStarted;
    }

    public static void resetScene() {
        stage.setScene(currentController.getView().getScene());
    }

    public static void startLoop() {
        gameLoop.start();
    }

    public static void stopLoop() {
        gameLoop.stop();
    }

    public static Controller getCurrentState() {
        return currentController;
    }


}

