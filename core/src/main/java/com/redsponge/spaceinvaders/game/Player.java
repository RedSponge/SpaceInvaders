package com.redsponge.spaceinvaders.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.TimeUtils;
import com.redsponge.spaceinvaders.assets.Assets;
import com.redsponge.spaceinvaders.screen.GameScreen;
import com.redsponge.spaceinvaders.utilities.Constants;
import com.redsponge.spaceinvaders.utilities.HashCollections;

public class Player extends Entity {

    private float speed;
    private GameScreen gameScreen;
    private HashCollections<Entity> entities;
    private long shootTime;
    private float squashAnimationLength;

    private Assets assets;
    private float timeSinceStart;

    public Player(GameScreen gameScreen, HashCollections<Entity> entities, Assets assets) {
        super(Constants.GAME_WIDTH / 2f - Constants.PLAYER_WIDTH / 2f, Constants.GAME_HEIGHT / 8f, Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT);
        this.gameScreen = gameScreen;
        this.entities = entities;
        this.assets = assets;
        speed = Constants.PLAYER_SPEED;
        shootTime = 0;
        squashAnimationLength = 0.1f;
        timeSinceStart = 0;
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
            if(TimeUtils.timeSinceNanos(shootTime) / 1000000000f > Constants.SHOOT_COOLDOWN_TIME)
            shootBullet();
        }

        if(position.x < 0) position.x = 0;
        if(position.x + bbWidth > Constants.GAME_WIDTH) position.x = Constants.GAME_WIDTH - bbWidth;
        timeSinceStart += delta;
    }

    private void shootBullet() {
        gameScreen.addEntity(new Bullet(position, entities, assets, gameScreen));
        assets.getSounds().shoot.play(0.5f, 1 + (float) (Math.random() * 0.5f) - 0.25f, 0);
        shootTime = TimeUtils.nanoTime();
    }

    @Override
    public void render(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        batch.draw(assets.getTextures().spaceShipAnimation.getKeyFrame(timeSinceStart), position.x, position.y, bbWidth, bbHeight);
    }
}
