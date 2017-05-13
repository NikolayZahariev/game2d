package tilemaps;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileMap {
    private double tween;
    private int tileSize;
    private int rowOffset;
    private int colOffset;
    private int numRowsToDraw;
    private int numColsToDraw;

    public TileLoading tileLoading;
    public MapLoading mapLoading;

    public TileMap(int tileSize) {
        this.tileSize = tileSize;
        tileLoading = new TileLoading(this.tileSize);
        mapLoading = new MapLoading(this.tileSize);
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