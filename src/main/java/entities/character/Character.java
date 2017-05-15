package entities.character;

        import java.awt.image.BufferedImage;
        import java.util.ArrayList;

/**
 * @author Denis Dimitrov <denis.k.dimitrov@gmail.com>.
 */
public class Character {
    public int health;
    public int maxHealth;
    public boolean dead;
    public boolean flinching;
    public long flinchTimer;
    public boolean attacking;
    public int attackDamage;
    public int attackRange;
    public ArrayList<BufferedImage[]> sprites;
    public int[] numFrames;

    public Character(int health, int maxHealth, boolean dead, boolean flinching, long flinchTimer, boolean attacking, int attackDamage, int attackRange, ArrayList<BufferedImage[]> sprites, int[] numFrames) {
        this.health = health;
        this.maxHealth = maxHealth;
        this.dead = dead;
        this.flinching = flinching;
        this.flinchTimer = flinchTimer;
        this.attacking = attacking;
        this.attackDamage = attackDamage;
        this.attackRange = attackRange;
        this.sprites = sprites;
        this.numFrames = numFrames;
    }
}
