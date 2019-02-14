package com.redsponge.spaceinvaders.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Skins implements AssetLoader {

    public Skin neon;
    public Skin quantum;

    @Override
    public void load(AssetManager am) {
        am.load("skins/neonui/neon-ui.json", Skin.class);
        am.load("skins/quantumhorizonui/quantum-horizon-ui.json", Skin.class);
    }

    @Override
    public void getResources(AssetManager am) {
        neon = am.get("skins/neonui/neon-ui.json", Skin.class);
        quantum = am.get("skins/quantumhorizonui/quantum-horizon-ui.json", Skin.class);
    }
}
