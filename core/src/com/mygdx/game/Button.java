package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


public class Button extends Actor {

    Integer id;
    boolean visible = true;
    public Button (Integer id, final MyGdxGame game, final GameScreen gameScreen){
        this.id=id;
        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (visible) {
                    switch (getId()) {
                        case 1:
                            game.setScreen(new GameScreen(game));
                            break;
                        case 2:
                            game.setScreen(new Rules());
                            break;
                        case 3:
                            Gdx.app.exit();
                            break;
                        case 5: {
                            for (int i = 0; i < gameScreen.players.elementAt(gameScreen.game_.currentPlayer).deck.play_deck.capacity(); i++) {
                                gameScreen.players.elementAt(gameScreen.game_.currentPlayer).deck.play_deck.elementAt(i).visible = false;
                            }
                            for (int i = 0; i < gameScreen.players.elementAt(gameScreen.game_.currentPlayer).effects.capacity(); i++) {
                                gameScreen.players.elementAt(gameScreen.game_.currentPlayer).effects.elementAt(i).visible = false;
                            }
                            gameScreen.players.elementAt(gameScreen.game_.currentPlayer).role.open = false;
                            gameScreen.game_.next_turn();
                            visible = false;
                            gameScreen.next_card.visible = false;
                            gameScreen.prev_card.visible = false;
                            gameScreen.submit.visible = true;
                            break;
                        }
                        case 6: {
                            switch (gameScreen.game_.currentPlayersNumber) {
                                case 4:
                                    gameScreen.group_actor[0].setPosition(ViewConst.pr1_x, ViewConst.pr1_y);
                                    gameScreen.group_actor[0].setRotation(ViewConst.pr1_r);
                                    gameScreen.group_actor[1].setPosition(ViewConst.pr2_x, ViewConst.pr2_y);
                                    gameScreen.group_actor[1].setRotation(ViewConst.pr2_r);
                                    gameScreen.group_actor[2].setPosition(ViewConst.pr3_x, ViewConst.pr3_y);
                                    gameScreen.group_actor[2].setRotation(ViewConst.pr3_r);
                                    gameScreen.group_actor[3].setPosition(ViewConst.pr0_x, ViewConst.pr0_y);
                                    gameScreen.group_actor[3].setRotation(ViewConst.pr0_r);
                                    break;
                                case 3:
                                    gameScreen.group_actor[0].setPosition(ViewConst.pr1_x, ViewConst.pr1_y);
                                    gameScreen.group_actor[0].setRotation(ViewConst.pr1_r);
                                    gameScreen.group_actor[1].setPosition(ViewConst.pr3_x, ViewConst.pr3_y);
                                    gameScreen.group_actor[1].setRotation(ViewConst.pr3_r);
                                    gameScreen.group_actor[2].setPosition(ViewConst.pr0_x, ViewConst.pr0_y);
                                    gameScreen.group_actor[2].setRotation(ViewConst.pr0_r);
                                    break;
                                case 2:
                                    gameScreen.group_actor[0].setPosition(ViewConst.pr2_x, ViewConst.pr2_y);
                                    gameScreen.group_actor[0].setRotation(ViewConst.pr2_r);
                                    gameScreen.group_actor[1].setPosition(ViewConst.pr0_x, ViewConst.pr0_y);
                                    gameScreen.group_actor[1].setRotation(ViewConst.pr0_r);

                            }
                            Group tmp_group = gameScreen.group_actor[gameScreen.game_.currentPlayersNumber - 1];
                            for (int i = gameScreen.game_.currentPlayersNumber - 1; i > 0; i--) {
                                gameScreen.group_actor[i] = gameScreen.group_actor[i - 1];
                            }
                            gameScreen.group_actor[0] = tmp_group;

                            for (int i = 0; i < gameScreen.players.elementAt(gameScreen.game_.currentPlayer).deck.play_deck.capacity(); i++) {
                                gameScreen.players.elementAt(gameScreen.game_.currentPlayer).deck.play_deck.elementAt(i).visible = true;
                            }
                            for (int i = 0; i < gameScreen.players.elementAt(gameScreen.game_.currentPlayer).effects.capacity(); i++) {
                                gameScreen.players.elementAt(gameScreen.game_.currentPlayer).effects.elementAt(i).visible = true;
                            }
                            gameScreen.players.elementAt(gameScreen.game_.currentPlayer).role.open = true;
                            visible = false;
                            gameScreen.next.visible = true;
                            break;
                        }
                        case 7:{
                            gameScreen.prev_card.visible = true;
                            visible = false;
                            break;
                        }
                        case 8:{
                            gameScreen.next_card.visible = true;
                            visible = false;
                            break;
                        }
                    }
                    super.clicked(event, x, y);
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
