package entities;

import core.CollisionDetection;
import core.SpriteDimensions;
import core.SpriteSheet;
import core.Visualization;
import entities.character.*;
import entities.character.Character;
import tilemaps.TileMap;

import java.awt.*;
import java.util.ArrayList;

public class Berserker {
    public CollisionDetection collision;
    public MoveSet moveSet = new MoveSet(false, false, false, false, false);
    private SpriteDimensions spriteDimensions;
    private SpriteSheet spriteSheet = new SpriteSheet("/sprites/player/playersprites.gif", 7);
    private Character character = new Character(5, 5, 2500, 2500, false, false, 0, false, 200, 5, false, 8, 40, false, new ArrayList<>(), new int[]{2, 8, 1, 2, 4, 2, 5});
    private Actions action = new ActionsBuilder().buildAnimations();
    private Movement movement;
    private Visualization visualization;
    private boolean facingRight;
    private int currentAction;

    public Berserker(TileMap tileMap) {
        collision = new CollisionDetection(tileMap);
        movement = new Movement(0.3, 1.6, 0.4, 0.15, 4.0, -4.8, 0.3);
        spriteDimensions = new SpriteDimensions(30, 30);
        collision.cwidth = 20;
        collision.cheight = 20;
        facingRight = true;
        spriteSheet.getCharacterSpriteSheet(character, spriteDimensions);
        visualization = new Visualization();
        currentAction = action.idle;
        visualization.setFrames(character.sprites.get(action.idle));
        visualization.setDelay(400);
    }

    public void update() {
        getNextPosition();
        collision.checkTileMapCollision();
        collision.characterMapPlacement.setPosition(collision.xtemp, collision.ytemp);
        if (character.scratching) {
            if (currentAction != action.scratching) {
                currentAction = action.scratching;
                visualization.setFrames(character.sprites.get(action.scratching));
                visualization.setDelay(50);
                spriteDimensions.width = 60;
            }
        } else if (character.firing) {
            if (currentAction != action.fireball) {
                currentAction = action.fireball;
                visualization.setFrames(character.sprites.get(action.fireball));
                visualization.setDelay(100);
                spriteDimensions.width = 30;
            }
        } else if (collision.dy > 0) {
            if (currentAction != action.falling) {
                currentAction = action.falling;
                visualization.setFrames(character.sprites.get(action.falling));
                visualization.setDelay(100);
                spriteDimensions.width = 30;
            }
        } else if (collision.dy < 0) {
            if (currentAction != action.jumping) {
                currentAction = action.jumping;
                visualization.setFrames(character.sprites.get(action.jumping));
                visualization.setDelay(-1);
                spriteDimensions.width = 30;
            }
        } else if (moveSet.left || moveSet.right) {
            if (currentAction != action.walking) {
                currentAction = action.walking;
                visualization.setFrames(character.sprites.get(action.walking));
                visualization.setDelay(40);
                spriteDimensions.width = 30;
            }
        } else {
            if (currentAction != action.idle) {
                currentAction = action.idle;
                visualization.setFrames(character.sprites.get(action.idle));
                visualization.setDelay(400);
                spriteDimensions.width = 30;
            }
        }
        visualization.update();
        if (currentAction != action.scratching && currentAction != action.fireball) {
            if (moveSet.right) {
                facingRight = true;
            }
            if (moveSet.left) {
                facingRight = false;
            }
        }
    }

    public void draw(Graphics g) {
        collision.characterMapPlacement.setMapPosition();
        if (character.flinching) {
            long elapsed = (System.nanoTime() - character.flinchTimer) / 1000000;
            if (elapsed / 100 % 2 == 0) {
                return;
            }
        }
        if (facingRight) {
            g.drawImage(visualization.getImage(), (int) (collision.characterMapPlacement.x + collision.characterMapPlacement.xmap - spriteDimensions.width / 2), (int) (collision.characterMapPlacement.y + collision.characterMapPlacement.ymap - spriteDimensions.height / 2), null);
        } else {
            g.drawImage(visualization.getImage(), (int) (collision.characterMapPlacement.x + collision.characterMapPlacement.xmap - spriteDimensions.width / 2 + spriteDimensions.width), (int) (collision.characterMapPlacement.y + collision.characterMapPlacement.ymap - spriteDimensions.height / 2), -spriteDimensions.width, spriteDimensions.height, null);
        }
    }

    private void getNextPosition() {
        if (moveSet.left) {
            collision.dx -= movement.moveSpeed;
            if (collision.dx < -movement.maxSpeed) {
                collision.dx = -movement.maxSpeed;
            }
        } else if (moveSet.right) {
            collision.dx += movement.moveSpeed;
            if (collision.dx > movement.maxSpeed) {
                collision.dx = movement.maxSpeed;
            }
        } else {
            if (collision.dx > 0) {
                collision.dx -= movement.stopSpeed;
                if (collision.dx < 0) {
                    collision.dx = 0;
                }
            } else if (collision.dx < 0) {
                collision.dx += movement.stopSpeed;
                if (collision.dx > 0) {
                    collision.dx = 0;
                }
            }
        }
        if ((currentAction == action.scratching || currentAction == action.fireball) && !(moveSet.jumping || collision.falling)) {
            collision.dx = 0;
        }
        if (moveSet.jumping && !collision.falling) {
            collision.dy = movement.jumpStart;
            collision.falling = true;
        }
        if (collision.falling) {
            collision.dy += movement.fallSpeed;
            if (collision.dy > 0) {
                moveSet.jumping = false;
            }
            if (collision.dy < 0 && !moveSet.jumping) {
                collision.dy += movement.stopJumpSpeed;
            }
            if (collision.dy > movement.maxFallSpeed) {
                collision.dy = movement.maxFallSpeed;
            }
        }
    }
}