package com.redsponge.spaceinvaders.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.redsponge.spaceinvaders.utilities.DependencyInjection;

public class OtherScreen extends AbstractScreen {

    public OtherScreen(DependencyInjection di, GameAccessor ga) {
        super(di, ga);
    }

    @Override
    public void tick(float delta) {

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}
