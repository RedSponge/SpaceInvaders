package com.redsponge.spaceinvaders.screen;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.redsponge.spaceinvaders.utilities.DependencyInjection;

import javax.inject.Inject;

public class ScreenTemplate extends ScreenAdapter {

    @Inject
    protected SpriteBatch batch;

    @Inject
    protected ShapeRenderer shapeRenderer;

    public ScreenTemplate(DependencyInjection di) {
        di.injectFields(this);
    }
}
