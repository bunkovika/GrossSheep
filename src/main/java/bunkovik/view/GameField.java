package bunkovik.view;

import bunkovik.controller.GameFieldController;
import bunkovik.core.Config;
import bunkovik.core.sprite.SpriteManager;
import bunkovik.core.tile.TileMap;
import bunkovik.model.GameModel;
import bunkovik.model.entity.Sheep;
import bunkovik.view.component.game.HPBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class GameField extends View {
    private TileMap tileMap;
    private VBox canvasBox;
    private GraphicsContext gc;
    private SpriteManager spriteManager;
    public GameField(GameFieldController controller) {
        this.controller = controller;
    }

    public void init() {
//        --------------init------------
        GameModel gameModel = GameModel.getInstance();
        tileMap = gameModel.getTileMap();
        spriteManager = gameModel.getSpriteManager();
        Sheep sheep = gameModel.getPlayer();
        canvasBox = new VBox();
//        ---------canvas and view of tileMap--------

        canvasBox.setAlignment(Pos.CENTER);
        canvasBox.setPadding(new Insets(15,0,50,12));
        Canvas canvas = new Canvas(tileMap.getMapWidth(), tileMap.getMapHeight());
        gc = canvas.getGraphicsContext2D();
        canvasBox.getChildren().add(canvas);

//---------------------HP BOX--------------------------------
        BorderPane borderPane = new BorderPane();
        HPBox health = new HPBox(sheep.getHP().getHealth());
        sheep.getHP().addObserver(health);
        borderPane.getChildren().add(health.getText());
        borderPane.setTop(canvasBox);
//---------------Stack PAne for combine HP BOX and canvasBox ------------
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(canvasBox, borderPane);
//        -------HBox-------
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.setStyle("-fx-background-color: #553A5F; ");
//        hbox.setPadding(new Insets(100));
        hbox.getChildren().add(stackPane);

        // Scene Creation
        scene = new Scene(hbox, Config.getWindowWidth(), Config.getWindowHeight());

        // Attaching Event Listeners
        scene.setOnKeyPressed(((GameFieldController) controller)::keyPressedHandler);
        scene.setOnKeyReleased(((GameFieldController) controller)::keyReleasedHandler);
    }
    public VBox getCanvasRoot() {
        return canvasBox;
    }

    @Override
    public void render() {
        gc.clearRect(0, 0, tileMap.getMapWidth(), tileMap.getMapHeight());
        tileMap.render(gc);
        spriteManager.render(gc);
    }
//

}