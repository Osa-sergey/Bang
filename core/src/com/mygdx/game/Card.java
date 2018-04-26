package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Card extends Actor {
    private Integer id;
    boolean visible=true;
    boolean open = true;
    boolean cover = true;
    public void draw(Batch batch, float parentAlpha) {
        if(visible) {
            if(open){
                batch.draw(Assets.get_texture_region("Card" + id),
                        getX(), getY(),
                        0, 0,getWidth(),getHeight(), 1, 1, getRotation());
            }
            else if(cover){
                batch.draw(Assets.get_texture_region("cover_card"),
                        getX(), getY(),
                        0, 0, getWidth(),getHeight(), 1, 1, getRotation());

            }else{
                batch.draw(Assets.get_texture_region("cover_roles"),
                        getX(), getY(),
                        0, 0, getWidth(),getHeight(), 1, 1, getRotation());
            }
        }
    }
    public Card(final Integer id){
        this.id=id;
        setSize(ViewConst.card_width,ViewConst.card_height);
        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

                if (visible && (getX()>=ViewConst.deck_card_x_start
                        && getX()<=ViewConst.screen_width
                        && getY()>=0
                        && getY()<=ViewConst.btnNext_y)
                        ||
                        getX()>=ViewConst.player_person_x
                        && getX()<=ViewConst.player_person_x+ViewConst.card_width
                        && getY()>=ViewConst.player_person_y
                        &&getY()<=ViewConst.player_person_y+ViewConst.card_height) {
                    System.out.println(id);
                    super.clicked(event, x, y);
                }
            }
        });
    }

    public Integer getId() {
        return id;
    }
}
