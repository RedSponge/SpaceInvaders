package com.redsponge.spaceinvaders.screen;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.redsponge.spaceinvaders.assets.Assets;
import com.redsponge.spaceinvaders.utilities.DependencyInjection;
import javax.inject.Inject;

public abstract class AbstractScreen extends ScreenAdapter {

    @Inject
    protected ShapeRenderer shapeRenderer;
    @Inject
    protected SpriteBatch batch;
    @Inject
    protected Assets assets;

    protected DependencyInjection di;
    protected GameAccessor ga;
    protected boolean transitioning;

    public AbstractScreen(DependencyInjection di, GameAccessor ga) {
        this.ga = ga;
        di.injectFields(this);
        this.di = di;
    }

    @Override
    public void show() {
        transitioning = false;
    }

    @Override
    public final void render(float delta) {
        tick(delta);
        render();
    }

    public abstract void tick(float delta);
    public abstract void render();

    public void beginTransition() {
        transitioning = true;
    }
}
