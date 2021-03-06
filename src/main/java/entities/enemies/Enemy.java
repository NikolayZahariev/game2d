package entities.enemies;

import core.CollisionDetection;
import core.SpriteDimensions;
import core.SpriteSheet;
import core.Visualization;
import entities.core.*;
import tilemaps.TileMap;

import java.awt.*;

/**
 * @author Nikolay Zahariev <nikolay.g.zahariev@gmail.com>.
 */
public class Enemy {
    public CollisionDetection collision;
    public MoveSet moveSet = new MoveSet(false, false, false, false, false);
    private SpriteDimensions spriteDimensions;
    private Movement movement;
    private Visualization visualization;
    private boolean facingRight;
    private int currentAction;
    private Actions action = new ActionsBuilder().buildAnimations();
    private SpriteSheet spriteSheet;
    public EnemyStats enemyStats;

    public Enemy(TileMap tileMap, SpriteSheet spriteSheet, EnemyStats enemyStats, Movement movement) {
        this.spriteSheet = spriteSheet;
        this.enemyStats = enemyStats;
        this.movement = movement;
        collision = new CollisionDetection(tileMap);
        spriteDimensions = new SpriteDimensions(30, 30);
        collision.cwidth = 20;
        collision.cheight = 20;
        facingRight = true;
        spriteSheet.getTestEnemySpriteSheet(enemyStats, spriteDimensions);
        visualization = new Visualization();
        currentAction = action.idle;
        visualization.setFrames(enemyStats.sprites.get(action.idle));
        visualization.setDelay(400);
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

        if (moveSet.jumping && !collision.falling) {
            collision.dy = movement.jumpStart;
            collision.falling = true;
        }

        if (collision.falling) {
            collision.dy += movement.fallSpeed;
        }
    }

    public void update() {
        getNextPosition();
        collision.checkTileMapCollision();
        collision.characterMapPlacement.setPosition(collision.xtemp, collision.ytemp);
        if (enemyStats.flinching) {
            long elapsed =
                    (System.nanoTime() - enemyStats.flinchTimer) / 1000000;
            if (elapsed > 400) {
                enemyStats.flinching = false;
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

    public boolean isDead() {
        return enemyStats.dead;
    }

    public int getDamage() {
        return enemyStats.attackDamage;
    }

    public void onDamageTaken(int damage) {
        if (enemyStats.dead || enemyStats.flinching) {
            return;
        }
        enemyStats.health -= damage;
        if (enemyStats.health < 0) {
            enemyStats.health = 0;
        }
        if (enemyStats.health == 0) {
            enemyStats.dead = true;
        }
        enemyStats.flinching = true;
        enemyStats.flinchTimer = System.nanoTime();
    }
}