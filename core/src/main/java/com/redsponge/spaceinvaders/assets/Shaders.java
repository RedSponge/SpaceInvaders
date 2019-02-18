package com.redsponge.spaceinvaders.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.Disposable;

public class Shaders implements AssetLoader, Disposable {

    public ShaderProgram opacityModify;

    @Override
    public void load(AssetManager am) {
        ShaderProgram.pedantic = false;
    }

    @Override
    public void getResources(AssetManager am) {
        opacityModify = new ShaderProgram(Gdx.files.internal("shaders/passthrough.vert"), Gdx.files.internal("shaders/opacity.frag"));
    }

    @Override
    public void dispose() {
        opacityModify.dispose();
    }
}
