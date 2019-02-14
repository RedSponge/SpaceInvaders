package com.redsponge.spaceinvaders;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.redsponge.spaceinvaders.screen.GameScreen;
import com.redsponge.spaceinvaders.screen.MenuScreen;
import com.redsponge.spaceinvaders.utilities.DependencyInjection;

import java.lang.reflect.InvocationTargetException;

public class SpaceInvaders extends Game {

    private DependencyInjection di;
    private static SpaceInvaders instance;

    public static void transitionToScreen(Class<? extends Screen> screen) {
        try {
            Screen s = screen.getConstructor(DependencyInjection.class).newInstance(instance.di);
            instance.setScreen(s);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create() {
        instance = this;


        di = new DependencyInjection();
        setScreen(new MenuScreen(di));
    }

    @Override
    public void dispose() {
        di.dispose();
    }
}