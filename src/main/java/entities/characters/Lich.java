package entities.characters;

import core.CollisionDetection;
import core.SpriteDimensions;
import core.SpriteSheet;
import core.Visualization;
import entities.core.Actions;
import entities.core.ActionsBuilder;
import entities.core.Character;
import entities.core.Movement;
import tilemaps.TileMap;

import java.util.ArrayList;

/**
 * @author Denis Dimitrov <denis.k.dimitrov@gmail.com>.
 */
public class Lich {
    public CollisionDetection collision;
    public SpriteDimensions spriteDimensions;
    public SpriteSheet spriteSheet;
    public Character character = new Character(10, 10, false, false, 0, false, 3, 31, new ArrayList<>(), new int[]{2, 8, 1, 2, 4, 2, 5});
    public Actions action = new ActionsBuilder().buildAnimations();
    public Movement movement;
    public Visualization visualization;

    public Lich(TileMap tileMap) {
        collision = new CollisionDetection(tileMap);
        spriteSheet = new SpriteSheet("/sprites/player/lich.png", 7);
        movement = new Movement(0.9, 3.9, 0.4, 0.15, 4.0, -4.8, 0.3);
        spriteDimensions = new SpriteDimensions(30, 30);
        collision.cwidth = 20;
        collision.cheight = 20;
        spriteSheet.getCharacterSpriteSheet(character, spriteDimensions);
        visualization = new Visualization();
        visualization.setFrames(character.sprites.get(action.idle));
        visualization.setDelay(400);
    }
}
