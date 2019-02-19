package com.redsponge.spaceinvaders.screen;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.redsponge.spaceinvaders.SpaceInvaders;
import com.redsponge.spaceinvaders.assets.Assets;
import com.redsponge.spaceinvaders.transitions.Transition;
import com.redsponge.spaceinvaders.transitions.TransitionScreen;


public class GameAccessor {

    private SpaceInvaders game;

    public GameAccessor(SpaceInvaders game) {
        this.game = game;
    }

    public void transitionTo(AbstractScreen to, Transition transition, float length) {
        AbstractScreen s = (AbstractScreen) game.getScreen();
        s.beginTransition();
        setScreen(new TransitionScreen(s, to, length, this, transition));
    }

    public void setScreen(ScreenAdapter screen) {
        game.setScreen(screen);
    }

    public SpriteBatch getSpriteBatch() {
        return game.getSpriteBatch();
    }

    public ShapeRenderer getShapeRenderer() {
        return game.getShapeRenderer();
    }

    public Assets getAssets() {
        return game.getAssets();
    }
}
