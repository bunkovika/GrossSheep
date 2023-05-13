package bunkovik.view;


import bunkovik.controller.GameRulesController;
import bunkovik.controller.InventoryController;
import bunkovik.core.Config;
import bunkovik.core.StateManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

public class GameRules extends View{
    public GameRules(GameRulesController controller) {
        this.controller = controller;
    }
    @Override
    public void init() {
        HBox hbox =  new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.setStyle("-fx-background-color: #553A5F; ");
        hbox.setPadding(new Insets(100));

        ImageView gameRules = new ImageView(new Image("gameRules/gameRules.png"));
        hbox.getChildren().add(gameRules);
        scene = new Scene(hbox, Config.getWindowWidth(), Config.getWindowHeight());
        scene.setOnKeyPressed(((GameRulesController) controller)::keyPressedHandler);
    }


    @Override
    public void render() {

    }
}
