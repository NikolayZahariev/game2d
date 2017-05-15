package tilemaps;

import main.GamePanel;

import java.awt.*;

public class TileMap {
    public TileLoader tileLoading;
    public MapLoader mapLoading;
    private double tween;
    private int tileSize;
    private int rowOffset;
    private int colOffset;
    private int numRowsToDraw;
    private int numColsToDraw;

    public TileMap(int tileSize) {
        this.tileSize = tileSize;
        tileLoading = new TileLoader(tileSize);
        mapLoading = new MapLoader(tileSize);
        numRowsToDraw = GamePanel.HEIGHT / tileSize + 2;
        numColsToDraw = GamePanel.WIDTH / tileSize + 2;
        tween = 0.07;
    }

    public int getTileSize() {
        return tileSize;
    }

    public int getType(int row, int col) {
        int rc = mapLoading.map[row][col];
        int r = rc / tileLoading.numTilesAcross;
        int c = rc % tileLoading.numTilesAcross;
        return tileLoading.tiles[r][c].getType();
    }

    public void setPosition(double x, double y) {
        mapLoading.x += (x - mapLoading.x) * tween;
        mapLoading.y += (y - mapLoading.y) * tween;
        mapLoading.fixBounds();
        colOffset = (int) -mapLoading.x / tileSize;
        rowOffset = (int) -mapLoading.y / tileSize;
    }

    public void draw(Graphics g) {
        for (int row = rowOffset; row < rowOffset + numRowsToDraw; row++) {
            if (row >= mapLoading.numRows) break;
            for (int col = colOffset; col < colOffset + numColsToDraw; col++) {
                if (col >= mapLoading.numCols) break;
                if (mapLoading.map[row][col] == 0) continue;
                int rc = mapLoading.map[row][col];
                int r = rc / tileLoading.numTilesAcross;
                int c = rc % tileLoading.numTilesAcross;
                g.drawImage(tileLoading.tiles[r][c].getImage(), (int) mapLoading.x + col * tileSize, (int) mapLoading.y + row * tileSize, null);
            }
        }
    }
}