package states;

import java.awt.*;

/**
 * @author Denis Dimitrov <denis.k.dimitrov@gmail.com>.
 */
public class StateManager {
    public final int MENUSTATE = 0;
    private StatePlaceholders placeholders;
    private int currentState;

    public StateManager() {
        currentState = MENUSTATE;
        placeholders = new StatePlaceholders(new MenuState(), new CharState(), new HelpState(), new SettingsState(), new LevelOne());
    }

    public void setState(int state) {
        switch (state) {
            case 4:
                placeholders.states.set(state, new LevelOne());
                break;
        }
        currentState = state;
        placeholders.states.get(state).init();
    }

    public void update() {
        placeholders.states.get(currentState).update();
    }

    public void draw(Graphics graphics) {
        placeholders.states.get(currentState).draw(graphics);
    }

    public void keyPressed(int k) {
        placeholders.states.get(currentState).keyPressed(k);
    }

    public void keyReleased(int k) {
        placeholders.states.get(currentState).keyReleased(k);
    }
}
