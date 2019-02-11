package com.redsponge.spaceinvaders.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Particles implements AssetLoader {

    public ParticleManager enemyDeath;

    public Particles() {
        this.enemyDeath = new ParticleManager("enemyDeath.p");
    }

    @Override
    public void load(AssetManager am) {
        enemyDeath.load(am);
    }

    @Override
    public void getResources(AssetManager am) {
        enemyDeath.getResources(am);
    }

    public void render(float delta, SpriteBatch batch) {
        enemyDeath.render(delta, batch);
    }
}
