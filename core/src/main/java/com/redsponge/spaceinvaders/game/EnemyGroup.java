package com.redsponge.spaceinvaders.game;

import com.badlogic.gdx.math.Vector2;
import com.redsponge.spaceinvaders.utilities.Constants;
import com.redsponge.spaceinvaders.utilities.HashCollections;

public class EnemyGroup {

    private HashCollections<Entity> entities;
    private int speed;
    private float counter;
    private Vector2 position;
    private Vector2 movedFromAnchor;
    private boolean right;

    public EnemyGroup(HashCollections<Entity> entities) {
        this.entities = entities;
        speed = 1;
        counter = 0;
        position = new Vector2(Constants.ENEMY_SPACING_FROM_BORDER, Constants.GAME_HEIGHT - Constants.ENEMY_SPACING_FROM_BORDER - Constants.ENEMY_HEIGHT);
        movedFromAnchor = new Vector2(0, 0);
        right = true;
    }

    public void initGroup() {
        float width = Constants.GAME_WIDTH - Constants.ENEMY_SPACING_FROM_BORDER * 2;
        float wSpacing = (width - Constants.ENEMY_WIDTH * Constants.ENEMY_COLS) / Constants.ENEMY_COLS;
        System.out.println(wSpacing);
        for(int i = 0; i < Constants.ENEMY_ROWS; i++) {
            for(int j = 0; j < Constants.ENEMY_COLS; j++) {
                float x = position.x + wSpacing / 2 + (wSpacing + Constants.ENEMY_WIDTH) * j;
                System.out.println(x);
                float y = position.y - (Constants.ENEMY_ROW_SPACING + Constants.ENEMY_HEIGHT) * (i);
                entities.add(new Enemy(new Vector2(x, y)));
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
//        System.out.println(movedFromAnchor);
        if(movedFromAnchor.x > Constants.ENEMY_SPACING_FROM_BORDER && right || movedFromAnchor.x < -Constants.ENEMY_SPACING_FROM_BORDER && !right) {
            right = !right;
            System.out.println("CHANGE! " + movedFromAnchor);
            change.y = -Constants.ENEMY_MOVE_SPEED;
        } else {
            change.x = Constants.ENEMY_MOVE_SPEED * (right ? 1 : -1);
        }

        movedFromAnchor.add(change);
        for(Enemy enemy : entities.get(Enemy.class)) {
            enemy.position.add(change);
        }
    }
}
