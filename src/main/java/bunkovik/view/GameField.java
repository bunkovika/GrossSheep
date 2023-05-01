package bunkovik.view;


import bunkovik.config.Config;
import bunkovik.config.SpriteManager;
import bunkovik.config.tile.TileMap;
import bunkovik.controller.GameFieldController;
import bunkovik.model.GameModel;
import bunkovik.model.entity.Sheep;
import bunkovik.view.component.game.HPBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.*;

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
        Sheep sheep = gameModel.getSheep();
        canvasBox = new VBox();
//        ---------canvas and view of tileMap--------

        canvasBox.setAlignment(Pos.CENTER);
        canvasBox.setPadding(new Insets(15,0,50,12));
        Canvas canvas = new Canvas(tileMap.getMapWidth(), tileMap.getMapHeight());
        gc = canvas.getGraphicsContext2D();
        canvasBox.getChildren().add(canvas);

//---------------------HP BOX--------------------------------
//---------------Stack PAne for combine HP BOX and canvasBox ------------
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(canvasBox);
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

    @Override
    public void render() {
        gc.clearRect(0, 0, tileMap.getMapWidth(), tileMap.getMapHeight());
        tileMap.render(gc);
        spriteManager.render(gc);
    }
}