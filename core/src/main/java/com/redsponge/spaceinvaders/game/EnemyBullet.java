package com.redsponge.spaceinvaders.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.redsponge.spaceinvaders.assets.Assets;
import com.redsponge.spaceinvaders.utilities.Constants;

public class EnemyBullet extends Entity {

    private Assets assets;

    public EnemyBullet(Vector2 position, Assets assets) {
        super(position, Constants.BULLET_WIDTH, Constants.BULLET_HEIGHT, new Vector2(0, -1));
        this.assets = assets;
    }

    @Override
    protected void tick(float delta) {
        position.add(velocity);
    }

    @Override
    public void render(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        batch.draw(assets.getTextures().bullet, position.x, position.y, 0, 0, bbWidth, bbHeight, 1, 1, 180);
    }
}
