package entities;

import core.*;
import core.Character;
import tilemaps.TileMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Berserker{
    private Character character = new Character(5, 5, 2500, 2500, false, false, 0, false, 200, 5, false, 8, 40, false, new ArrayList<>());
    private CharacterAction action = new CharacterActionBuilder().buildAnimations();
    public CharacterMovement movement = new CharacterMovement();
    public CharacterCollisionDetection collision;
    protected int width;
    protected int height;
    protected double moveSpeed;
    protected double maxSpeed;
    protected double stopSpeed;
    protected double fallSpeed;
    protected double maxFallSpeed;
    protected double jumpStart;
    protected double stopJumpSpeed;
    protected Visualization animation;
    protected int currentAction;
    protected boolean facingRight;

    public Berserker(TileMap tm) {
        collision = new CharacterCollisionDetection(tm);
        width = 30;
        height = 30;
        collision.cwidth = 20;
        collision.cheight = 20;
        moveSpeed = 0.3;
        maxSpeed = 1.6;
        stopSpeed = 0.4;
        fallSpeed = 0.15;
        maxFallSpeed = 4.0;
        jumpStart = -4.8;
        stopJumpSpeed = 0.3;
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
        animation = new Visualization();
        currentAction = action.idle;
        animation.setFrames(character.sprites.get(action.idle));
        animation.setDelay(400);
    }

    private void getNextPosition() {
        if (movement.getLeft()) {
            collision.dx -= moveSpeed;
            if (collision.dx < -maxSpeed) {
                collision.dx = -maxSpeed;
            }
        } else if (movement.getRight()) {
            collision.dx += moveSpeed;
            if (collision.dx > maxSpeed) {
                collision.dx = maxSpeed;
            }
        } else {
            if (collision.dx > 0) {
                collision.dx -= stopSpeed;
                if (collision.dx < 0) {
                    collision.dx = 0;
                }
            } else if (collision.dx < 0) {
                collision.dx += stopSpeed;
                if (collision.dx > 0) {
                    collision.dx = 0;
                }
            }
        }
        if ((currentAction == action.scratching || currentAction == action.fireball) && !(movement.getJumping() || collision.falling)) {
            collision.dx = 0;
        }
        if (movement.getJumping() && !collision.falling) {
            collision.dy = jumpStart;
            collision.falling = true;
        }
        if (collision.falling) {
            collision.dy += fallSpeed;
            if (collision.dy > 0) {
                movement.setJumping(false);
            }
            if (collision.dy < 0 && !movement.getJumping()) {
                collision.dy += stopJumpSpeed;
            }
            if (collision.dy > maxFallSpeed) {
                collision.dy = maxFallSpeed;
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
                animation.setFrames(character.sprites.get(action.scratching));
                animation.setDelay(50);
                width = 60;
            }
        } else if (character.firing) {
            if (currentAction != action.fireball) {
                currentAction = action.fireball;
                animation.setFrames(character.sprites.get(action.fireball));
                animation.setDelay(100);
                width = 30;
            }
        } else if (collision.dy > 0) {
            if (currentAction != action.falling) {
                currentAction = action.falling;
                animation.setFrames(character.sprites.get(action.falling));
                animation.setDelay(100);
                width = 30;
            }
        } else if (collision.dy < 0) {
            if (currentAction != action.jumping) {
                currentAction = action.jumping;
                animation.setFrames(character.sprites.get(action.jumping));
                animation.setDelay(-1);
                width = 30;
            }
        } else if (movement.getLeft() || movement.getRight()) {
            if (currentAction != action.walking) {
                currentAction = action.walking;
                animation.setFrames(character.sprites.get(action.walking));
                animation.setDelay(40);
                width = 30;
            }
        } else {
            if (currentAction != action.idle) {
                currentAction = action.idle;
                animation.setFrames(character.sprites.get(action.idle));
                animation.setDelay(400);
                width = 30;
            }
        }
        animation.update();
        if (currentAction != action.scratching && currentAction != action.fireball) {
            if (movement.getRight()) {
                facingRight = true;
            }
            if (movement.getLeft()) {
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
            g.drawImage(animation.getImage(), (int) ( collision.characterMapPlacement.x +  collision.characterMapPlacement.xmap - width / 2), (int) ( collision.characterMapPlacement.y +  collision.characterMapPlacement.ymap - height / 2), null);
        } else {
            g.drawImage(animation.getImage(), (int) ( collision.characterMapPlacement.x +  collision.characterMapPlacement.xmap - width / 2 + width), (int) ( collision.characterMapPlacement.y +  collision.characterMapPlacement.ymap - height / 2), -width, height, null);
        }
    }
}