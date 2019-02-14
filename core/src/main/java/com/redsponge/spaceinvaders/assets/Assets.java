package com.redsponge.spaceinvaders.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.utils.Disposable;

public class Assets implements Disposable {

    private AssetManager am;
    private Sounds sounds;
    private Particles particles;
    private Textures textures;
    private Fonts fonts;

    public Assets() {
        am = new AssetManager();
        particles = new Particles();
        sounds = new Sounds();
        textures = new Textures();
        fonts = new Fonts();

        sounds.load(am);
        particles.load(am);
        textures.load(am);
        fonts.load(am);

        am.finishLoading();

        sounds.getResources(am);
        particles.getResources(am);
        textures.getResources(am);
        fonts.getResources(am);
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

    public Fonts getFonts() {
        return fonts;
    }

    @Override
    public void dispose() {
        am.dispose();
        fonts.dispose();
    }
}
