package states;

import java.util.ArrayList;

public class GameStateManager {
    public final int MENUSTATE = 0;
    private ArrayList<GameState> gameStates = new ArrayList<>();
    private int currentState;

    public GameStateManager() {
        currentState = MENUSTATE;
        gameStates.add(new MenuState(this));
    }

    public void update() {
        gameStates.get(currentState).update();
    }

    public void draw(java.awt.Graphics2D g) {
        gameStates.get(currentState).draw(g);
    }

    public void keyPressed(int k) {
        gameStates.get(currentState).keyPressed(k);
    }

    public void keyReleased(int k) {
        gameStates.get(currentState).keyReleased(k);
    }
}