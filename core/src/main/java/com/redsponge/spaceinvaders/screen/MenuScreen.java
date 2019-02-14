package com.redsponge.spaceinvaders.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.redsponge.spaceinvaders.SpaceInvaders;
import com.redsponge.spaceinvaders.utilities.Constants;
import com.redsponge.spaceinvaders.utilities.DependencyInjection;

public class MenuScreen extends ScreenTemplate {

    private Stage stage;
    private FitViewport viewport;

    public MenuScreen(DependencyInjection di) {
        super(di);
    }

    @Override
    public void show() {
        viewport = new FitViewport(Constants.MENU_WIDTH, Constants.MENU_HEIGHT);
        stage = new Stage(viewport, batch);

        Table buttonTable = new Table(assets.getSkins().neon);
        buttonTable.setFillParent(true);
        buttonTable.setDebug(true);
        buttonTable.bottom();

        Image title = new Image(assets.getTextures().title);
        buttonTable.add(title).width(256).height(128).grow().center();
        buttonTable.row();
//        title.moveBy(-100, 0);

        TextButton play = new TextButton("Start Game", assets.getSkins().neon);
        play.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SpaceInvaders.transitionToScreen(GameScreen.class);
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


        stage.addActor(buttonTable);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }
}
