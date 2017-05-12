package entities.character;

/**
 * @author Denis Dimitrov <denis.k.dimitrov@gmail.com>.
 */
public class ActionsBuilder {
    public int idle = 0;
    public int walking = 1;
    public int jumping = 2;
    public int falling = 3;
    public int gliding = 4;
    public int fireball = 5;
    public int scratching = 6;


    public ActionsBuilder idle(int idle) {
        this.idle = idle;
        return this;
    }

    public ActionsBuilder walking(int walking) {
        this.walking = walking;
        return this;
    }

    public ActionsBuilder jumping(int jumping) {
        this.jumping = jumping;
        return this;
    }

    public ActionsBuilder falling(int falling) {
        this.falling = falling;
        return this;
    }

    public ActionsBuilder gliding(int gliding) {
        this.gliding = gliding;
        return this;
    }

    public ActionsBuilder fireball(int items) {
        this.fireball = fireball;
        return this;
    }
    public ActionsBuilder scratching(int scratching) {
        this.scratching = scratching;
        return this;
    }

    public Actions buildAnimations() {
        return new Actions(idle, walking, jumping, falling, gliding, falling, scratching);
    }
}
