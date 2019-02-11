package com.redsponge.spaceinvaders.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.redsponge.spaceinvaders.game.Bullet;
import com.redsponge.spaceinvaders.game.Enemy;
import com.redsponge.spaceinvaders.game.EnemyGroup;
import com.redsponge.spaceinvaders.game.Entity;
import com.redsponge.spaceinvaders.game.Player;
import com.redsponge.spaceinvaders.utilities.Constants;
import com.redsponge.spaceinvaders.utilities.DependencyInjection;
import com.redsponge.spaceinvaders.utilities.HashCollections;

/** First screen of the application. Displayed after the application is created. */
public class GameScreen extends ScreenTemplate {

    private FitViewport viewport;
    private Player player;
    private EnemyGroup enemyGroup;

    private HashCollections<Entity> entities;

    public GameScreen(DependencyInjection di) {
        super(di);
        entities = new HashCollections<Entity>();
        entities.addType(Enemy.class);
        entities.addType(Bullet.class);
    }

    @Override
    public void show() {
        viewport = new FitViewport(Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        player = new Player(this, entities);
        entities.clearAll();
        enemyGroup = new EnemyGroup(entities);
        //addEntity(new Enemy(new Vector2(200, 200)));
        enemyGroup.initGroup();
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

        viewport.apply();

        shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);
        batch.setProjectionMatrix(viewport.getCamera().combined);

        shapeRenderer.begin(ShapeType.Filled);
        player.render(batch, shapeRenderer);

        entities.forEach(e -> e.render(batch, shapeRenderer));
        shapeRenderer.end();
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