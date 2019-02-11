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

    public Player(GameScreen gameScreen, HashCollections<Entity> entities, Assets assets) {
        super(Constants.GAME_WIDTH / 2f - Constants.PLAYER_WIDTH / 2f, Constants.GAME_HEIGHT / 8f, Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT);
        this.gameScreen = gameScreen;
        this.entities = entities;
        this.assets = assets;
        speed = Constants.PLAYER_SPEED;
        shootTime = 0;
        squashAnimationLength = 0.1f;
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
        gameScreen.addEntity(new Bullet(position, entities, assets, gameScreen));
        assets.getSounds().shoot.play(0.5f, 1 + (float) (Math.random() * 0.5f) - 0.25f, 0);
        shootTime = TimeUtils.nanoTime();
    }

    @Override
    public void render(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.WHITE);

        Interpolation squash = Interpolation.exp5In;
        float prog = (TimeUtils.timeSinceNanos(shootTime) / 1000000000f) / squashAnimationLength;
        float alpha = 1 - squash.apply(prog);
        float extraW = 0;
        if(prog <= 1) {
            System.out.println(alpha);
            extraW = alpha * 10;
        }

        shapeRenderer.rect(position.x - extraW / 2, position.y, bbWidth + extraW, bbHeight);
    }
}
