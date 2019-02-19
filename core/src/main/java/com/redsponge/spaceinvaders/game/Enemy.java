package com.redsponge.spaceinvaders.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.redsponge.spaceinvaders.assets.Assets;
import com.redsponge.spaceinvaders.utilities.Constants;

public class Enemy extends Entity {

    private long startTime;
    private Assets assets;
    private float counter;
    private float scale;

    public Enemy(Vector2 position, Assets assets) {
        this(position, assets, 1);
    }

    public Enemy(Vector2 position, Assets assets, float scale) {
        super(position, Constants.ENEMY_WIDTH, Constants.ENEMY_HEIGHT);
        this.assets = assets;
        this.scale = scale;
        startTime = TimeUtils.nanoTime();
        counter = 0;
    }

    @Override
    protected void tick(float delta) {
        // Enemy Movement Is Done Through "EnemyGroup"
    }

    @Override
    public void render(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        float secondsSinceStart = TimeUtils.timeSinceNanos(startTime) / 1000000000f;
        batch.draw(assets.getTextures().enemyAnimation.getKeyFrame(secondsSinceStart), position.x, position.y, bbWidth * scale, bbHeight * scale);
    }
}
