package com.redsponge.spaceinvaders.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Particles implements AssetLoader {

    public ParticleManager enemyDeath;
    public ParticleManager star;

    public Particles() {
        this.enemyDeath = new ParticleManager("enemyDeath.p");
        this.star = new ParticleManager("starParticles.p");
    }

    @Override
    public void load(AssetManager am) {
        Gdx.app.log("Particles", "Loading Particles");
        this.enemyDeath.load(am);
        this.star.load(am);
    }

    @Override
    public void getResources(AssetManager am) {
        Gdx.app.log("Particles", "Retrieving Particles");
        this.enemyDeath.getResources(am);
        this.star.getResources(am);
    }

    public void render(float delta, SpriteBatch batch) {
        this.enemyDeath.render(delta, batch);
        this.star.render(delta, batch);
    }
}
