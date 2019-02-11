package com.redsponge.spaceinvaders.utilities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Disposable;
import com.redsponge.spaceinvaders.assets.Assets;
import org.codejargon.feather.Feather;

import javax.inject.Singleton;

public class DependencyInjection implements Disposable {

    private Feather feather;
    private SpriteBatch spriteBatch;
    private ShapeRenderer shapeRenderer;
    private Assets assets;

    public DependencyInjection() {
        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        assets = new Assets();

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

    @Singleton
    private Assets getAssets() {
        return assets;
    }

    public void injectFields(Object target) {
        feather.injectFields(target);
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        shapeRenderer.dispose();
        assets.dispose();
    }
}
