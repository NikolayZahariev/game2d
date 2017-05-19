package entities.enemies;

import entities.characters.Player;

import java.util.ArrayList;

/**
 * @author Nikolay Zahariev <nikolay.g.zahariev@gmail.com>.
 */
public class EnemyMovement {
    public Player player;
    public Slugger enemy;

    public EnemyMovement(Player player, Slugger enemy) {
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

        if (jump(enemy)) {
            enemy.moveSet.jumping = true;
        } else {
            enemy.moveSet.jumping = false;
        }
    }

    private boolean moveLeft(Slugger enemy) {
        if (enemy.collision.characterMapPlacement.getx() > player.collision.characterMapPlacement.x) {
            return true;
        }
        return false;
    }

    private boolean jump(Slugger enemy) {
        if (enemy.collision.characterMapPlacement.gety() > player.collision.characterMapPlacement.y) {
            return true;
        }
        return false;
    }
}
