package com.redsponge.spaceinvaders.texturepacker;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class TexturePackerRunner {

    public static void main(String[] args) {
        TexturePacker.processIfModified("raw_assets/sprites", "../assets/sprites/", "sprite_atlas");
        TexturePacker.processIfModified("raw_assets/particles", "../assets/particles/", "particle_atlas");
    }

}
