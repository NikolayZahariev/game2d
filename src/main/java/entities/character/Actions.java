package entities.character;

/**
 * @author Denis Dimitrov <denis.k.dimitrov@gmail.com>.
 */
public class Actions {
    public int idle;
    public int walking;
    public int jumping;
    public int falling;
    public int gliding;
    public int fireball;
    public int scratching;

    public Actions(int idle, int walking, int jumping, int falling, int gliding, int fireball, int scratching) {
        this.idle = idle;
        this.walking = walking;
        this.jumping = jumping;
        this.falling = falling;
        this.gliding = gliding;
        this.fireball = fireball;
        this.scratching = scratching;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Actions that = (Actions) o;

        if (idle != that.idle) return false;
        if (walking != that.walking) return false;
        if (jumping != that.jumping) return false;
        if (falling != that.falling) return false;
        if (gliding != that.gliding) return false;
        if (fireball != that.fireball) return false;
        return scratching == that.scratching;
    }

    @Override
    public int hashCode() {
        int result = idle;
        result = 31 * result + walking;
        result = 31 * result + jumping;
        result = 31 * result + falling;
        result = 31 * result + gliding;
        result = 31 * result + fireball;
        result = 31 * result + scratching;
        return result;
    }

    @Override
    public String toString() {
        return "Actions{" +
                "idle=" + idle +
                ", walking=" + walking +
                ", jumping=" + jumping +
                ", falling=" + falling +
                ", gliding=" + gliding +
                ", fireball=" + fireball +
                ", scratching=" + scratching +
                '}';
    }
}
