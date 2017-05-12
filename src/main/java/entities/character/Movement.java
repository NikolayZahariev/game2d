package entities.character;

/**
 * @author Denis Dimitrov <denis.k.dimitrov@gmail.com>.
 */
public class Movement {
    public final double moveSpeed;
    public final double maxSpeed;
    public final double stopSpeed;
    public final double fallSpeed;
    public final double maxFallSpeed;
    public final double jumpStart;
    public final double stopJumpSpeed;

    public Movement(double moveSpeed, double maxSpeed, double stopSpeed, double fallSpeed, double maxFallSpeed, double jumpStart, double stopJumpSpeed) {
        this.moveSpeed = moveSpeed;
        this.maxSpeed = maxSpeed;
        this.stopSpeed = stopSpeed;
        this.fallSpeed = fallSpeed;
        this.maxFallSpeed = maxFallSpeed;
        this.jumpStart = jumpStart;
        this.stopJumpSpeed = stopJumpSpeed;
    }
}
