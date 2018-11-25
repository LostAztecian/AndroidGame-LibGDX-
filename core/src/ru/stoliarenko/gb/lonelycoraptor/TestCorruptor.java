package ru.stoliarenko.gb.lonelycoraptor;

import com.badlogic.gdx.Game;

import ru.stoliarenko.gb.lonelycoraptor.screen.MainScreen2D;

public class TestCorruptor extends Game {
    @Override
    public void create() {
        setScreen(new MainScreen2D());
    }
}
