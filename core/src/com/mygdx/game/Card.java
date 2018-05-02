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
    boolean dragged = false;
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
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                if (visible && getX()>=ViewConst.deck_card_x_start
                        && getX()<=ViewConst.screen_width
                        && getY()>=0
                        && getY()<=ViewConst.btnNext_y)
                    dragged = true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if(dragged) {
                    switch (getId()) {
                        case 5:
                        case 6:
                        case 7:
                        case 8:
                        case 9:
                        case 10:
                        case 11:
                        case 12:
                        case 13:
                        case 14:
                        case 15:
                        case 16:
                            /*карты активности*/
                            CardAction tmp = new CardAction(getId());
                            tmp.setPosition(ViewConst.discard_x,ViewConst.discard_y);
                            GameScreen.getStage().addActor(tmp);
                            Game.dis.add_in_discarded(tmp);
                            Game.players.elementAt(Game.currentPlayer).deck.delete_from_deck(Card.this);
                            break;
                        case 0:
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                            /*карты эффекты*/
                            CardEffect tmp1 = new CardEffect(getId());
                            tmp1.setPosition(ViewConst.discard_x,ViewConst.discard_y);
                            GameScreen.getStage().addActor(tmp1);
                            Game.dis.add_in_discarded(tmp1);
                            Game.players.elementAt(Game.currentPlayer).deck.delete_from_deck(Card.this);
                            break;
                        default:
                            /*карты оружие*/
                            CardWeapon tmp2 = new CardWeapon(getId());
                            tmp2.setPosition(ViewConst.discard_x,ViewConst.discard_y);
                            GameScreen.getStage().addActor(tmp2);
                            Game.dis.add_in_discarded(tmp2);
                            Game.players.elementAt(Game.currentPlayer).deck.delete_from_deck(Card.this);
                    }
                    if(Game.players.elementAt(Game.currentPlayer).deck.play_deck.size()<ViewConst.deck_cardsVisible_number){
                        GameScreen.next_card.visible = false;
                        GameScreen.prev_card.visible = false;
                    }else{
                        GameScreen.next_card.visible = true;
                        GameScreen.prev_card.visible = false;
                    }
                        for (int i = 0; i < Game.players.elementAt(Game.currentPlayer).deck.play_deck.size(); i++) {
                            if (i <= ViewConst.deck_cardsVisible_number) {
                                Game.players.elementAt(Game.currentPlayer).deck.play_deck.elementAt(i).
                                        visible = true;
                                Game.players.elementAt(Game.currentPlayer).deck.play_deck.elementAt(i).
                                        setPosition(ViewConst.deck_card_x_start + i * ViewConst.deck_card_hor, ViewConst.deck_card_y);
                            } else break;
                        }
                    for (int i = ViewConst.deck_cardsVisible_number+1; i < Game.players.elementAt(Game.currentPlayer).deck.play_deck.size(); i++) {
                        Game.players.elementAt(Game.currentPlayer).deck.play_deck.elementAt(i).
                                visible = false;
                        Game.players.elementAt(Game.currentPlayer).deck.play_deck.elementAt(i).
                                setPosition(ViewConst.screen_width + (i - 2 - ViewConst.deck_cardsVisible_number) * ViewConst.deck_card_hor, ViewConst.deck_card_y);
                    }
                    GameScreen.group_actor[0].removeActor(Card.this);
                    dragged = false;
                }else{
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
            }
        });
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
