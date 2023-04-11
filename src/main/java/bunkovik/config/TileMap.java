package bunkovik.config;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;

public class TileMap extends Node {
    private Tile[][] tiles;

    private int mapWidth;
    private int mapHeight;

    public TileMap(int width, int height, int tileWidth, int tileHeight) {
        this.tiles = new Tile[width][height];
        this.mapWidth = getWidth() * tileWidth;
        this.mapHeight = getHeight() * tileHeight;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                this.tiles[j][i] = new Tile("Hay.png", true);
                this.tiles[j][i].setTileWidth(tileWidth);
                this.tiles[j][i].setTileHeight(tileHeight);
            }
        }
    }

    public int getTileWidth() {
        return tiles[0][0].getTileWidth();
    }

    public int getTileHeight() {
        return tiles[0][0].getTileHeight();
    }

    public int getWidth() {
        return tiles.length;
    }

    public int getHeight() {
        return tiles[0].length;
    }

    public Tile getTile(int x, int y) {
        if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight()) {
            return null;
        } else {
            return tiles[x][y];
        }
    }

    public void setTile(int x, int y, Tile tile) {
        tiles[x][y] = tile;
    }

    public void setAllTiles(GraphicsContext gc) {
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                Tile tile = getTile(j, i);
                if (tile != null) {
                    tile.setTile(gc, j*tile.getTileWidth(), i*tile.getTileHeight());
                }
            }
        }
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    @Override
    protected NGNode impl_createPeer() {
        return null;
    }

    @Override
    public BaseBounds impl_computeGeomBounds(BaseBounds bounds, BaseTransform tx) {
        return null;
    }

    @Override
    protected boolean impl_computeContains(double localX, double localY) {
        return false;
    }

    @Override
    public Object impl_processMXNode(MXNodeAlgorithm alg, MXNodeAlgorithmContext ctx) {
        return null;
    }
}
