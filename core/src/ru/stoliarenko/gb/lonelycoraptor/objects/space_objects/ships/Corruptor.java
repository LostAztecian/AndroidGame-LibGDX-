package ru.stoliarenko.gb.lonelycoraptor.objects.space_objects.ships;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import lombok.Getter;
import ru.stoliarenko.gb.lonelycoraptor.base.Ship;
import ru.stoliarenko.gb.lonelycoraptor.objects.ShipWeapon;
import ru.stoliarenko.gb.lonelycoraptor.screen.MainScreen2D;
import ru.stoliarenko.gb.lonelycoraptor.utils.Assets;
import ru.stoliarenko.gb.lonelycoraptor.utils.ScreenParameters;
import ru.stoliarenko.gb.lonelycoraptor.utils.Sprite;


/**
 * Main ship
 */
public final class Corruptor extends Ship {

    private final Sprite healed;
    private final Sprite scored;

    @Getter private int score = 0;

    private Vector2 sliderAcceleration = new Vector2(0, 0);
    private float acceleration = 300;
    private float speedDecay = 1f;
    private float horizontalAcceleration = 0;
    private float verticalAcceleration = 0;

    private boolean isChargingWeapon = false;
    private Sound chargindSound = Assets.getInstance().getAssetManager().get("sounds/charging.mp3", Sound.class);


    public Corruptor(@NotNull final MainScreen2D gs) {
        super(new Sprite(Assets.getInstance().getSpaceAtlas().findRegion("corruptor"), 1f), gs);
        damaged = new Sprite(Assets.getInstance().getSpaceAtlas().findRegion("corruptorDamaged"), 1f);
        healed = new Sprite(Assets.getInstance().getSpaceAtlas().findRegion("corruptorHealed"), 1f);
        scored = new Sprite(Assets.getInstance().getSpaceAtlas().findRegion("corruptorScored"), 1f);
        regular = img;

        final int posX = (int)(ScreenParameters.myScreen.getWidth() / 2);
        final int posY = (int)(ScreenParameters.myScreen.getHeight() / 2);
        position.set(posX, posY);

        weapon = new ShipWeapon(gs, ShipWeapon.Type.CORROSIVE_BILE_LAUNCHER);
        visible = true;
    }

    @Override
    public void move(float dt){
        checkOuterSpace();
        position.mulAdd(velocity, dt);
        if (velocity.len() >= acceleration /60) rotateTo(velocity.angle(), dt);
        checkCollisions();

        checkDamagedTimer(dt);

        accelerate(dt);
        slowDown(dt);

        if (isChargingWeapon) chargeWeapon(dt);
    }

    private void accelerate(float dt){
        if (!sliderAcceleration.isZero()) {
            velocity.mulAdd(sliderAcceleration, acceleration*dt);
            return;
        }
        velocity.add(horizontalAcceleration*dt, verticalAcceleration*dt);
    }

    /**
     * Acceleration with slider or mouse
     * @param acceleration maximum length is 1f;
     */
    public void setAcceleration(@NotNull final Vector2 acceleration) {
        sliderAcceleration.set(acceleration);
    }

    private void slowDown(float dt){
        velocity.scl(1f - speedDecay *dt);
    }

    public void accelerateUp(){
        verticalAcceleration += acceleration;
    }
    public void accelerateDown(){ verticalAcceleration -= acceleration; }
    public void accelerateRight(){
        horizontalAcceleration += acceleration;
    }
    public void accelerateLeft(){
        horizontalAcceleration -= acceleration;
    }

    @Override
    protected boolean checkOuterSpace(){
        if ( (position.x + img.getLeftShift() < 0 && velocity.x < 0) || (position.x + img.getRightShift() > ScreenParameters.myScreen.getWidth() && velocity.x > 0) )
            velocity.scl(-0.35f, 0.6f);

        if ( (position.y + img.getBottomShift() < 0 && velocity.y < 0) || (position.y + img.getTopShift() > ScreenParameters.myScreen.getHeight() && velocity.y > 0) )
            velocity.scl(0.6f, -0.35f);
        return false;
    }

    private void checkCollisions() {
        final List<SimpleEnemy> enemyList = gs.getSimpleEnemyEmitter().getActiveList();
        for (int i = 0; i < enemyList.size(); i++) {
            if (checkCollision(enemyList.get(i))) {
                takeDamage(10 * enemyList.get(i).getCollisionDamageMultiplier());
                enemyList.get(i).takeDamage(10 * getCollisionDamageMultiplier());
            }
        }
    }

    public float setWeaponCharging(boolean flag) {
        isChargingWeapon = flag;
        if (flag) chargindSound.play(0.5f); //unsafe
        else chargindSound.stop();
        return 0; //TODO return total charge time
    }

    public void shoot() {
        setWeaponCharging(false);
        temp.set(1, 0).rotate(angle);
        shoot(temp.x, temp.y);
    }

    public void getScore(int score) {
        this.score += score; //TODO score multiplier?
    }

    @Override
    protected void destroy() {
        gs.getGame().mainMenu("Game Over!");
    }

}
