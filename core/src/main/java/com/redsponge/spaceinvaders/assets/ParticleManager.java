package com.redsponge.spaceinvaders.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.ParticleEffectLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool.PooledEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.particles.ParticleEffectLoader.ParticleEffectLoadParameter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.redsponge.spaceinvaders.assets.AssetLoader;

public class ParticleManager implements AssetLoader {

    private ParticleEffectPool pool;
    private String name;
    private DelayedRemovalArray<PooledEffect> spawned;

    public ParticleManager(String name) {
        this.name = name;
        spawned = new DelayedRemovalArray<>();
    }

    @Override
    public void load(AssetManager am) {
        am.load("particles/" + name, ParticleEffect.class);
    }

    @Override
    public void getResources(AssetManager am) {
        ParticleEffect effect = am.get("particles/" + name, ParticleEffect.class);
        pool = new ParticleEffectPool(effect, 3, 10);
    }

    public void render(float delta, SpriteBatch batch) {
        for(PooledEffect p : spawned) {
            p.update(delta);
            p.draw(batch);
            if(p.isComplete()) {
                p.free();
                spawned.removeValue(p, true);
            }
        }
    }

    public void spawn(Vector2 pos) {
        PooledEffect p = pool.obtain();
        p.setPosition(pos.x,pos.y);
        p.start();
        spawned.add(p);
    }
}
