package states;

import java.awt.*;

public abstract class GameState {
    protected GameStateManager gameStateManager;

    public abstract void init();

    public abstract void update();

    public abstract void draw(Graphics g);

    public abstract void keyPressed(int k);

    public abstract void keyReleased(int k);
}