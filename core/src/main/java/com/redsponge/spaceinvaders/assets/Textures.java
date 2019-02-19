package com.redsponge.spaceinvaders.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.redsponge.spaceinvaders.game.Enemy;
import com.redsponge.spaceinvaders.utilities.Constants;

public class Textures implements AssetLoader {

    public Animation<TextureRegion> spaceShipAnimation;
    public Animation<TextureRegion> enemyAnimation;
    public TextureRegion sky;
    public TextureRegion bullet;
    public TextureRegion title;

    @Override
    public void load(AssetManager am) {
        Gdx.app.log("Textures", "Loading Textures!");
        am.load("sprites/sprite_atlas.atlas", TextureAtlas.class);
    }

    @Override
    public void getResources(AssetManager am) {
        Gdx.app.log("Textures", "Retrieving Textures!");
        TextureAtlas atlas = am.get("sprites/sprite_atlas.atlas");

        Array<TextureRegion> frames = new Array<>();
        for(int i = 0; i < Constants.PLAYER_ANIMATION_FRAMES; i++) {
            frames.add(atlas.findRegion("spaceship" + (i + 1)));
        }
        spaceShipAnimation = new Animation<TextureRegion>(Constants.PLAYER_ANIMATION_FRAME_DURATION, frames, PlayMode.LOOP);

        Array<TextureRegion> enemyFrames = new Array<>();
        for(int i = 0; i < Constants.ENEMY_ANIMATION_FRAMES; i++) {
            enemyFrames.add(atlas.findRegion("enemy" + (i + 1)));
        }
        enemyAnimation = new Animation<TextureRegion>(Constants.ENEMY_ANIMATION_FRAME_DURATION, enemyFrames, PlayMode.LOOP);

        sky = atlas.findRegion("sky");
        bullet = atlas.findRegion("bullet");
        title = atlas.findRegion("title");
    }
}
