package entities.core;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * @author Nikolay Zahariev <nikolay.g.zahariev@gmail.com>.
 */
public class EnemyStats {
    public int health;
    public int maxHealth;
    public boolean dead;
    public boolean flinching;
    public long flinchTimer;
    public boolean attacking;
    public int attackDamage;
    public ArrayList<BufferedImage[]> sprites;
    public int[] numFrames;

    public EnemyStats(int health, int maxHealth, boolean dead, boolean flinching, long flinchTimer, boolean attacking, int attackDamage, ArrayList<BufferedImage[]> sprites, int[] numFrames) {
        this.health = health;
        this.maxHealth = maxHealth;
        this.dead = dead;
        this.flinching = flinching;
        this.flinchTimer = flinchTimer;
        this.attacking = attacking;
        this.attackDamage = attackDamage;
        this.sprites = sprites;
        this.numFrames = numFrames;
    }
}
