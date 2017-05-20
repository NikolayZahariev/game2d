package entities.enemies;

import core.CollisionDetection;
import core.SpriteDimensions;
import core.SpriteSheet;
import core.Visualization;
import entities.core.*;
import tilemaps.TileMap;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author Nikolay Zahariev <nikolay.g.zahariev@gmail.com>.
 */
public class Slugger {
    public CollisionDetection collision;
    public SpriteDimensions spriteDimensions;
    public SpriteSheet spriteSheet;
    public EnemyStats enemyStats = new EnemyStats(2, 2, false, false, 0, false, 1, new ArrayList<>(), new int[]{1});
    public Actions action = new ActionsBuilder().buildAnimations();
    public Movement movement;
    public Visualization visualization;

    public Slugger(TileMap tileMap) {
        collision = new CollisionDetection(tileMap);
        spriteSheet = new SpriteSheet("/sprites/enemies/slugger.gif", 1);
        movement = new Movement(0.1, 0.9, 0.4, 0.15, 4.0, -4.8, 0.3);
        spriteDimensions = new SpriteDimensions(30, 30);
        collision.cwidth = 20;
        collision.cheight = 20;
        spriteSheet.getTestEnemySpriteSheet(enemyStats, spriteDimensions);
        visualization = new Visualization();
        visualization.setFrames(enemyStats.sprites.get(action.idle));
        visualization.setDelay(300);
    }
}