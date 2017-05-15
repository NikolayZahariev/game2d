package entities.core;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * @author Nikolay Zahariev <nikolay.g.zahariev@gmail.com>.
 */
public class EnemyStats {
    public int health;
    public int maxHealth;
    public int fire;
    public int maxFire;
    public boolean dead;
    public boolean flinching;
    public long flinchTimer;
    public boolean attacking;
    public int attackCost;
    public int attackDamage;
    public ArrayList<BufferedImage[]> sprites;
    public int[] numFrames;

    public EnemyStats(int health, int maxHealth, int fire, int maxFire, boolean dead, boolean flinching, long flinchTimer, boolean attacking, int attackCost, int attackDamage, ArrayList<BufferedImage[]> sprites, int[] numFrames) {
        this.health = health;
        this.maxHealth = maxHealth;
        this.fire = fire;
        this.maxFire = maxFire;
        this.dead = dead;
        this.flinching = flinching;
        this.flinchTimer = flinchTimer;
        this.attacking = attacking;
        this.attackCost = attackCost;
        this.attackDamage = attackDamage;
        this.sprites = sprites;
        this.numFrames = numFrames;
    }
}
