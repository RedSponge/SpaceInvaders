package com.redsponge.spaceinvaders.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.redsponge.spaceinvaders.utilities.Constants;
import com.redsponge.spaceinvaders.utilities.HashCollections;

public class Bullet extends Entity {

    private HashCollections<Entity> entities;

    public Bullet(Vector2 playerPosition, HashCollections<Entity> entities) {
        super(new Vector2(playerPosition).add(Constants.PLAYER_WIDTH / 2f - Constants.BULLET_WIDTH / 2f, Constants.PLAYER_HEIGHT / 2f + Constants.BULLET_HEIGHT / 2f), Constants.BULLET_WIDTH, Constants.BULLET_HEIGHT, new Vector2(0, Constants.BULLET_SPEED));
        this.entities = entities;
    }

    @Override
    protected void tick(float delta) {
        position.add(velocity);

        this.updateBoundingBox();
        for(Enemy e : entities.get(Enemy.class)) {
            if(e.boundingBox.overlaps(this.boundingBox)) {
                e.kill();
                this.kill();
                Gdx.app.log("Bullet", "Killed Enemy!");
            }
        }

        if(position.y - bbHeight > Constants.GAME_HEIGHT) {
            Gdx.app.log("Bullet", "Removing Bullet Out Of Screen");
            this.kill();
        }
    }

    @Override
    public void render(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.rect(position.x, position.y, bbWidth, bbHeight);
    }
}
