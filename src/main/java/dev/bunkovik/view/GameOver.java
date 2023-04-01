package dev.bunkovik.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GameOver extends AView {

    @Override
    public void init() {
        HBox hBox = new HBox();
        VBox vBox = new VBox();
        hBox.setStyle("-fx-background-color: #553A5F; ");

        vBox.setStyle("-fx-background-color: #956E8D; ");
        vBox.setPrefHeight(520);
        vBox.setPrefWidth(820);
//        vBox.setPadding(new Insets(30, 1, 30, 1));

        vBox.setSpacing(30);

        hBox.getChildren().add(vBox);
        hBox.setAlignment(Pos.CENTER);

        scene = new Scene(hBox, 1000, 700);
    }
    @Override
    public Scene getScene() {
        return super.getScene();
    }
}
