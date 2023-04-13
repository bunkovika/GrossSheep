package bunkovik.view;

import bunkovik.config.WindowConfig;
import bunkovik.controller.InventoryController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Inventory extends View {
    public Inventory(InventoryController controller) {
        this.controller = controller;
    }

    @Override
    public void init() {

        VBox imagePlace = new VBox();
        VBox vboxUp = new VBox();
        VBox vboxDown = new VBox();
        VBox vbox = new VBox(10);
        HBox hbox = new HBox();

        Label label = new Label("E - equip                   C - consume");
        label.setTextFill(Color.WHITE);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 25));
//        VBOX for image
        imagePlace.setAlignment(Pos.CENTER_LEFT);
        imagePlace.setPadding(new Insets(25));
        Rectangle rectangle = new Rectangle(270, 270, Color.web("#956E8D"));
        rectangle.setArcWidth(10);
        rectangle.setArcHeight(10);
        imagePlace.getChildren().add(rectangle);

//        VBOX
        vbox.setAlignment(Pos.CENTER);
        vbox.setStyle("-fx-background-color: #956E8D; "+
                "-fx-border-radius: 5; "+
                "-fx-background-radius: 5; " );
        vbox.setPrefWidth(850);
        vbox.setPrefHeight(550);
        vbox.setPadding(new Insets(25));
        vbox.setSpacing(30);
//        VBOX Up
        vboxUp.setAlignment(Pos.TOP_CENTER);
        vboxUp.setStyle("-fx-background-color: #ECD2ED;"+
                        "-fx-border-radius: 5; "+
                        "-fx-background-radius: 5; " );
        vboxUp.setPrefWidth(800);
        vboxUp.setPrefHeight(330);
        vboxUp.getChildren().add(imagePlace);
//        VBOX Down
        vboxDown.setAlignment(Pos.BOTTOM_CENTER);
        vboxDown.setStyle("-fx-background-color: #ECD2ED;"+
                "-fx-border-radius: 5; "+
                "-fx-background-radius: 5; " );
        vboxDown.setPrefWidth(800);
        vboxDown.setPrefHeight(130);

//        8 squares
        // Create an HBox to hold the rectangles
        HBox squaresBox = new HBox(10); // 10 is the spacing between squares
        squaresBox.setAlignment(Pos.CENTER);
        squaresBox.setPadding(new Insets(15));

// Create 8 rectangles and add them to the HBox
        for (int i = 0; i < 8; i++) {
            Rectangle square = new Rectangle(90, 90, Color.web("#553A5F"));
            square.setArcHeight(10);
            square.setArcWidth(10);
            squaresBox.getChildren().add(square);
        }

// Add the HBox to the vboxDown
        vboxDown.getChildren().add(squaresBox);


//      VBOX
        vbox.getChildren().add(vboxUp);
        vbox.getChildren().add(vboxDown);
        vbox.getChildren().add(label);

//        HBOX
        hbox.setAlignment(Pos.CENTER);
        hbox.setStyle("-fx-background-color: #553A5F; ");
        hbox.setPadding(new Insets(100));
        hbox.getChildren().add(vbox);

        scene = new Scene(hbox, WindowConfig.getWindowWidth(), WindowConfig.getWindowHeight());
    }
    @Override
    public void render() {

    }
}

