package states;

import main.State;

import java.util.ArrayList;

/**
 * @author Nikolay Zahariev <nikolay.g.zahariev@gmail.com>.
 */
public class StatePlaceholders {

    public ArrayList<State> states = new ArrayList<>();
    public MenuState menuState;
    public CharState charState;
    public SettingsState settingsState;
    public LevelOne levelOne;
    public HelpState helpState;

    public StatePlaceholders(MenuState menu, CharState charState, HelpState helpState, SettingsState settingsState, LevelOne lvlOne) {
        this.menuState = menu;
        this.charState = charState;
        this.settingsState = settingsState;
        this.helpState = helpState;
        this.levelOne = lvlOne;
        populateStatesArray();
    }

    private void populateStatesArray() {
        states.add(menuState);
        states.add(charState);
        states.add(settingsState);
        states.add(helpState);
        states.add(levelOne);
    }
}
