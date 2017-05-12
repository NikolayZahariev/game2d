package entities;

import core.Character;
import core.*;
import tilemaps.TileMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Berserker {
    private Character character = new Character(5, 5, 2500, 2500, false, false, 0, false, 200, 5, false, 8, 40, false, new ArrayList<>());
    private CharacterAction action = new CharacterActionBuilder().buildAnimations();
    public CharacterMoveSet moveSet = new CharacterMoveSet();
    public CharacterCollisionDetection collision;
    private Movement movement;
    protected int width;
    protected int height;
    protected Visualization visualization;
    protected boolean facingRight;
    protected int currentAction;

    public Berserker(TileMap tm) {
        collision = new CharacterCollisionDetection(tm);
        movement = new Movement(0.3, 1.6, 0.4, 0.15, 4.0, -4.8, 0.3);
        width = 30;
        height = 30;
        collision.cwidth = 20;
        collision.cheight = 20;
        facingRight = true;
        try {
            BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Sprites/player/playersprites.gif")
            );
            character.sprites = new ArrayList<>();
            for (int i = 0; i < 7; i++) {
                BufferedImage[] bi = new BufferedImage[character.numFrames[i]];
                for (int j = 0; j < character.numFrames[i]; j++) {
                    if (i != 6) {
                        bi[j] = spritesheet.getSubimage(j * width, i * height, width, height);
                    } else {
                        bi[j] = spritesheet.getSubimage(j * width * 2, i * height, width, height);
                    }
                }
                character.sprites.add(bi);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        visualization = new Visualization();
        currentAction = action.idle;
        visualization.setFrames(character.sprites.get(action.idle));
        visualization.setDelay(400);
    }

    private void getNextPosition() {
        if (moveSet.getLeft()) {
            collision.dx -= movement.moveSpeed;
            if (collision.dx < -movement.maxSpeed) {
                collision.dx = -movement.maxSpeed;
            }
        } else if (moveSet.getRight()) {
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
        if ((currentAction == action.scratching || currentAction == action.fireball) && !(moveSet.getJumping() || collision.falling)) {
            collision.dx = 0;
        }
        if (moveSet.getJumping() && !collision.falling) {
            collision.dy = movement.jumpStart;
            collision.falling = true;
        }
        if (collision.falling) {
            collision.dy += movement.fallSpeed;
            if (collision.dy > 0) {
                moveSet.setJumping(false);
            }
            if (collision.dy < 0 && !moveSet.getJumping()) {
                collision.dy += movement.stopJumpSpeed;
            }
            if (collision.dy > movement.maxFallSpeed) {
                collision.dy = movement.maxFallSpeed;
            }
        }
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
                width = 60;
            }
        } else if (character.firing) {
            if (currentAction != action.fireball) {
                currentAction = action.fireball;
                visualization.setFrames(character.sprites.get(action.fireball));
                visualization.setDelay(100);
                width = 30;
            }
        } else if (collision.dy > 0) {
            if (currentAction != action.falling) {
                currentAction = action.falling;
                visualization.setFrames(character.sprites.get(action.falling));
                visualization.setDelay(100);
                width = 30;
            }
        } else if (collision.dy < 0) {
            if (currentAction != action.jumping) {
                currentAction = action.jumping;
                visualization.setFrames(character.sprites.get(action.jumping));
                visualization.setDelay(-1);
                width = 30;
            }
        } else if (moveSet.getLeft() || moveSet.getRight()) {
            if (currentAction != action.walking) {
                currentAction = action.walking;
                visualization.setFrames(character.sprites.get(action.walking));
                visualization.setDelay(40);
                width = 30;
            }
        } else {
            if (currentAction != action.idle) {
                currentAction = action.idle;
                visualization.setFrames(character.sprites.get(action.idle));
                visualization.setDelay(400);
                width = 30;
            }
        }
        visualization.update();
        if (currentAction != action.scratching && currentAction != action.fireball) {
            if (moveSet.getRight()) {
                facingRight = true;
            }
            if (moveSet.getLeft()) {
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
            g.drawImage(visualization.getImage(), (int) (collision.characterMapPlacement.x + collision.characterMapPlacement.xmap - width / 2), (int) (collision.characterMapPlacement.y + collision.characterMapPlacement.ymap - height / 2), null);
        } else {
            g.drawImage(visualization.getImage(), (int) (collision.characterMapPlacement.x + collision.characterMapPlacement.xmap - width / 2 + width), (int) (collision.characterMapPlacement.y + collision.characterMapPlacement.ymap - height / 2), -width, height, null);
        }
    }
}