package ru.stoliarenko.gb.lonelycoraptor.emitters.pool;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Poolable {
    boolean isActive();
    void destroy();
    void draw(SpriteBatch batch);
    void move(float dt);
}
