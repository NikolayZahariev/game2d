package entities;

import tilemaps.TileMap;

import java.util.ArrayList;

/**
 * @author Nikolay Zahariev <nikolay.g.zahariev@gmail.com>.
 */
public class Enemy {
    protected int health;
    protected int maxHealth;
    protected boolean dead;
    protected int damage;

    public boolean flinching;
    public long flinchTimer;

    public Enemy() {}

    public boolean isDead() { return dead; }

    public int getDamage() { return damage; }

    public void hit(int damage) {
        if(dead || flinching) return;
        health -= damage;
        if(health < 0) health = 0;
        if(health == 0) dead = true;
        flinching = true;
        flinchTimer = System.nanoTime();
    }
}
