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
    public Card(Integer id){
        this.id=id;
        setSize(ViewConst.card_width,ViewConst.card_height);
        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (visible) {
                    if (getX() != ViewConst.playingCard_x && getY() != ViewConst.playingCard_y) {
                        setPosition(ViewConst.playingCard_x, ViewConst.playingCard_y);
                        Game.play_card = this;
                        Game.players.elementAt(Game.currentPlayer).deck.play_deck.set(
                                Game.players.elementAt(Game.currentPlayer).deck.play_deck.indexOf(this), null);
                        Game.players.elementAt(Game.currentPlayer).deck.play_deck.trimToSize();

                    } else if (getX() == ViewConst.playingCard_x && getY() == ViewConst.playingCard_y) {

                    }
                    super.clicked(event, x, y);
                }
            }
        });
    }

    public Integer getId() {
        return id;
    }
}
