package bunkovik.view;

import bunkovik.controller.GameMenuController;
import bunkovik.core.Config;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class GameMenu extends View {
    public GameMenu(GameMenuController controller) {
        this.controller = controller;
    }

    public void init() {
        // JavaFX init
        HBox hBox = new HBox();
        VBox vBox = new VBox();
        ArrayList<Button> buttons = new ArrayList<>();

        hBox.setStyle("-fx-background-color: #553A5F; ");
        hBox.setAlignment(Pos.CENTER);
        // Tile Pane Init
        vBox.setStyle("-fx-background-color: #553A5F; ");
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(40);

//        Continue Game
        Button continueGame = new Button();
        continueGame.setText("Continue Game");
        buttons.add(continueGame);

//        Save Game
        Button saveGame = new Button();
        saveGame.setText("Save Game");
        buttons.add(saveGame);

        //Main Menu
        Button mainMenu = new Button();
        mainMenu.setText("Main Menu");
        buttons.add(mainMenu);

//        Game Rules
        Button gameRules = new Button();
        gameRules.setText("Game Rules");
        buttons.add(gameRules);

        for (Button button : buttons) {
            button.setPrefHeight(80);
            button.setPrefWidth(250);
            button.setStyle("-fx-background-color: #956E8D; " +
                    "-fx-background-radius: 20; " +
                    "-fx-border-radius: 30; " +
                    "-fx-text-fill: #FFFFFF; " +
                    "-fx-font-size: 30px; " +
                    "-fx-font-weight: 600; " +
                    "-fx-cursor: hand;" );
            vBox.getChildren().add(button);

        }
        continueGame.setOnMouseClicked(((GameMenuController) controller)::gameContinueButtonClickHandler);
        saveGame.setOnMouseClicked(((GameMenuController) controller)::gameSaveButtonClickHandler);
        mainMenu.setOnMouseClicked(((GameMenuController) controller)::toMainMenuButtonClickHandler);
//        gameRules.setOnMouseClicked(((GameMenuController) controller)::toGameRulesButtonClickHandler);

        hBox.getChildren().add(vBox);

        scene = new Scene(hBox, Config.getWindowWidth(), Config.getWindowHeight());
    }

    @Override
    public void render() {

    }
    @Override
    public Scene getScene() {
        return super.getScene();
    }
}

