package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/*
жизни игроков
 */
public class Bullet extends Actor {
    boolean visible = true;
    public Bullet(){
        this.setSize(ViewConst.bul_width,ViewConst.bul_height);
        this.setRotation(ViewConst.bul_r);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (visible) {
            batch.draw(Assets.get_texture_region("bullet"),
                    getX(), getY(),
                    getWidth() / 2, getHeight() / 2, getWidth(), getHeight(), 1, 1, getRotation());

        }
    }
}
