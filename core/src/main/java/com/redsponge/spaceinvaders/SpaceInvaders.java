package com.redsponge.spaceinvaders;

import com.badlogic.gdx.Game;
import com.redsponge.spaceinvaders.screen.GameAccessor;
import com.redsponge.spaceinvaders.screen.GameScreen;
import com.redsponge.spaceinvaders.screen.SplashScreenScreen;
import com.redsponge.spaceinvaders.utilities.DependencyInjection;

public class SpaceInvaders extends Game {

    private DependencyInjection di;
    private GameAccessor ga;

    @Override
    public void create() {
        di = new DependencyInjection();
        ga = new GameAccessor(this, di);

        setScreen(new SplashScreenScreen(di, ga));
    }

    @Override
    public void dispose() {
        di.dispose();
    }
}