package ru.stoliarenko.gb.lonelycoraptor.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import ru.stoliarenko.gb.lonelycoraptor.base.Projectile;
import ru.stoliarenko.gb.lonelycoraptor.base.Ship;
import ru.stoliarenko.gb.lonelycoraptor.screen.MainScreen2D;

public final class Corruptor extends Ship {
    private static Corruptor instance;
    private final List<Projectile> projectiles = new LinkedList<Projectile>();
    private final Queue<Projectile> expiredProjectiles = new LinkedList<Projectile>();
    private static final float ACCELERATION = 18 /60f;
    private static final float SPEED_DECAY = 0.97f;
    private static final Vector2 POSITION_DELTA = new Vector2(18, 22);

    private float horizontalAcceleration = 0;
    private float verticalAcceleration = 0;

    private Vector2 movement = new Vector2(0, 0);
    private Vector2 position;
    private Texture pic = new Texture("corruptor_small.png");
    private Sprite sprite = new Sprite(pic);

    private Corruptor(int posX, int posY) {
        position = new Vector2(posX, posY);
    }

    public static Corruptor getCorruptor(){
        if (instance == null) {
            int posX = (int)(MainScreen2D.currentWidth / 2);
            int posY = (int)(MainScreen2D.currentHeight / 2);
            instance = new Corruptor(posX, posY);
            instance.sprite.setOrigin(POSITION_DELTA.x, POSITION_DELTA.y);
        }
        return instance;
    }

    public Vector2 getPosition(){
        return position;
    }

    public void draw(SpriteBatch batch){
        sprite.setOriginBasedPosition(position.x, position.y);
        drawProjectiles(batch);
        sprite.draw(batch);
        checkCollision();
        move();
    }

    private void drawProjectiles(SpriteBatch batch) {
        synchronized (this.projectiles) {
            projectiles.removeAll(expiredProjectiles);
            for (Projectile p : projectiles) {
                p.draw(batch);
            }
        }
    }

    private void move(){
        position.add(movement);
        if (movement.len() >= ACCELERATION) sprite.setRotation(movement.angle() - 90);
        accelerate();
        slowDown();
    }

    private void accelerate(){
        movement.add(horizontalAcceleration, verticalAcceleration);
    }

    private void slowDown(){
        movement.scl(SPEED_DECAY);
    }

    public void accelerateUp(){
        verticalAcceleration += ACCELERATION;
    }
    public void accelerateDown(){
        verticalAcceleration -= ACCELERATION;
    }
    public void accelerateRight(){
        horizontalAcceleration += ACCELERATION;
    }
    public void accelerateLeft(){
        horizontalAcceleration -= ACCELERATION;
    }

    private void checkCollision(){
        if (position.x < POSITION_DELTA.x || position.x > MainScreen2D.currentWidth - POSITION_DELTA.x)
            movement.scl(-1.1f, 0.6f);
        if (position.y < POSITION_DELTA.y || position.y > MainScreen2D.currentHeight - POSITION_DELTA.y)
            movement.scl(0.6f, -1.1f);
    }

    public void launchBile(int posX, int posY) {
        synchronized (this.projectiles) {
            projectiles.add(new CorrosiveBile(position, posX, posY));
        }
    }

    public void removeProjectile(Projectile projectile) {
        expiredProjectiles.add(projectile);
    }
}
