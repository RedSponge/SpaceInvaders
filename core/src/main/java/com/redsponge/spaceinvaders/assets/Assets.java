package com.redsponge.spaceinvaders.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.utils.Disposable;

public class Assets implements Disposable {

    private AssetManager am;
    private Sounds sounds;
    private Particles particles;
    private Textures textures;
    private Fonts fonts;
    private Skins skins;
    private Shaders shaders;

    public Assets() {
        am = new AssetManager();
        particles = new Particles();
        sounds = new Sounds();
        textures = new Textures();
        fonts = new Fonts();
        skins = new Skins();
        shaders = new Shaders();

        sounds.load(am);
        particles.load(am);
        textures.load(am);
        fonts.load(am);
        skins.load(am);
        shaders.load(am);
    }

    public void getResources() {
        Gdx.app.log("Assets", "Loading!");

        am.finishLoading();

        sounds.getResources(am);
        particles.getResources(am);
        textures.getResources(am);
        fonts.getResources(am);
        skins.getResources(am);
        shaders.getResources(am);
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

    public Skins getSkins() {
        return skins;
    }

    public Shaders getShaders() {
        return shaders;
    }

    @Override
    public void dispose() {
        am.dispose();
        fonts.dispose();
        shaders.dispose();
    }

    public void finishLoading() {
        am.finishLoading();
    }

    public void update() {
        am.update();
    }
}
