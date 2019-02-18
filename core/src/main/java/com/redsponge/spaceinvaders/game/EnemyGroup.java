package com.redsponge.spaceinvaders.game;

import com.badlogic.gdx.math.Vector2;
import com.redsponge.spaceinvaders.assets.Assets;
import com.redsponge.spaceinvaders.screen.GameScreen;
import com.redsponge.spaceinvaders.utilities.Constants;
import com.redsponge.spaceinvaders.utilities.HashCollections;

public class EnemyGroup {

    private HashCollections<Entity> entities;
    private float speed;
    private float counter;
    private Vector2 position;
    private Vector2 movedFromAnchor;
    private boolean right;
    private Assets assets;
    private GameScreen gameScreen;

    public EnemyGroup(HashCollections<Entity> entities, Assets assets, GameScreen gameScreen) {
        this.entities = entities;
        this.assets = assets;
        this.gameScreen = gameScreen;
        speed = 1.5f;
        counter = 0;
        position = new Vector2(Constants.ENEMY_SPACING_FROM_BORDER, Constants.GAME_HEIGHT - Constants.ENEMY_SPACING_FROM_BORDER - Constants.ENEMY_HEIGHT);
        movedFromAnchor = new Vector2(0, 0);
        right = true;
    }

    public void initGroup() {
        float width = Constants.GAME_WIDTH - Constants.ENEMY_SPACING_FROM_BORDER * 2;
        float wSpacing = (width - Constants.ENEMY_WIDTH * Constants.ENEMY_COLS) / Constants.ENEMY_COLS;
        for(int i = 0; i < Constants.ENEMY_ROWS; i++) {
            for(int j = 0; j < Constants.ENEMY_COLS; j++) {
                float x = position.x + wSpacing / 2 + (wSpacing + Constants.ENEMY_WIDTH) * j;
                float y = position.y - (Constants.ENEMY_ROW_SPACING + Constants.ENEMY_HEIGHT) * (i);
                entities.add(new Enemy(new Vector2(x, y), assets));
            }
        }
    }

    public void update(float delta) {
        counter += delta;
        if(counter > speed) {
            moveGroup();
            counter -= speed;
        }
    }

    private void moveGroup() {
        Vector2 change = new Vector2();
        if(movedFromAnchor.x > Constants.ENEMY_SPACING_FROM_BORDER && right || movedFromAnchor.x < -Constants.ENEMY_SPACING_FROM_BORDER && !right) {
            right = !right;
            change.y = -Constants.ENEMY_MOVE_SPEED;
            speed -= 0.1f;
        } else {
            change.x = Constants.ENEMY_MOVE_SPEED * (right ? 1 : -1);
        }

        movedFromAnchor.add(change);
        for(Enemy enemy : entities.get(Enemy.class)) {
            enemy.position.add(change);
        }
    }
}
