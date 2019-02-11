package com.redsponge.spaceinvaders;

import com.badlogic.gdx.Game;
import com.redsponge.spaceinvaders.screen.GameScreen;
import com.redsponge.spaceinvaders.utilities.DependencyInjection;

public class SpaceInvaders extends Game {

    private DependencyInjection di;

    @Override
    public void create() {
        di = new DependencyInjection();
        setScreen(new GameScreen(di));
    }

    @Override
    public void dispose() {
        di.dispose();
    }
}