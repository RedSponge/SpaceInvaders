package com.redsponge.spaceinvaders.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.redsponge.spaceinvaders.game.Bullet;
import com.redsponge.spaceinvaders.game.Enemy;
import com.redsponge.spaceinvaders.game.EnemyBullet;
import com.redsponge.spaceinvaders.game.EnemyGroup;
import com.redsponge.spaceinvaders.game.Entity;
import com.redsponge.spaceinvaders.game.Player;
import com.redsponge.spaceinvaders.utilities.Constants;
import com.redsponge.spaceinvaders.utilities.DependencyInjection;
import com.redsponge.spaceinvaders.utilities.HashCollections;

import java.util.Random;

public class GameScreen extends ScreenTemplate {

    private FitViewport viewport;
    private Player player;
    private EnemyGroup enemyGroup;

    private int cameraShakes;
    private Random random;

    private HashCollections<Entity> entities;
    private float offset;

    public GameScreen(DependencyInjection di) {
        super(di);
        entities = new HashCollections<Entity>();
        entities.addType(Enemy.class);
        entities.addType(Bullet.class);
        entities.addType(EnemyBullet.class);
        cameraShakes = 0;
        random = new Random();
        offset = 0;
    }

    @Override
    public void show() {
        viewport = new FitViewport(Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        player = new Player(this, entities, assets);
        entities.clearAll();
        enemyGroup = new EnemyGroup(entities, assets, this);
        enemyGroup.initGroup();
        offset = 0;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        player.update(delta);
        enemyGroup.update(delta);
        entities.forEach(e -> {
            e.update(delta);
        });

        entities.forEach(e -> {
            if(e.isDead()) {
                entities.remove(e);
            }
        });

        updateCameraAndViewport();

        shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);
        batch.setProjectionMatrix(viewport.getCamera().combined);

        batch.begin();
        renderSky();
        player.render(batch, shapeRenderer);
        entities.forEach(e -> e.render(batch, shapeRenderer));
        assets.getParticles().render(delta, batch);

        batch.end();

        offset -= 1f;
        offset %= assets.getTextures().sky.getRegionHeight();
    }

    private void renderSky() {
        TextureRegion sky = assets.getTextures().sky;
        for(int j = 0; j < Constants.GAME_HEIGHT / sky.getRegionHeight() + 1; j++) {
            for(int i = 0; i < Constants.GAME_WIDTH / sky.getRegionWidth(); i++) {
                float x = i * sky.getRegionWidth();
                float y = j * sky.getRegionHeight() + offset;
                boolean thing = false;
                if(y + sky.getRegionHeight() < 0) {
                    y = Constants.GAME_HEIGHT - y;
                    thing = true;
                }
                if(thing)
                    System.out.println(y);
                batch.draw(sky, x, y);
            }
        }
    }

    private void updateCameraAndViewport() {
        if(cameraShakes > 0) {
            int strength = 3;
            float xAdj = random.nextInt(strength * 2) - strength;
            float yAdj = random.nextInt(strength * 2) - strength;
            viewport.getCamera().position.x += xAdj;
            viewport.getCamera().position.y += yAdj;

            viewport.apply();
            cameraShakes--;
        } else {
            viewport.apply(true);
        }
    }

    public void shakeCamera() {
        cameraShakes = 5;
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void hide() {
        entities.clearAll();
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }
}