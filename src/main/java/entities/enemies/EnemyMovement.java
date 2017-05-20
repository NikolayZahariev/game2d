package entities.enemies;

import entities.characters.Player;

import java.util.ArrayList;

/**
 * @author Nikolay Zahariev <nikolay.g.zahariev@gmail.com>.
 */
public class EnemyMovement {
    public Player player;
    public Enemy enemy;

    public EnemyMovement(Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
    }

    public void movement() {
        if (moveLeft(enemy)) {
            enemy.moveSet.right = false;
            enemy.moveSet.left = true;
        } else {
            enemy.moveSet.left = false;
            enemy.moveSet.right = true;
        }

        enemy.moveSet.jumping = jump(enemy);
    }

    private boolean moveLeft(Enemy enemy) {
        return enemy.collision.characterMapPlacement.getx() > player.collision.characterMapPlacement.x;
    }

    private boolean jump(Enemy enemy) {
        return enemy.collision.characterMapPlacement.gety() > player.collision.characterMapPlacement.y;
    }
}
