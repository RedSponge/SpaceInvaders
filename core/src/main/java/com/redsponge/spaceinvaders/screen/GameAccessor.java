package com.redsponge.spaceinvaders.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.redsponge.spaceinvaders.transitions.Transition;
import com.redsponge.spaceinvaders.transitions.TransitionScreen;
import com.redsponge.spaceinvaders.utilities.DependencyInjection;


public class GameAccessor {

    private Game game;
    private DependencyInjection di;

    public GameAccessor(Game game, DependencyInjection di) {
        this.game = game;
        this.di = di;
    }

    public void transitionTo(AbstractScreen to, Transition transition, float length) {
        AbstractScreen s = (AbstractScreen) game.getScreen();
        s.beginTransition();
        setScreen(new TransitionScreen(di, s, to, length, this, transition));
    }

    public void setScreen(ScreenAdapter screen) {
        game.setScreen(screen);
    }
}
