package dev.bunkovik.other;

import javafx.scene.canvas.GraphicsContext;

public class TileMap {
    private Tile[][] tiles;
    private int tileWidth;
    private int tileHeight;
    private int mapWidth;
    private int mapHeight;

    public TileMap(int width, int height, int tileWidth,int tileHeight) {
        this.tiles = new Tile[width][height];
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.mapWidth = getWidth() * tileWidth;
        this.mapHeight = getHeight() * tileHeight;
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
                    tile.setTile(gc, convertTileToPixel(j), convertTileToPixel(i));
                }
            }
        }
    }

    public  int convertTileToPixel(int coord) {
        return coord * tileWidth;
    }

    public  int convertPixelToTile(double coord) {
        return (int) (coord / tileWidth);
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }
}
