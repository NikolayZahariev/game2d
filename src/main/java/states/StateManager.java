package states;

import core.State;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Legion on 4/30/2017.
 */
public class StateManager {
    public final int MENUSTATE = 0;
    private ArrayList<State> states = new ArrayList<>();
    private int currentState;

    public StateManager() {
        currentState = MENUSTATE;
        states.add(new MenuState());
    }

    public void update() {
        states.get(currentState).update();
    }

    public void draw(Graphics graphics) {
        states.get(currentState).draw(graphics);
    }

    public void keyPressed(int k) {
        states.get(currentState).keyPressed(k);
    }

    public void keyReleased(int k) {
        states.get(currentState).keyReleased(k);
    }
}
