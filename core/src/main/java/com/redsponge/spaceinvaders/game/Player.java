package com.redsponge.spaceinvaders.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.redsponge.spaceinvaders.screen.GameScreen;
import com.redsponge.spaceinvaders.utilities.Constants;
import com.redsponge.spaceinvaders.utilities.HashCollections;

public class Player extends Entity {

    private float speed;
    private GameScreen gameScreen;
    private HashCollections<Entity> entities;

    public Player(GameScreen gameScreen, HashCollections<Entity> entities) {
        super(Constants.GAME_WIDTH / 2f - Constants.PLAYER_WIDTH / 2f, Constants.GAME_HEIGHT / 8f, Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT);
        this.gameScreen = gameScreen;
        this.entities = entities;
        speed = Constants.PLAYER_SPEED;
    }

    @Override
    protected void tick(float delta) {
        if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
            position.x += speed * delta;
        }
        if(Gdx.input.isKeyPressed(Keys.LEFT)) {
            position.x -= speed * delta;
        }

        if(Gdx.input.isKeyJustPressed(Keys.SPACE)) {
            shootBullet();
        }

        if(position.x < 0) position.x = 0;
        if(position.x + bbWidth > Constants.GAME_WIDTH) position.x = Constants.GAME_WIDTH - bbWidth;
    }

    private void shootBullet() {
        gameScreen.addEntity(new Bullet(position, entities));
    }

    @Override
    public void render(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(position.x, position.y, bbWidth, bbHeight);
    }
}
