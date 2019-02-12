package com.redsponge.spaceinvaders.texturepacker;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class TexturePackerRunner {

    public static void main(String[] args) {
        TexturePacker.processIfModified("raw_assets", "../assets/sprites/", "myatlas");
    }

}
