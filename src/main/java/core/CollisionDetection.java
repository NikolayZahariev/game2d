package core;

import entities.core.MapPlacement;
import tilemaps.Tile;
import tilemaps.TileMap;
import entities.enemies.TestEnemy;

import java.awt.*;

/**
 * @author Nikolay Zahariev <nikolay.g.zahariev@gmail.com>.
 */
public class CollisionDetection {
    protected TileMap tileMap;
    protected int tileSize;
    public double dx;
    public double dy;
    public int cwidth;
    public int cheight;
    protected int currRow;
    protected int currCol;
    protected double xdest;
    protected double ydest;
    public double xtemp;
    public double ytemp;
    protected boolean topLeft;
    protected boolean topRight;
    protected boolean bottomLeft;
    protected boolean bottomRight;
    public boolean falling;
    public MapPlacement characterMapPlacement;

    public CollisionDetection(TileMap tileMap) {
        this.tileMap = tileMap;
        tileSize = tileMap.getTileSize();
        characterMapPlacement = new MapPlacement(this.tileMap);
    }

    public boolean hitboxIntersection(TestEnemy enemy) {
        Rectangle playerHitbox = getEntityHitbox();
        Rectangle enemyHitbox = enemy.collision.getEntityHitbox();
        return playerHitbox.intersects(enemyHitbox);
    }

    public Rectangle getEntityHitbox() {
        return new Rectangle(
                (int)characterMapPlacement.x - cwidth,
                (int)characterMapPlacement.y - cheight,
                cwidth,
                cheight
        );
    }

    public void checkTileMapCollision() {
        double x = characterMapPlacement.x;
        double y = characterMapPlacement.y;

        currCol = (int) x / tileSize;
        currRow = (int) y / tileSize;

        xdest = x + dx;
        ydest = y + dy;

        xtemp = x;
        ytemp = y;

        calculateCorners(x, ydest);
        if (dy < 0) {
            if (topLeft || topRight) {
                dy = 0;
                ytemp = currRow * tileSize + cheight / 2;
            } else {
                ytemp += dy;
            }
        }
        if (dy > 0) {
            if (bottomLeft || bottomRight) {
                dy = 0;
                falling = false;
                ytemp = (currRow + 1) * tileSize - cheight / 2;
            } else {
                ytemp += dy;
            }
        }

        calculateCorners(xdest, y);
        if (dx < 0) {
            if (topLeft || bottomLeft) {
                dx = 0;
                xtemp = currCol * tileSize + cwidth / 2;
            } else {
                xtemp += dx;
            }
        }
        if (dx > 0) {
            if (topRight || bottomRight) {
                dx = 0;
                xtemp = (currCol + 1) * tileSize - cwidth / 2;
            } else {
                xtemp += dx;
            }
        }

        if (!falling) {
            calculateCorners(x, ydest + 1);
            if (!bottomLeft && !bottomRight) {
                falling = true;
            }
        }
    }

    private void calculateCorners(double x, double y) {
        int leftTile = (int) (x - cwidth / 2) / tileSize;
        int rightTile = (int) (x + cwidth / 2 - 1) / tileSize;
        int topTile = (int) (y - cheight / 2) / tileSize;
        int bottomTile = (int) (y + cheight / 2 - 1) / tileSize;

        int tl = tileMap.getType(topTile, leftTile);
        int tr = tileMap.getType(topTile, rightTile);
        int bl = tileMap.getType(bottomTile, leftTile);
        int br = tileMap.getType(bottomTile, rightTile);

        topLeft = tl == Tile.BLOCKED;
        topRight = tr == Tile.BLOCKED;
        bottomLeft = bl == Tile.BLOCKED;
        bottomRight = br == Tile.BLOCKED;
    }
}
