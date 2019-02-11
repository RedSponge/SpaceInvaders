package com.redsponge.spaceinvaders.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {

    protected Vector2 position;
    protected Vector2 velocity;
    protected float bbWidth, bbHeight;
    protected Rectangle boundingBox;
    protected boolean dead;


    public Entity(Vector2 position, float bbWidth, float bbHeight, Vector2 velocity) {
        this.position = position;
        this.bbWidth = bbWidth;
        this.bbHeight = bbHeight;
        this.velocity = velocity;
        this.boundingBox = new Rectangle();
        dead = false;
    }

    public Entity(Vector2 position, float bbWidth, float bbHeight) {
        this(position, bbWidth, bbHeight, new Vector2());
    }

    public Entity(float x, float y, float bbWidth, float bbHeight) {
        this(new Vector2(x, y), bbWidth, bbHeight);
    }

    /**
     * Updates the bounding box for checks
     */
    protected void updateBoundingBox() {
        boundingBox.set(position.x, position.y, bbWidth, bbHeight);
    }

    public void update(float delta) {
        updateBoundingBox();
        tick(delta);
        updateBoundingBox();
    }

    public void kill() {
        dead = true;
    }

    public boolean isDead() {
        return dead;
    }

    protected abstract void tick(float delta);
    public abstract void render(SpriteBatch batch, ShapeRenderer shapeRenderer);

}
