package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


public class Button extends Actor {

    Integer id;
    boolean visible = true;
    boolean inProcess = false;
    public Button (Integer id, final MyGdxGame game, final GameScreen gameScreen){
        this.id=id;
        /*
        добавление прослушивания нажатия кнопок
         */
        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (visible) {
                    switch (getId()) {
                        /*
                        кнопка старта игры, переход на игровой экран
                         */
                        case 1:
                            game.setScreen(new GameScreen(game));
                            break;
                            /*
                            переход на страницу с правилами
                             */
                        case 2:
                            game.setScreen(new Rules());
                            break;
                            /*
                            кнопка выхода из игры
                             */
                        case 3:
                            Gdx.app.exit();
                            break;
                            /*
                            кнопка next
                             */
                        case 5: {
                            gameScreen.prev_card.inProcess = true;
                            gameScreen.next_card.inProcess = true;
                            /*
                            расставление карт и деланье их видимыми
                             */
                            if(Game.players.elementAt(Game.currentPlayer).deck.play_deck.size()>Game.players.elementAt(Game.currentPlayer).currentHealthPoints){
                                return;
                            }
                            if(!gameScreen.next_card.visible) {
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
                            }
                            /*
                            скрывание карт, эффектов и роли
                             */
                            for (int i = 0; i < gameScreen.players.elementAt(gameScreen.game_.currentPlayer).deck.play_deck.size(); i++) {
                                gameScreen.players.elementAt(gameScreen.game_.currentPlayer).deck.play_deck.elementAt(i).
                                        visible = false;
                            }
                            for (int i = 0; i < gameScreen.players.elementAt(gameScreen.game_.currentPlayer).effects.size(); i++) {
                                gameScreen.players.elementAt(gameScreen.game_.currentPlayer).effects.elementAt(i).
                                        visible = false;
                            }
                            if(gameScreen.players.elementAt(gameScreen.game_.currentPlayer).currentHealthPoints!=0)
                            gameScreen.players.elementAt(gameScreen.game_.currentPlayer).role.open = false;
                            /*
                            передача хода
                             */
                            gameScreen.game_.next_turn();
                            /*
                            скрытие кнопок
                             */
                            if(Game.players.elementAt(Game.currentPlayer).currentHealthPoints==0){
                                visible = false;
                                gameScreen.next_card.visible = false;
                                gameScreen.prev_card.visible = false;
                                gameScreen.submit.visible = true;
                                        gameScreen.group_actor[0].setPosition(ViewConst.pr1_x, ViewConst.pr1_y);
                                        gameScreen.group_actor[0].setRotation(ViewConst.pr1_r);
                                        gameScreen.group_actor[1].setPosition(ViewConst.pr2_x, ViewConst.pr2_y);
                                        gameScreen.group_actor[1].setRotation(ViewConst.pr2_r);
                                        gameScreen.group_actor[2].setPosition(ViewConst.pr3_x, ViewConst.pr3_y);
                                        gameScreen.group_actor[2].setRotation(ViewConst.pr3_r);
                                        gameScreen.group_actor[3].setPosition(ViewConst.pr0_x, ViewConst.pr0_y);
                                        gameScreen.group_actor[3].setRotation(ViewConst.pr0_r);
                            /*
                            синхронизация графики и логики
                             */
                                Group tmp_group = gameScreen.group_actor[gameScreen.game_.currentPlayersNumber - 1];
                                for (int i = gameScreen.game_.currentPlayersNumber - 1; i > 0; i--) {
                                    gameScreen.group_actor[i] = gameScreen.group_actor[i - 1];
                                }
                                gameScreen.group_actor[0] = tmp_group;
                            /*
                            отображение карт, эффектов и роли
                             */
                                for (int i = 0; i < gameScreen.players.elementAt(gameScreen.game_.currentPlayer).deck.play_deck.size(); i++) {
                                    if(i>ViewConst.deck_cardsVisible_number){
                                        gameScreen.players.elementAt(gameScreen.game_.currentPlayer).deck.play_deck.elementAt(i).visible = false;
                                        Game.players.elementAt(Game.currentPlayer).deck.play_deck.elementAt(i).setPosition(ViewConst.screen_width+(i-1-ViewConst.
                                                deck_cardsVisible_number)*ViewConst.deck_card_hor,ViewConst.deck_card_y);
                                    }else{
                                        gameScreen.players.elementAt(gameScreen.game_.currentPlayer).deck.play_deck.elementAt(i).visible = true;
                                        Game.players.elementAt(Game.currentPlayer).deck.play_deck.elementAt(i).setPosition(ViewConst.deck_card_x_start+i*ViewConst.deck_card_hor,ViewConst.deck_card_y);
                                    }
                                }
                                for (int i = 0; i < gameScreen.players.elementAt(gameScreen.game_.currentPlayer).effects.size(); i++) {
                                    gameScreen.players.elementAt(gameScreen.game_.currentPlayer).effects.elementAt(i).visible = true;
                                }
                                gameScreen.players.elementAt(gameScreen.game_.currentPlayer).role.open = true;
                            /*
                            скрытие кнопок
                             */
                                gameScreen.submit.visible = false;
                                visible = true;
                                gameScreen.next_card.inProcess = false;
                                gameScreen.prev_card.inProcess = false;

                                gameScreen.prev_card.inProcess = true;
                                gameScreen.next_card.inProcess = true;
                            /*
                            расставление карт и деланье их видимыми
                             */
                                if(!gameScreen.next_card.visible) {
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
                                }
                            /*
                            скрывание карт, эффектов и роли
                             */
                                for (int i = 0; i < gameScreen.players.elementAt(gameScreen.game_.currentPlayer).deck.play_deck.size(); i++) {
                                    gameScreen.players.elementAt(gameScreen.game_.currentPlayer).deck.play_deck.elementAt(i).
                                            visible = false;
                                }
                                for (int i = 0; i < gameScreen.players.elementAt(gameScreen.game_.currentPlayer).effects.size(); i++) {
                                    gameScreen.players.elementAt(gameScreen.game_.currentPlayer).effects.elementAt(i).
                                            visible = false;
                                }
                                if(gameScreen.players.elementAt(gameScreen.game_.currentPlayer).currentHealthPoints!=0)
                                gameScreen.players.elementAt(gameScreen.game_.currentPlayer).role.open = false;
                            /*
                            передача хода
                             */
                                gameScreen.game_.next_turn();
                            /*
                            скрытие кнопок
                             */
                                visible = false;
                                gameScreen.next_card.visible = false;
                                gameScreen.prev_card.visible = false;
                                gameScreen.submit.visible = true;
                                break;
                            }else{
                                visible = false;
                                gameScreen.next_card.visible = false;
                                gameScreen.prev_card.visible = false;
                                gameScreen.submit.visible = true;
                                break;
                            }
                        }
                        /*
                        принятие хода
                         */
                        case 6: {
                            /*
                            поварачивание игроков на поле
                             */
                                    gameScreen.group_actor[0].setPosition(ViewConst.pr1_x, ViewConst.pr1_y);
                                    gameScreen.group_actor[0].setRotation(ViewConst.pr1_r);
                                    gameScreen.group_actor[1].setPosition(ViewConst.pr2_x, ViewConst.pr2_y);
                                    gameScreen.group_actor[1].setRotation(ViewConst.pr2_r);
                                    gameScreen.group_actor[2].setPosition(ViewConst.pr3_x, ViewConst.pr3_y);
                                    gameScreen.group_actor[2].setRotation(ViewConst.pr3_r);
                                    gameScreen.group_actor[3].setPosition(ViewConst.pr0_x, ViewConst.pr0_y);
                                    gameScreen.group_actor[3].setRotation(ViewConst.pr0_r);
                            /*
                            синхронизация графики и логики
                             */
                            Group tmp_group = gameScreen.group_actor[gameScreen.game_.currentPlayersNumber - 1];
                            for (int i = gameScreen.game_.currentPlayersNumber - 1; i > 0; i--) {
                                gameScreen.group_actor[i] = gameScreen.group_actor[i - 1];
                            }
                            gameScreen.group_actor[0] = tmp_group;
                            /*
                            отображение карт, эффектов и роли
                             */
                            for (int i = 0; i < gameScreen.players.elementAt(gameScreen.game_.currentPlayer).deck.play_deck.size(); i++) {
                                if(i>ViewConst.deck_cardsVisible_number){
                                    gameScreen.players.elementAt(gameScreen.game_.currentPlayer).deck.play_deck.elementAt(i).visible = false;
                                    Game.players.elementAt(Game.currentPlayer).deck.play_deck.elementAt(i).setPosition(ViewConst.screen_width+(i-1-ViewConst.
                                            deck_cardsVisible_number)*ViewConst.deck_card_hor,ViewConst.deck_card_y);
                                }else{
                                    gameScreen.players.elementAt(gameScreen.game_.currentPlayer).deck.play_deck.elementAt(i).visible = true;
                                    Game.players.elementAt(Game.currentPlayer).deck.play_deck.elementAt(i).setPosition(ViewConst.deck_card_x_start+i*ViewConst.deck_card_hor,ViewConst.deck_card_y);
                                }
                            }
                            for (int i = 0; i < gameScreen.players.elementAt(gameScreen.game_.currentPlayer).effects.size(); i++) {
                                gameScreen.players.elementAt(gameScreen.game_.currentPlayer).effects.elementAt(i).visible = true;
                            }
                            gameScreen.players.elementAt(gameScreen.game_.currentPlayer).role.open = true;
                            /*
                            скрытие кнопок
                             */
                            Game.players.elementAt(Game.currentPlayer).deck.add_in_deck(2);
                            for (int i =Game.players.elementAt(Game.currentPlayer).deck.play_deck.size()-2 ; i <Game.players.elementAt(Game.currentPlayer).deck.play_deck.size() ; i++) {
                                Game.players.elementAt(Game.currentPlayer).deck.play_deck.elementAt(i).open = true;
                                Game.players.elementAt(Game.currentPlayer).deck.play_deck.elementAt(i).setPosition(-500,-500);
                                GameScreen.group_actor[0].addActor(Game.players.elementAt(Game.currentPlayer).deck.play_deck.elementAt(i));
                            }

                            /*
                            присваиваем характеристики игроку
                             */
                            Game.players.elementAt(Game.currentPlayer).dist = 1;
                            Game.players.elementAt(Game.currentPlayer).distBang = Game.players.elementAt(Game.currentPlayer).weapon.distBang;
                            Game.players.elementAt(Game.currentPlayer).bangsInStep = Game.players.elementAt(Game.currentPlayer).weapon.bangsInStep;
                            Game.players.elementAt(Game.currentPlayer).currentBangsInStep = Game.players.elementAt(Game.currentPlayer).weapon.bangsInStep;
                          /*  switch (Game.players.elementAt(Game.currentPlayer).person.getId()){
                                case 114:{
                                    Game.players.elementAt(Game.currentPlayer).distBang += 1;
                                    break;
                                }
                                case 110:{
                                    Game.players.elementAt(Game.currentPlayer).dist += 1;
                                    break;
                                }
                                case 109:{
                                    Game.players.elementAt(Game.currentPlayer).bangsInStep += 1000;
                                    Game.players.elementAt(Game.currentPlayer).currentBangsInStep += 1000;
                                    break;
                                }
                            }
                            for (int i = 0; i <Game.players.elementAt(Game.currentPlayer).effects.size() ; i++) {
                                if(Game.players.elementAt(Game.currentPlayer).effects.elementAt(i).getId()== 0){
                                    Game.players.elementAt(Game.currentPlayer).dist++;
                                    continue;
                                }if(Game.players.elementAt(Game.currentPlayer).effects.elementAt(i).getId()== 1){
                                    Game.players.elementAt(Game.currentPlayer).distBang++;
                                }
                            }
                            */
                            visible = false;
                            gameScreen.next.visible = true;
                            gameScreen.next_card.inProcess = false;
                            gameScreen.prev_card.inProcess = false;
                          /*  System.out.println(Game.players.elementAt(Game.
                                    currentPlayer).currentBangsInStep+"curBangInStep");
                            System.out.println(Game.players.elementAt(Game.
                                    currentPlayer).bangsInStep+"bangInStep");
                            System.out.println(Game.players.elementAt(Game.
                                    currentPlayer).distBang+"distBang");
                            System.out.println(Game.players.elementAt(Game.
                                    currentPlayer).dist+"dist");
                            System.out.println("//////////////////////");*/
                            break;
                        }
                        /*
                        кнопка слайдера вперед
                         */
                        case 7:{
                            /*
                            скрытие кнопок
                             */
                            gameScreen.prev_card.visible = true;
                            visible = false;
                            /*
                             скрытие текущих карт и их смещение вниз
                             */
                            for (int i = 0; i <Game.players.elementAt(Game.currentPlayer).deck.play_deck.size(); i++) {
                                if(i<=ViewConst.deck_cardsVisible_number) {
                                    Game.players.elementAt(Game.currentPlayer).deck.play_deck.elementAt(i).visible = false;
                                    Game.players.elementAt(Game.currentPlayer).deck.play_deck.elementAt(i).setPosition(ViewConst.deck_card_x_start + i * ViewConst.deck_card_hor, -ViewConst.card_height);
                               }else break;
                            }
                            /*
                            открытие невлезающих карт и их сдвиг влево
                             */
                            for (int i = ViewConst.deck_cardsVisible_number+1; i <Game.players.elementAt(Game.currentPlayer).deck.play_deck.size(); i++) {
                                Game.players.elementAt(Game.currentPlayer).deck.play_deck.elementAt(i).visible = true;
                                Game.players.elementAt(Game.currentPlayer).deck.play_deck.elementAt(i).setPosition(ViewConst.deck_card_x_newStart + (i-2-ViewConst.deck_cardsVisible_number)* ViewConst.deck_card_hor,ViewConst.deck_card_y);
                            }
                            break;
                        }
                        /*
                        кнопка слайдера назад
                         */
                        case 8:{
                            /*
                            скрытие кнопок
                             */
                            gameScreen.next_card.visible = true;
                            visible = false;
                            /*
                            перемещение начала карт в исходное положение
                             */
                            for (int i = 0; i <Game.players.elementAt(Game.currentPlayer).deck.play_deck.size(); i++) {
                                if(i<=ViewConst.deck_cardsVisible_number) {
                                    Game.players.elementAt(Game.currentPlayer).deck.play_deck.elementAt(i).
                                            visible = true;
                                    Game.players.elementAt(Game.currentPlayer).deck.play_deck.elementAt(i).
                                            setPosition(ViewConst.deck_card_x_start + i * ViewConst.deck_card_hor, ViewConst.deck_card_y);
                                }else break;
                            }
                            /*
                            перемещение конца карт в исходное положение
                             */
                            for (int i = ViewConst.deck_cardsVisible_number+1; i <Game.players.elementAt(Game.currentPlayer).deck.play_deck.size(); i++) {
                                Game.players.elementAt(Game.currentPlayer).deck.play_deck.elementAt(i).
                                        visible = false;
                                Game.players.elementAt(Game.currentPlayer).deck.play_deck.elementAt(i).
                                        setPosition(ViewConst.screen_width+(i-2-ViewConst.deck_cardsVisible_number)*ViewConst.deck_card_hor,ViewConst.deck_card_y);
                            }
                            break;
                        }
                    }
                }
            }
        });
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        switch (id){
            case 1:
            case 2:
            case 3:
            case 4: {
                batch.draw(Assets.get_texture_region(id + ""),
                        getX(), getY(),
                        getWidth() * getScaleX(), getHeight() * getScaleY());
                break;
            }
            case 5: {
                if (visible) {
                    batch.draw(Assets.get_texture_region("next"), getX(), getY(), getWidth(), getHeight());
                }
                break;
            }
            case 6: {
                if (visible) {
                    batch.draw(Assets.get_texture_region("submit"), getX(), getY(), getWidth(), getHeight());
                }
                break;
            }
            case 7: {
                if (visible) {
                    batch.draw(Assets.get_texture_region("next_card"), getX(), getY(), getWidth(), getHeight());
                }
                break;
            }
            case 8: {
                if (visible) {
                    batch.draw(Assets.get_texture_region("prev_card"), getX(), getY(), getWidth(), getHeight());
                }
                break;
            }
        }
    }

    public Integer getId() {
        return id;
    }
}
