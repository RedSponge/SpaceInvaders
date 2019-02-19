package com.redsponge.spaceinvaders;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.redsponge.spaceinvaders.assets.Assets;
import com.redsponge.spaceinvaders.screen.GameAccessor;
import com.redsponge.spaceinvaders.screen.SplashScreenScreen;

public class SpaceInvaders extends Game {

    private Assets assets;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;

    private GameAccessor ga;

    @Override
    public void create() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        assets = new Assets();

        ga = new GameAccessor(this);

        setScreen(new SplashScreenScreen(ga));
    }

    public Assets getAssets() {
        return assets;
    }

    public SpriteBatch getSpriteBatch() {
        return batch;
    }

    public ShapeRenderer getShapeRenderer() {
        return shapeRenderer;
    }

    @Override
    public void render() {
        assets.update();
        super.render();
    }

    @Override
    public void dispose() {
        assets.dispose();
        shapeRenderer.dispose();
        batch.dispose();
    }
}