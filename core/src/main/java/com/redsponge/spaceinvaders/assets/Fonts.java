package com.redsponge.spaceinvaders.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Disposable;

public class Fonts implements AssetLoader, Disposable {

    public BitmapFont scoreFont;

    @Override
    public void load(AssetManager am) {
        scoreFont = new BitmapFont();
    }

    @Override
    public void getResources(AssetManager am) {

    }

    @Override
    public void dispose() {
        scoreFont.dispose();
    }
}
