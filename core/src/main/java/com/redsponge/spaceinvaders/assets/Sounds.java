package com.redsponge.spaceinvaders.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;

public class Sounds implements AssetLoader {

    private AssetManager am;

    public Sound shoot;
    public Sound enemyHit;

    @Override
    public void load(AssetManager am) {
        this.am = am;
        this.am.load("sounds/shoot.wav", Sound.class);
        this.am.load("sounds/hit.wav", Sound.class);
    }

    @Override
    public void getResources(AssetManager am) {
        shoot = am.get("sounds/shoot.wav", Sound.class);
        enemyHit= am.get("sounds/hit.wav");
    }
}
