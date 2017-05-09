package entities;

import core.Character;
import core.CharacterAction;
import core.CharacterActionBuilder;
import tilemaps.TileMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Berserker extends MapObject {
    private Character character = new Character(5, 5, 2500, 2500, false, true, 0, false, 200, 5, false, 8, 40, false, new ArrayList<>());
    private CharacterAction action = new CharacterActionBuilder().buildAnimations();

    public Berserker(TileMap tm) {
        super(tm);
        width = 30;
        height = 30;
        cwidth = 20;
        cheight = 20;
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
        if (left) {
            dx -= moveSpeed;
            if (dx < -maxSpeed) {
                dx = -maxSpeed;
            }
        } else if (right) {
            dx += moveSpeed;
            if (dx > maxSpeed) {
                dx = maxSpeed;
            }
        } else {
            if (dx > 0) {
                dx -= stopSpeed;
                if (dx < 0) {
                    dx = 0;
                }
            } else if (dx < 0) {
                dx += stopSpeed;
                if (dx > 0) {
                    dx = 0;
                }
            }
        }
        if ((currentAction == action.scratching || currentAction == action.fireball) && !(jumping || falling)) {
            dx = 0;
        }
        if (jumping && !falling) {
            dy = jumpStart;
            falling = true;
        }
        if (falling) {
            if (dy > 0 && character.gliding) {
                dy += fallSpeed * 0.1;
            }
            dy += fallSpeed;
            if (dy > 0) {
                jumping = false;
            }
            if (dy < 0 && !jumping) {
                dy += stopJumpSpeed;
            }
            if (dy > maxFallSpeed) {
                dy = maxFallSpeed;
            }
        }
    }

    public void update() {
        getNextPosition();
        checkTileMapCollision();
        setPosition(xtemp, ytemp);
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
        } else if (dy > 0) {
            if (character.gliding) {
                if (currentAction != action.gliding) {
                    currentAction = action.gliding;
                    animation.setFrames(character.sprites.get(action.gliding));
                    animation.setDelay(100);
                    width = 30;
                }
            } else if (currentAction != action.falling) {
                currentAction = action.falling;
                animation.setFrames(character.sprites.get(action.falling));
                animation.setDelay(100);
                width = 30;
            }
        } else if (dy < 0) {
            if (currentAction != action.jumping) {
                currentAction = action.jumping;
                animation.setFrames(character.sprites.get(action.jumping));
                animation.setDelay(-1);
                width = 30;
            }
        } else if (left || right) {
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
            if (right) {
                facingRight = true;
            }
            if (left) {
                facingRight = false;
            }
        }
    }

    public void draw(Graphics g) {
        setMapPosition();
        if (character.flinching) {
            long elapsed = (System.nanoTime() - character.flinchTimer) / 1000000;
            if (elapsed / 100 % 2 == 0) {
                return;
            }
        }
        if (facingRight) {
            g.drawImage(animation.getImage(), (int) (x + xmap - width / 2), (int) (y + ymap - height / 2), null);
        } else {
            g.drawImage(animation.getImage(), (int) (x + xmap - width / 2 + width), (int) (y + ymap - height / 2), -width, height, null);
        }
    }
}