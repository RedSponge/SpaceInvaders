package com.redsponge.spaceinvaders.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.redsponge.spaceinvaders.utilities.Constants;

public class Textures implements AssetLoader {

    public Animation<TextureRegion> spaceShipAnimation;
    public TextureRegion sky;

    @Override
    public void load(AssetManager am) {
        Gdx.app.debug("Textures", "Loading Textures!");
        am.load("sprites/myatlas.atlas", TextureAtlas.class);
    }

    @Override
    public void getResources(AssetManager am) {
        Gdx.app.debug("Textures", "Retrieving Textures!");
        TextureAtlas atlas = am.get("sprites/myatlas.atlas");

        Array<TextureRegion> frames = new Array<>();
        for(int i = 0; i < Constants.PLAYER_ANIMATION_FRAMES; i++) {
            frames.add(atlas.findRegion("spaceship" + (i + 1)));
        }

        spaceShipAnimation = new Animation<TextureRegion>(Constants.PLAYER_ANIMATION_FRAME_DURATION, frames, PlayMode.LOOP);
        sky = atlas.findRegion("sky");
    }
}
