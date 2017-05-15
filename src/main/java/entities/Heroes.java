package entities;

import tilemaps.TileMap;

import java.util.ArrayList;

/**
 * @author Nikolay Zahariev <nikolay.g.zahariev@gmail.com>.
 */
public class Heroes {
    public ArrayList<Object> heroes = new ArrayList<>(2);


    public Heroes(TileMap tileMap, int hero) {
        heroes.add(Berserker berserker);
        switch (hero){
            case 1:
                berserker = new Berserker(tileMap);
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                break;
        }
    }
}
