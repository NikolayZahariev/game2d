package core;

/**
 * @author Denis Dimitrov <denis.k.dimitrov@gmail.com>.
 */
public class CharacterActionBuilder {
    public int idle = 0;
    public int walking = 1;
    public int jumping = 2;
    public int falling = 3;
    public int gliding = 4;
    public int fireball = 5;
    public int scratching = 6;


    public CharacterActionBuilder idle(int idle) {
        this.idle = idle;
        return this;
    }

    public CharacterActionBuilder walking(int walking) {
        this.walking = walking;
        return this;
    }

    public CharacterActionBuilder jumping(int jumping) {
        this.jumping = jumping;
        return this;
    }

    public CharacterActionBuilder falling(int falling) {
        this.falling = falling;
        return this;
    }

    public CharacterActionBuilder gliding(int gliding) {
        this.gliding = gliding;
        return this;
    }

    public CharacterActionBuilder fireball(int items) {
        this.fireball = fireball;
        return this;
    }
    public CharacterActionBuilder scratching(int scratching) {
        this.scratching = scratching;
        return this;
    }

    public CharacterAction buildAnimations() {
        return new CharacterAction(idle, walking, jumping, falling, gliding, falling, scratching);
    }
}
