package bunkovik.view;

import bunkovik.controller.GameWinController;
import bunkovik.core.Config;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import static javafx.scene.text.TextAlignment.CENTER;

public class GameWin extends View {
    public GameWin(GameWinController controller) {
        this.controller = controller;
    }

    @Override
    public void init() {
//        INIT
        Text header = new Text("CONGRATULATIONS! YOU WIN!");
        Button button = new Button("MAIN MENU");
        VBox vbox = new VBox(10);
        HBox hbox = new HBox();
//        HEADER
        header.setTextAlignment(CENTER);
        header.setFill(Color.WHITE);
        header.setStyle("-fx-font-size: 64px; " +
                "-fx-font-weight: 700; " );

//

//        BUTTON
        button.setPrefHeight(80);
        button.setPrefWidth(250);
        button.setStyle("" +
                "-fx-background-color: #553A5F; " +
                "-fx-background-radius: 20; " +
                "-fx-border-radius: 30; " +
                "-fx-text-fill: #FFFFFF; " +
                "-fx-font-size: 30px; " +
                "-fx-font-weight: 700; " +
                "-fx-cursor: hand;");

//        VBOX
        vbox.setAlignment(Pos.CENTER);
        vbox.setStyle("-fx-background-color: #956E8D; "+
                "-fx-border-radius: 30; "+
                "-fx-background-radius: 10; " );
        vbox.setPrefWidth(850);
        vbox.setPadding(new Insets(100));
        vbox.setSpacing(60);
        vbox.getChildren().addAll(header,button);

//        HBOX
        hbox.setAlignment(Pos.CENTER);
        hbox.setStyle("-fx-background-color: #553A5F; ");
        hbox.setPadding(new Insets(100));
        hbox.getChildren().add(vbox);
        // Attaching Event Listeners
        button.setOnMouseClicked(((GameWinController) controller)::goToMainMenuButtonClickHandler);
        scene = new Scene(hbox, Config.getWindowWidth(), Config.getWindowHeight());

    }
    @Override
    public void render() {

    }
    @Override
    public Scene getScene() {
        return super.getScene();
    }

}
