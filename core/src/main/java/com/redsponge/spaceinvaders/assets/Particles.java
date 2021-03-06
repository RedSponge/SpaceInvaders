package com.redsponge.spaceinvaders.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Particles implements AssetLoader {

    public ParticleManager enemyDeath;
    public ParticleManager star;
    public ParticleManager score_100;

    public Particles() {
        this.enemyDeath = new ParticleManager("enemyDeath.p");
        this.star = new ParticleManager("starParticles.p");
        this.score_100 = new ParticleManager("100_score.p");
    }

    @Override
    public void load(AssetManager am) {
        Gdx.app.log("Particles", "Loading Particles");
        am.load("particles/particle_atlas.atlas", TextureAtlas.class);
    }

    @Override
    public void getResources(AssetManager am) {
        Gdx.app.log("Particles", "Retrieving Particles");
        TextureAtlas atlas = am.get("particles/particle_atlas.atlas");
        this.enemyDeath.getResources(atlas);
        this.star.getResources(atlas);
        this.score_100.getResources(atlas);
    }

    public void render(float delta, SpriteBatch batch) {
        this.enemyDeath.render(delta, batch);
        this.star.render(delta, batch);
        this.score_100.render(delta, batch);
    }
}
