package ru.stoliarenko.gb.lonelycoraptor.emitters.pool;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public abstract class ObjectPool<T extends Poolable> {
    protected List<T> activeList;
    protected List<T> freeList;

    public ObjectPool() {
        activeList = new ArrayList<>();
        freeList = new ArrayList<>();
    }

    public void addObjectsToFreeList(int number) {
        for (int i = 0; i < number; i++) {
            freeList.add(newObject());
        }
    }

    public List<T> getActiveList() { return activeList; }

    public List<T> getFreeList() { return freeList; }

    private void free(int index) { freeList.add(activeList.remove(index)); }

    protected abstract T newObject();

    public T getActiveElement() {
        if (freeList.isEmpty()) freeList.add(newObject());
        T temp = freeList.remove(freeList.size() - 1);
        activeList.add(temp);
        return temp;
    }

    public void checkPool() {
        for (int i = activeList.size() - 1; i >= 0; i--) {
            if (!activeList.get(i).isActive()) free(i);
        }
    }

    public void draw(@NotNull final SpriteBatch batch) {
        for (int i = 0; i < activeList.size(); i++) {
            activeList.get(i).draw(batch);
        }
    }

    public void move(float dt) {
        for (int i = 0; i < activeList.size(); i++) {
            activeList.get(i).move(dt);
        }
        checkPool();
    }
}
