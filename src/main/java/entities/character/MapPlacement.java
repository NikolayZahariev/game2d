package entities.character;

import tilemaps.TileMap;

/**
 * @author Nikolay Zahariev <nikolay.g.zahariev@gmail.com>.
 */
public class MapPlacement {

    protected TileMap tileMap;
    public double xmap;
    public double ymap;
    public double x;
    public double y;

    public MapPlacement(TileMap tileMap) {
        this.tileMap = tileMap;
    }

    public int getx() {
        return (int) x;
    }

    public int gety() {
        return (int) y;
    }

    public void setMapPosition() {
        xmap = tileMap.mapLoading.getx();
        ymap = tileMap.mapLoading.gety();
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }
}