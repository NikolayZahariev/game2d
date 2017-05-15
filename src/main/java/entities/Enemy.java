package entities;

import entities.character.EnemyStats;
import tilemaps.TileMap;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author Nikolay Zahariev <nikolay.g.zahariev@gmail.com>.
 */
public class Enemy {
    public boolean flinching;
    public long flinchTimer;
    EnemyStats enemyStats;

    public Enemy(EnemyStats enemyStats) {
        this.enemyStats = enemyStats;
    }

    public boolean isDead() {
        return enemyStats.dead;
    }

    public int getDamage() {
        return enemyStats.attackDamage;
    }

    public void hit(int damage) {
        if (enemyStats.dead || flinching) {
            return;
        }
        enemyStats.health -= damage;
        if (enemyStats.health < 0) {
            enemyStats.health = 0;
        }
        if (enemyStats.health == 0) {
            enemyStats.dead = true;
        }
        flinching = true;
        flinchTimer = System.nanoTime();
    }
}
