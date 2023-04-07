package dev.bunkovik.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class GameField extends View {
    @Override
    public void init() {
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.setStyle("-fx-background-color: #956E8D; " +
                "-fx-border-radius: 5; " +
                "-fx-background-radius: 5; ");
        vbox.setPrefWidth(1000);
        vbox.setPrefHeight(700);
        vbox.setPadding(new Insets(0));

        GridPane tileMap = new GridPane();
        tileMap.setAlignment(Pos.CENTER);
        tileMap.setHgap(1.1);
        tileMap.setVgap(1.1);

        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 10; j++) {
                Rectangle rect = new Rectangle(75, 65);
                rect.setFill(Color.web("#553A5F"));
                tileMap.add(rect, i, j);
            }
        }
        Image image = new Image("Hay.png");
        Rectangle rect = new Rectangle(75, 65);
        rect.setFill(new ImagePattern(image));
        tileMap.add(rect, 1, 0);
        Rectangle rect1 = new Rectangle(75, 65);
        rect1.setFill(new ImagePattern(image));
        tileMap.add(rect1, 3, 0);
        vbox.getChildren().add(tileMap);

        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.setStyle("-fx-background-color: #553A5F; ");
        hbox.setPadding(new Insets(100));
        hbox.getChildren().add(vbox);

        scene = new Scene(hbox, 1000, 700);
    }
}

