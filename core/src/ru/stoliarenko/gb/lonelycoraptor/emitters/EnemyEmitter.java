package ru.stoliarenko.gb.lonelycoraptor.emitters;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import ru.stoliarenko.gb.lonelycoraptor.emitters.slave.SimpleEnemyEmitter;
import ru.stoliarenko.gb.lonelycoraptor.objects.space_objects.ships.Ship;
import ru.stoliarenko.gb.lonelycoraptor.objects.space_objects.ships.SimpleEnemy;
import ru.stoliarenko.gb.lonelycoraptor.screen.GameScreen;

public final class EnemyEmitter {

    private GameScreen gs;
    @Getter private SimpleEnemyEmitter simpleEnemies;

    private List<Ship> activeList;

    private float nextWaveTimer = 0;

    public EnemyEmitter(GameScreen gs) {
        this.gs = gs;
        this.activeList = new ArrayList<>();
        this.simpleEnemies = new SimpleEnemyEmitter(gs);
    }

    public List<Ship> getActiveList() {
        activeList.clear();
        for (int i = 0; i < simpleEnemies.getActiveList().size(); i++) {
            activeList.add(simpleEnemies.getActiveList().get(i));
        }
        return activeList;
    }

    public void move(float dt) {
        simpleEnemies.move(dt);
        checkTimer(dt);
    }

    public void draw(SpriteBatch batch) {
        simpleEnemies.draw(batch);
    }

    private void checkTimer(float dt) {
        nextWaveTimer -= dt;
        if (nextWaveTimer <= 0) {
            spawnRandomWave();
            nextWaveTimer = 7f;
        }
    }

    private void spawnRandomWave() {
        final int waveId = MathUtils.random(1);
        final int score = gs.getPlayer().getScore();
        switch (waveId) {
            case 0: {
                if (score < 500) spawnRandomLeftgoers(SimpleEnemy.Type.GREEN, 1 + score/100);
                else if (score < 1000) spawnRandomLeftgoers(SimpleEnemy.Type.BLUE, 1 + score%500/100);
                else spawnRandomLeftgoers(SimpleEnemy.Type.YELLOW, Math.min(6, 2 + (score - 1000) / 200));
                break;
            }
            case 1: {
                spawnCrossCascade(SimpleEnemy.Type.REGULAR, Math.min(6, 1 + gs.getPlayer().getScore()/100));
                break;
            }
        }
    }

    private void spawnCrossCascade(SimpleEnemy.Type type, int number) {
        for (int i = 0; i < number; i++) {
            simpleEnemies.spawnAngled(type, true, i*2, i*2);
            simpleEnemies.spawnAngled(type, false, i*2+1, i*2+1);
        }
    }

    private void spawnRandomLeftgoers(SimpleEnemy.Type type, int number) {
        for (int i = 0; i < number; i++) {
            simpleEnemies.spawnSimple(type, true, true, MathUtils.random(1f), i, 0);
        }
    }


}
