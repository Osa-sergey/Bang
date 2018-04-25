package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Base extends Actor {
    private Integer id;
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(Assets.get_texture_region("base000"+id),
                getX(),getY(),
                0, 0,getWidth(),getHeight(), 1, 1, getRotation());

    }
    public Base(Integer id){
        this.id=id;
        this.setSize(ViewConst.base_width,ViewConst.base_height);
    }
}
