package com.redsponge.spaceinvaders.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.redsponge.spaceinvaders.game.Enemy;
import com.redsponge.spaceinvaders.transitions.TransitionFade;
import com.redsponge.spaceinvaders.utilities.Constants;
import com.redsponge.spaceinvaders.utilities.DependencyInjection;

public class MenuScreen extends AbstractScreen {

    private Stage stage;
    private FitViewport viewport;
    private Enemy enemy;
    private double counter;

    public MenuScreen(DependencyInjection di, GameAccessor ga) {
        super(di, ga);
    }

    @Override
    public void show() {
        viewport = new FitViewport(Constants.MENU_WIDTH, Constants.MENU_HEIGHT);
        stage = new Stage(viewport, batch);

        Table buttonTable = new Table(assets.getSkins().neon);
        buttonTable.setFillParent(true);
        buttonTable.bottom();

        Image title = new Image(assets.getTextures().title);
        title.getColor().a = 0;
        buttonTable.add(title).width(256*2).height(128*2).grow().center();
        buttonTable.row();

        TextButton play = new TextButton("Start Game", assets.getSkins().neon);
        play.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ga.transitionTo(new GameScreen(di, ga), new TransitionFade(), 5);
            }
        });


        TextButton settings = new TextButton("Settings", assets.getSkins().neon);
        settings.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }
        });

        TextButton credits = new TextButton("Credits", assets.getSkins().neon);
        credits.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }
        });

        TextButton exit = new TextButton("Exit", assets.getSkins().neon);
        exit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });


        float pad = -5;
        float fadeInSpeed = 1;
        float delay = 0.5f;
        Interpolation interpolation = Interpolation.exp5;

        play.getColor().a = 0;
        settings.getColor().a = 0;
        credits.getColor().a = 0;
        exit.getColor().a = 0;

        play.addAction(Actions.fadeIn(fadeInSpeed, interpolation));
        buttonTable.add(play).pad(pad);
        buttonTable.row();
        buttonTable.add(settings).pad(pad);
        settings.addAction(Actions.delay(delay, Actions.fadeIn(fadeInSpeed, interpolation)));
        buttonTable.row();
        buttonTable.add(credits).pad(pad);
        credits.addAction(Actions.delay(delay * 2, Actions.fadeIn(fadeInSpeed, interpolation)));
        buttonTable.row();
        buttonTable.add(exit).pad(pad);
        exit.addAction(Actions.delay(delay * 3, Actions.fadeIn(fadeInSpeed, interpolation)));
        title.addAction(Actions.delay(delay * 4, Actions.fadeIn(fadeInSpeed * 2, interpolation)));

        stage.addActor(buttonTable);

        Gdx.input.setInputProcessor(stage);

        enemy = new Enemy(new Vector2(viewport.getWorldWidth() / 4 * 3, viewport.getWorldHeight() / 4 * 3), assets);
        counter = 0;
    }

    @Override
    public void tick(float delta) {
        counter += delta;
        stage.act(delta);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        viewport.apply();
        stage.draw();

        batch.begin();
        enemy.getPosition().y = (float) (viewport.getWorldHeight() / 4 * 2.5 + Math.sin(counter) * 15);
        enemy.render(batch, shapeRenderer);
        batch.end();
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }
}
