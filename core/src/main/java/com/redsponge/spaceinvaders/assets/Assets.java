package com.redsponge.spaceinvaders.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.utils.Disposable;

public class Assets implements Disposable {

    private AssetManager am;
    private Sounds sounds;
    private Particles particles;
    private Textures textures;

    public Assets() {
        am = new AssetManager();
        particles = new Particles();
        sounds = new Sounds();
        textures = new Textures();

        sounds.load(am);
        particles.load(am);
        textures.load(am);

        am.finishLoading();

        sounds.getResources(am);
        particles.getResources(am);
        textures.getResources(am);
    }

    public Sounds getSounds() {
        return sounds;
    }

    public Particles getParticles() {
        return particles;
    }

    public Textures getTextures() {
        return textures;
    }

    @Override
    public void dispose() {
        am.dispose();
    }
}
