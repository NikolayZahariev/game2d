package entities.enemies;

import entities.core.EnemyStats;

/**
 * @author Nikolay Zahariev <nikolay.g.zahariev@gmail.com>.
 */
public class Enemy {
    public EnemyStats enemyStats;

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