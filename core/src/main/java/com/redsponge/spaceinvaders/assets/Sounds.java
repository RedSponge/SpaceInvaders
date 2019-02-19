package com.redsponge.spaceinvaders.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;

public class Sounds implements AssetLoader {

    private AssetManager am;

    public Sound shoot;
    public Sound enemyHit;
    public Sound reachGoodScore;

    @Override
    public void load(AssetManager am) {
        Gdx.app.log("Sounds", "Loading Sounds!");

        this.am = am;
        this.am.load("sounds/shoot.wav", Sound.class);
        this.am.load("sounds/hit.wav", Sound.class);
        this.am.load("sounds/reach_good_score.wav", Sound.class);
    }

    @Override
    public void getResources(AssetManager am) {
        Gdx.app.log("Sounds", "Retrieving Sounds!");

        shoot = am.get("sounds/shoot.wav", Sound.class);
        enemyHit = am.get("sounds/hit.wav", Sound.class);
        reachGoodScore = am.get("sounds/reach_good_score.wav", Sound.class);
    }
}
