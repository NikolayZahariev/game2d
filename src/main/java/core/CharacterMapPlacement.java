package core;

import tilemaps.TileMap;

/**
 * @author Nikolay Zahariev <nikolay.g.zahariev@gmail.com>.
 */
public class CharacterMapPlacement {

    protected TileMap tileMap;
    public double xmap;
    public double ymap;
    public double x;
    public double y;

    public CharacterMapPlacement(TileMap tm) {
        tileMap = tm;
    }

    public int getx() {
        return (int) x;
    }

    public int gety() {
        return (int) y;
    }

    public void setMapPosition() {
        xmap = tileMap.getx();
        ymap = tileMap.gety();
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }
}