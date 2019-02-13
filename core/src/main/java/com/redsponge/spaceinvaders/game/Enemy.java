package com.redsponge.spaceinvaders.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.redsponge.spaceinvaders.assets.Assets;
import com.redsponge.spaceinvaders.screen.GameScreen;
import com.redsponge.spaceinvaders.utilities.Constants;

public class Enemy extends Entity {

    private long startTime;
    private Assets assets;
    private float counter;
    private GameScreen gameScreen;

    public Enemy(Vector2 position, Assets assets, GameScreen gameScreen) {
        super(position, Constants.ENEMY_WIDTH, Constants.ENEMY_HEIGHT);
        this.assets = assets;
        this.gameScreen = gameScreen;
        startTime = TimeUtils.nanoTime();
        counter = 0;
    }

    @Override
    protected void tick(float delta) {
//        if(true) return;
        counter += delta;
        if(counter > 1) {
            Gdx.app.log("Enemy", "Spawning Bullet!");
            counter--;
            gameScreen.addEntity(new EnemyBullet(new Vector2(position.x + bbWidth / 2 + Constants.BULLET_WIDTH / 2f, position.y), assets));
        }
    }

    @Override
    public void render(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        float secondsSinceStart = TimeUtils.timeSinceNanos(startTime) / 1000000000f;
        batch.draw(assets.getTextures().enemyAnimation.getKeyFrame(secondsSinceStart), position.x, position.y, bbWidth, bbHeight);
    }
}
