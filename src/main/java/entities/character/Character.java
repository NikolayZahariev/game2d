package entities.character;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * @author Denis Dimitrov <denis.k.dimitrov@gmail.com>.
 */
public class Character {
    public int health;
    public int maxHealth;
    public int fire;
    public int maxFire;
    public boolean dead;
    public boolean flinching;
    public long flinchTimer;
    public boolean firing;
    public int fireCost;
    public int fireBallDamage;
    public boolean scratching;
    public int scratchDamage;
    public int scratchRange;
    public boolean gliding;
    public ArrayList<BufferedImage[]> sprites;
    public final int[] numFrames = {2, 8, 1, 2, 4, 2, 5};

    public Character(int health, int maxHealth, int fire, int maxFire, boolean dead, boolean flinching, long flinchTimer, boolean firing, int fireCost, int fireBallDamage, boolean scratching, int scratchDamage, int scratchRange, boolean gliding, ArrayList<BufferedImage[]> sprites) {
        this.health = health;
        this.maxHealth = maxHealth;
        this.fire = fire;
        this.maxFire = maxFire;
        this.dead = dead;
        this.flinching = flinching;
        this.flinchTimer = flinchTimer;
        this.firing = firing;
        this.fireCost = fireCost;
        this.fireBallDamage = fireBallDamage;
        this.scratching = scratching;
        this.scratchDamage = scratchDamage;
        this.scratchRange = scratchRange;
        this.gliding = gliding;
        this.sprites = sprites;
    }
}
