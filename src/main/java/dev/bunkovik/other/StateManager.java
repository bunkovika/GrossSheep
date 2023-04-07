package dev.bunkovik.other;

import dev.bunkovik.controller.Controller;
import dev.bunkovik.controller.GameOverController;
import dev.bunkovik.controller.MainMenuController;
import javafx.stage.Stage;
import java.util.HashMap;
public class StateManager {
        private static HashMap<String, Controller> states = new HashMap<>();
        private static Controller currentController;
        private static Stage stage;

        public static void init(Stage stage) {
            StateManager.stage = stage;

            // Init States
            states.put("MENU", new MainMenuController());
            states.put("GAME_OVER", new GameOverController());

            gameOver();

            // Open and Start game
            stage.show();

        }

        public static void startGame(boolean fromSave) {
            currentController = states.get("GAME");

            // Reset All Controllers and Views (in case we start a new game after the game has already been played)
            states.forEach((key, value) -> {
                value.reset();
            });

            // Init Controller
            currentController.init();

            // Setting Scene
            stage.setScene(currentController.getView().getScene());
        }
        public static void continueGame() {
            currentController = states.get("GAME");
            currentController.init();
            stage.setScene(currentController.getView().getScene());

        }

        public static void goToMainMenu() {
            currentController = states.get("MENU");
            currentController.init();
            stage.setScene(currentController.getView().getScene());
        }

        public static void goToGameMenu() {
            currentController = states.get("GAME_MENU");
            currentController.init();
            stage.setScene(currentController.getView().getScene());

        }

        public static void goToInventory() {
            currentController = states.get("INVENTORY");
            currentController.init();
            stage.setScene(currentController.getView().getScene());

        }

        public static void gameOver() {
            currentController = states.get("GAME_OVER");
            currentController.init();
            stage.setScene(currentController.getView().getScene());

        }
        public static Controller getCurrentState() {
            return currentController;
        }
    }

