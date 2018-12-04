package ru.stoliarenko.gb.lonelycoraptor.objects.space_objects.ships;

import org.jetbrains.annotations.Nullable;

import ru.stoliarenko.gb.lonelycoraptor.base.Ship;
import ru.stoliarenko.gb.lonelycoraptor.utils.Atlases;
import ru.stoliarenko.gb.lonelycoraptor.utils.ScreenParameters;
import ru.stoliarenko.gb.lonelycoraptor.utils.Sprite;


/**
 * Main ship
 */
public final class Corruptor extends Ship {


    @Nullable private static Corruptor instance;

    private int score = 0;

    private float ACCELERATION = 300;
    private float SPEED_DECAY = 1f;
    private float horizontalAcceleration = 0;
    private float verticalAcceleration = 0;


    private Corruptor(int posX, int posY) {
        super(new Sprite(Atlases.space.findRegion("corruptor"), 1f));
        position.set(posX, posY);
        visible = true;
    }

    public static Corruptor getCorruptor(){
        if (instance == null) {
            final int posX = (int)(ScreenParameters.myScreen.getWidth() / 2);
            final int posY = (int)(ScreenParameters.myScreen.getHeight() / 2);
            instance = new Corruptor(posX, posY);
        }
        return instance;
    }

    @Override
    public void move(float dt){
        checkOuterSpace();
        position.mulAdd(velocity, dt);
        if (velocity.len() >= ACCELERATION/60) rotateTo(velocity.angle(), dt);
        accelerate(dt);
        slowDown(dt);
    }

    private void accelerate(float dt){
        velocity.add(horizontalAcceleration*dt, verticalAcceleration*dt);
    }

    private void slowDown(float dt){
        velocity.scl(1f - SPEED_DECAY*dt);
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

    @Override
    protected boolean checkOuterSpace(){
        if ( (position.x + img.getLeftShift() < 0 && velocity.x < 0) || (position.x + img.getRightShift() > ScreenParameters.myScreen.getWidth() && velocity.x > 0) )
            velocity.scl(-0.35f, 0.6f);

        if ( (position.y + img.getBottomShift() < 0 && velocity.y < 0) || (position.y + img.getTopShift() > ScreenParameters.myScreen.getHeight() && velocity.y > 0) )
            velocity.scl(0.6f, -0.35f);
        return false;
    }

    public void getScore(int score) {
        this.score += score; //TODO score multiplier?
    }

}
