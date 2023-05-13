package bunkovik.controller;

import bunkovik.core.StateManager;
import bunkovik.core.sprite.SpriteManager;
import bunkovik.core.tile.TileMap;
import bunkovik.model.GameModel;
import bunkovik.model.entity.Item.Item;
import bunkovik.model.entity.Sheep;
import bunkovik.model.entity.Transition;
import bunkovik.model.entity.Wolf;
import bunkovik.view.GameField;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class GameFieldController extends Controller {
    // Inputs
    private ArrayList<String> input = new ArrayList<>();

    // Links
    private Sheep sheep;
    private TileMap tileMap;
    private ArrayList<Wolf> wolves;
    private ArrayList<Item> items;
    private ArrayList<Transition> transitions;
    private SpriteManager spriteManager;
    private VBox canvasRoot;

    public void init() {
        if (isOpened) return;

        // Init View
        view = new GameField(this);
        view.init();

        // Links to game entities
        GameModel gameModel = GameModel.getInstance();
        sheep = gameModel.getPlayer();
        wolves = gameModel.getWolves();
        items = gameModel.getItems();
        transitions = gameModel.getPortals();
        tileMap = gameModel.getTileMap();
        canvasRoot = ((GameField) view).getCanvasRoot();
        spriteManager = gameModel.getSpriteManager();

        // Setting Up State
        isOpened = true;
    }

// Event Handlers
    public void keyPressedHandler(KeyEvent e) {
        String code = e.getCode().toString();

        if (!input.contains(code)) {
            input.add(code);
        }

        // Sheep attack
        if (code.equals("SPACE")) {
            playerAttack();
        }

        // Game Menu
        if (code.equals("ESCAPE")) {
            input = new ArrayList<>();
            StateManager.goToGameMenu();
        }

        // Go to Inventory
        if (code.equals("I")) {
            input = new ArrayList<>();
            StateManager.goToInventory();
        }
    }

    public void keyReleasedHandler(KeyEvent e) {
        String code = e.getCode().toString();
        input.remove(code);
    }

    public void playerAttack() {
        ArrayList<Wolf> deadWolves = new ArrayList<>();

        for (Wolf wolf : wolves) {
            if (sheep.intersectsAttackBox(wolf)) {
                sheep.attack(wolf);

                if (wolf.isDead()) {
                    deadWolves.add(wolf);
                    break;
                }
            }
        }

        // Remove dead wolves
        for (Wolf deadWolf : deadWolves) {
            wolves.remove(deadWolf);
            spriteManager.removeSprite(deadWolf);
        }
    }

    private void updatePlayerPosition(double delta) {
        Rectangle2D moveBox = sheep.getMoveBox();
        double path = sheep.getSpeed() * delta; // real distance traveled in time without reference to frame rate
        double pathBound = path + 1;

        if (input.contains("A") && sheep.getX() - pathBound > 0) {
            int tileMinX = TileMap.convertPixelToTile(moveBox.getMinX() - pathBound);
            int tileMinY = TileMap.convertPixelToTile(moveBox.getMinY());
            int tileMaxY = TileMap.convertPixelToTile(moveBox.getMaxY());

            if (tileMap.getTile(tileMinX, tileMinY).isPassable() &&
                    tileMap.getTile(tileMinX, tileMaxY).isPassable()) {
                sheep.moveLeft(path);
            }
        }

        if (input.contains("D") && sheep.getX() + sheep.getWidth() + pathBound < tileMap.getMapWidth()) {
            int tileMinY = TileMap.convertPixelToTile(moveBox.getMinY());
            int tileMaxY = TileMap.convertPixelToTile(moveBox.getMaxY());
            int tileMaxX = TileMap.convertPixelToTile(moveBox.getMaxX() + pathBound);

            if (tileMap.getTile(tileMaxX, tileMinY).isPassable() &&
                    tileMap.getTile(tileMaxX, tileMaxY).isPassable()) {
                sheep.moveRight(path);
            }
        }

        if (input.contains("W") && sheep.getY() - pathBound > 0) {
            int tileMinX = TileMap.convertPixelToTile(moveBox.getMinX());
            int tileMinY = TileMap.convertPixelToTile(moveBox.getMinY() - pathBound);
            int tileMaxX = TileMap.convertPixelToTile(moveBox.getMaxX());

            if (tileMap.getTile(tileMinX, tileMinY).isPassable() &&
                    tileMap.getTile(tileMaxX, tileMinY).isPassable()) {
                sheep.moveUp(path);
            }
        }

        if (input.contains("S") && sheep.getY() + sheep.getHeight() + pathBound < tileMap.getMapHeight()) {
            int tileMinX = TileMap.convertPixelToTile(moveBox.getMinX());
            int tileMaxY = TileMap.convertPixelToTile(moveBox.getMaxY() + pathBound);
            int tileMaxX = TileMap.convertPixelToTile(moveBox.getMaxX());

            if (tileMap.getTile(tileMinX, tileMaxY).isPassable() &&
                    tileMap.getTile(tileMaxX, tileMaxY).isPassable()) {
                sheep.moveDown(path);
            }
        }
    }

    private void checkIntersections() {
        // Items
        ArrayList<Item> takenItems = new ArrayList<>();

        for (Item item : items) {
            if (sheep.intersectsMoveBox(item)) {
                if (item.take(sheep)) {
                    takenItems.add(item);
                }
            }
        }

        // Remove Taken Items from game world
        for (Item item : takenItems) {
            spriteManager.removeSprite(item);
            items.remove(item);
        }

        // Monsters View
        for (Wolf wolf : wolves) {
            if (wolf.intersectsRadiusViewBox(sheep)) {
                wolf.setAim(sheep);
            }
        }

        // Transition
        for (Transition transition : transitions) {
            if (sheep.intersectsMoveBox(transition)) {
//
                if (transition.getId() == transitions.size()+1) {
                    StateManager.gameWin();

                    return;
                }
//                sheep.getInventory().resetInventory();
                transition.activate();

                for (Wolf wolf : wolves) {
                    wolf.offCombat();
                }

                // Reload Controller
                isOpened = false;
                init();

                // Reset Current Game Scene
                StateManager.resetScene();
                resetInput();

                return;
            }
        }
    }

    private void resetInput() {
        input = new ArrayList<>();
    }

    @Override
    public void tick(double delta) {
        // Update
        updatePlayerPosition(delta);

        // Check intersections
        checkIntersections();

        // Sprite Update
        spriteManager.update(delta);

        // Render
        view.render();
    }
}
