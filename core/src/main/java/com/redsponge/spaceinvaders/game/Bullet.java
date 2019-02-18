package com.redsponge.spaceinvaders.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.redsponge.spaceinvaders.assets.Assets;
import com.redsponge.spaceinvaders.screen.GameScreen;
import com.redsponge.spaceinvaders.utilities.Constants;
import com.redsponge.spaceinvaders.utilities.HashCollections;

public class Bullet extends Entity {

    private HashCollections<Entity> entities;
    private Assets assets;
    private GameScreen gameScreen;

    public Bullet(Vector2 playerPosition, HashCollections<Entity> entities, Assets assets, GameScreen gameScreen) {
        super(new Vector2(playerPosition).add(Constants.PLAYER_WIDTH / 2f - Constants.BULLET_WIDTH / 2f, Constants.PLAYER_HEIGHT / 2f + Constants.BULLET_HEIGHT / 2f), Constants.BULLET_WIDTH, Constants.BULLET_HEIGHT, new Vector2(0, Constants.BULLET_SPEED));
        this.entities = entities;
        this.assets = assets;
        this.gameScreen = gameScreen;
    }

    @Override
    protected void tick(float delta) {
        position.add(velocity);

        this.updateBoundingBox();
        for(Enemy e : entities.get(Enemy.class)) {
            if(e.boundingBox.overlaps(this.boundingBox)) {
                e.kill();
                this.kill();
                gameScreen.shakeCamera();
                assets.getSounds().enemyHit.play(0.5f, 1 + (float) (Math.random() * 0.5f) - 0.25f, 0);
                assets.getParticles().enemyDeath.spawn(position);
                assets.getParticles().score_100.spawn(new Vector2(position).add(0, 10));
                gameScreen.addToScore(100);
            }
        }

        if(position.y - bbHeight > Constants.GAME_HEIGHT) {
            this.kill();
        }
    }

    @Override
    public void render(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        batch.draw(assets.getTextures().bullet, position.x, position.y, bbWidth, bbHeight);
    }
}
