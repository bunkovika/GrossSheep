package bunkovik.view;

import bunkovik.config.WindowConfig;
import bunkovik.controller.GameFieldController;
import bunkovik.model.entity.Sheep;
import bunkovik.model.entity.Sprite;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameField extends View {
    public GameField(GameFieldController controller) {
        this.controller = controller;
    }

    @Override
    public void init() {
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.setStyle("-fx-background-color: #493745; " +
                "-fx-border-radius: 5; " +
                "-fx-background-radius: 5; ");
        vbox.setPrefWidth(1000);
        vbox.setPrefHeight(700);
        vbox.setPadding(new Insets(0));

        GridPane tileMap = new GridPane();
        tileMap.setAlignment(Pos.CENTER);
        tileMap.setHgap(1.1);
        tileMap.setVgap(1.1);

        File file = new File("tileMap.txt");
        ArrayList<String> lines = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lines.add(line);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int numRows = lines.size();
        int numCols = lines.get(0).length();

        for (int i = 0; i < numCols; i++) {
            for (int j = 0; j < numRows; j++) {
                char tile = lines.get(j).charAt(i); // get the character at (i, j) position in the file
                Rectangle rect = new Rectangle(65, 55);
                if (tile == 'A') {
                    rect.setFill(Color.web("#CAA9D6"));
                } else if (tile == 'C') {
                    rect.setFill(Color.web("#956E8D"));
                }
                tileMap.add(rect, i, j);
            }
        }
        Sprite sheep = new Sheep("sheep");
        sheep.setImage("sheep/sheep_bottom.png");
        sheep.setPosition(3,0);
        vbox.getChildren().add(tileMap);

        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.setStyle("-fx-background-color: #553A5F; ");
        hbox.setPadding(new Insets(100));
        hbox.getChildren().add(vbox);

        scene = new Scene(hbox, WindowConfig.getWindowWidth(), WindowConfig.getWindowHeight());
        // Adding Event Listeners
        scene.setOnKeyPressed(((GameFieldController) controller)::keyPressedHandler);
        scene.setOnKeyReleased(((GameFieldController) controller)::keyReleasedHandler);
    }

}