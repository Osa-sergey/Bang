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
        /*
        прослушивание нажатий на карту
         */
        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                /*
                выделение зоны прослушивания на экране
                 */
                if (visible && getX()>=ViewConst.deck_card_x_start
                        && getX()<=ViewConst.screen_width
                        && getY()>=0
                        && getY()<=ViewConst.btnNext_y)
                {
                    /*
                    выбор цели карты
                     */
                    Game.target = null;
                    if(getId()!=5)
                        Game.target = Game.players.elementAt(Game.currentPlayer);
                    else
                    {
                        /*
                        в случае когда эффект карты действует только на одного конкретного другого игрока
                         */
                        GameScreen.isBangActive = true;
                        GameScreen.tmpCard=Card.this;
                        // ожидание выбора цели действия карты
                        // в обработчике карт персон
                        return;
                    }
                    /*
                    выполнение действия карты
                     */
                    Game.players.elementAt(Game.currentPlayer).play_card(Card.this,Game.target);
                }
            }
        });
    }

    public Integer getId() {
        return id;
    }
}
