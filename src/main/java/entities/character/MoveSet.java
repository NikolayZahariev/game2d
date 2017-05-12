package entities.character;

/**
 * @author Nikolay Zahariev <nikolay.g.zahariev@gmail.com>.
 */
public class MoveSet {
    public boolean left;
    public boolean right;
    public boolean up;
    public boolean down;
    public boolean jumping;

    public MoveSet(boolean left, boolean right, boolean up, boolean down, boolean jumping) {
        this.left = left;
        this.right = right;
        this.up = up;
        this.down = down;
        this.jumping = jumping;
    }
}
