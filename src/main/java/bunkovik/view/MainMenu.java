package bunkovik.view;

import bunkovik.controller.MainMenuController;
import bunkovik.core.Config;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class MainMenu extends View {
    public MainMenu(MainMenuController controller) {
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
        vBox.setSpacing(30);

        ImageView sheepImage = new ImageView(new Image("icon/sheep_Icon.png"));
        sheepImage.setFitHeight(480);
        sheepImage.setFitWidth(470);
        sheepImage.setTranslateX(-80);

        hBox.getChildren().add(sheepImage);

//        Start Game
        Button startGame = new Button();
        startGame.setText("Start Game");
        buttons.add(startGame);

//        Load Game
        Button loadGame = new Button();
        loadGame.setText("Load Game");
        buttons.add(loadGame);

//        Game Rules
        Button gameRules = new Button();
        buttons.add(gameRules);
        gameRules.setText("Game Rules");

        for (Button button : buttons) {
            button.setPrefHeight(80);
            button.setPrefWidth(250);
            button.setStyle("" +
                    "-fx-background-color: #956E8D; " +
                    "-fx-background-radius: 20; " +
                    "-fx-border-radius: 30; " +
                    "-fx-text-fill: #FFFFFF; " +
                    "-fx-font-size: 30px; " +
                    "-fx-font-weight: 600; " +
                    "-fx-cursor: hand;"
            );
            vBox.getChildren().add(button);

        }
        // Add the VBox to the right of the HBox
        hBox.getChildren().add(vBox);
        startGame.setOnMouseClicked(((MainMenuController) controller)::gameStartButtonClickHandler);
        loadGame.setOnMouseClicked(((MainMenuController) controller)::gameLoadButtonClickHandler);
        gameRules.setOnMouseClicked(((MainMenuController) controller)::gameRulesButtonClickHandler);
        scene = new Scene(hBox,Config.getWindowWidth(), Config.getWindowHeight());
    }


    @Override
    public Scene getScene() {
        return super.getScene();
    }
    @Override
    public void render() {}

}
