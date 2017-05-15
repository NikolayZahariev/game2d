package entities.enemies;

import core.CollisionDetection;
import core.SpriteDimensions;
import core.SpriteSheet;
import core.Visualization;
import entities.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entities.character.*;
import entities.character.Character;
import tilemaps.TileMap;

/**
 * @author Nikolay Zahariev <nikolay.g.zahariev@gmail.com>.
 */
public class TestEnemy {
    public CollisionDetection collision;
    public MoveSet moveSet = new MoveSet(false, false, false, false, false);
    private SpriteSheet spriteSheet = new SpriteSheet("/sprites/enemies/slugger.gif", 1);
    private SpriteDimensions spriteDimensions;
    private EnemyStats enemyStats = new EnemyStats(2, 2, 2500, 2500, false, false, 0, false, 200, 1, new ArrayList<>(), new int[]{1});
    private Actions action = new ActionsBuilder().buildAnimations();
    private Movement movement;
    private Visualization visualization;
    private boolean facingRight;
    public Enemy enemy = new Enemy(enemyStats);

    public TestEnemy(TileMap tileMap) {
        collision = new CollisionDetection(tileMap);
        movement = new Movement(0.3, 1.6, 0.4, 0.15, 4.0, -4.8, 0.3);
        spriteDimensions = new SpriteDimensions(30, 30);
        collision.cwidth = 20;
        collision.cheight = 20;
        facingRight = true;
        spriteSheet.getTestEnemySpriteSheet(enemyStats, spriteDimensions);
        visualization = new Visualization();
        visualization.setFrames(enemyStats.sprites.get(action.idle));
        visualization.setDelay(300);
    }

    private void getNextPosition() {
        if (moveSet.left) {
            collision.dx -= movement.moveSpeed;
            if (collision.dx < -movement.maxSpeed) {
                collision.dx = -movement.maxSpeed;
            }
        } else if (moveSet.right) {
            collision.dx += movement.moveSpeed;
            if (collision.dx > movement.maxSpeed) {
                collision.dx = movement.maxSpeed;
            }
        }

        if (collision.falling) {
            collision.dy += movement.fallSpeed;
        }
    }

    public void update() {
        getNextPosition();
        collision.checkTileMapCollision();
        collision.characterMapPlacement.setPosition(collision.xtemp, collision.ytemp);

        if (enemy.flinching) {
            long elapsed =
                    (System.nanoTime() - enemy.flinchTimer) / 1000000;
            if (elapsed > 400) {
                enemy.flinching = false;
            }
        }

        if (moveSet.right && collision.dx == 0) {
            moveSet.right = false;
            moveSet.left = true;
            facingRight = false;
        } else if (moveSet.left && collision.dx == 0) {
            moveSet.right = true;
            moveSet.left = false;
            facingRight = true;
        }

        visualization.update();

    }

    public void draw(Graphics g) {
        collision.characterMapPlacement.setMapPosition();
        if (facingRight) {
            g.drawImage(
                    visualization.getImage(),
                    (int) (collision.characterMapPlacement.x + collision.characterMapPlacement.xmap - spriteDimensions.width / 2),
                    (int) (collision.characterMapPlacement.y + collision.characterMapPlacement.ymap - spriteDimensions.height / 2),
                    null
            );
        } else {
            g.drawImage(
                    visualization.getImage(),
                    (int) (collision.characterMapPlacement.x + collision.characterMapPlacement.xmap - spriteDimensions.width / 2 + spriteDimensions.width),
                    (int) (collision.characterMapPlacement.y + collision.characterMapPlacement.ymap - spriteDimensions.height / 2),
                    -spriteDimensions.width,
                    spriteDimensions.height,
                    null
            );
        }
    }
}