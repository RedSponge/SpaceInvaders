package com.redsponge.spaceinvaders.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.redsponge.spaceinvaders.splashscreen.SplashScreenRenderer;
import com.redsponge.spaceinvaders.transitions.TransitionFade;
import com.redsponge.spaceinvaders.utilities.DependencyInjection;

public class SplashScreenScreen extends AbstractScreen {
    private SplashScreenRenderer splashScreenRenderer;
    private ScalingViewport scalingViewport;
    private boolean didTransition;

    public SplashScreenScreen(DependencyInjection di, GameAccessor ga) {
        super(di, ga);
    }

    @Override
    public void show() {
        this.scalingViewport = new ScalingViewport(Scaling.fill, 1, 1);
        splashScreenRenderer = new SplashScreenRenderer(batch);
        splashScreenRenderer.begin();
        didTransition = true;
    }

    @Override
    public void tick(float delta) {
        splashScreenRenderer.tick(delta);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.8f, 0.25f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(!splashScreenRenderer.isComplete()) {
            splashScreenRenderer.render();
        } else if(!transitioning) {
            ga.transitionTo(new MenuScreen(di, ga), new TransitionFade(), 2);
        }
    }

    @Override
    public void resize(int width, int height) {
        splashScreenRenderer.resize(width, height);
        scalingViewport.update(width, height, true);
    }


    @Override
    public void dispose() {
        splashScreenRenderer.dispose();
    }
}