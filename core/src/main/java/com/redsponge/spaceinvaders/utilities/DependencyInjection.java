package com.redsponge.spaceinvaders.utilities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Disposable;
import org.codejargon.feather.Feather;

import javax.inject.Singleton;

public class DependencyInjection implements Disposable {

    private Feather feather;
    private SpriteBatch spriteBatch;
    private ShapeRenderer shapeRenderer;

    public DependencyInjection() {
        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        feather = Feather.with(this);
    }

    @Singleton
    private SpriteBatch spriteBatch() {
        return spriteBatch;
    }

    @Singleton
    private ShapeRenderer shapeRenderer() {
        return shapeRenderer;
    }

    public void injectFields(Object target) {
        feather.injectFields(target);
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        shapeRenderer.dispose();
    }
}
