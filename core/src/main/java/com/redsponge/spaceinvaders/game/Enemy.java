package com.redsponge.spaceinvaders.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.redsponge.spaceinvaders.utilities.Constants;

public class Enemy extends Entity {

    public Enemy(Vector2 position) {
        super(position, Constants.ENEMY_WIDTH, Constants.ENEMY_HEIGHT);
    }

    @Override
    protected void tick(float delta) {

    }

    @Override
    public void render(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(position.x, position.y, bbWidth, bbHeight);
    }
}
