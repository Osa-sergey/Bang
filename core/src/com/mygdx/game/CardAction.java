package com.mygdx.game;

public class CardAction extends Card {

    public CardAction(Integer id) {
        super(id);
    }

    public void play_card_action(Player target){
        switch (super.getId()){
            case 5: { // бэнг
                for (int i = 0; i <target.deck.play_deck.size() ; i++) {
                    if(target.deck.play_deck.elementAt(i).getId()==6){

                        CardAction tmp = new CardAction(target.deck.play_deck.elementAt(i).getId());
                        tmp.setPosition(ViewConst.discard_x,ViewConst.discard_y);
                        GameScreen.getStage().addActor(tmp);
                        Game.dis.add_in_discarded(tmp);
                        target.deck.delete_from_deck(target.deck.play_deck.elementAt(i));
                        GameScreen.group_actor[0].removeActor(target.deck.play_deck.elementAt(i));

                        Game.players.elementAt(Game.currentPlayer).currentBangsInStep--;
                        return;
                    }
                }
                target.currentHealthPoints--;
                target.bullets[target.currentHealthPoints].visible = false;
                Game.players.elementAt(Game.currentPlayer).currentBangsInStep--;
                break;
            }
            case 6: // мимо
                break;
            case 7: { // пиво
                target.bullets[target.currentHealthPoints].visible = true;
                target.currentHealthPoints++;
                break;
            }
            case 8: // салун
                for (int i = 0; i <Game.currentPlayersNumber; i++) {
                    if(!Game.players.elementAt(i).maxHealthPoints.equals(Game.players.elementAt(i).currentHealthPoints)){
                        Game.players.elementAt(i).bullets[Game.players.elementAt(i).currentHealthPoints].visible = true;
                        Game.players.elementAt(i).currentHealthPoints++;
                    }
                }
                break;
            case 9: // дилижанс
                break;
            case 10: // Уэллс Фарго
                break;
            case 11: // магазин
                break;
            case 12: // паника
                break;
            case 13: // красотка
                break;
            case 14: { // гатлинг
                boolean flag = false;
                for (int i = 0; i < Game.currentPlayersNumber; i++) {
                    if (!Game.players.elementAt(i).equals(target)) {
                        for (int j = 0; j < Game.players.elementAt(i).deck.play_deck.size(); j++) {
                            if (Game.players.elementAt(i).deck.play_deck.elementAt(j).getId() == 6) {

                                CardAction tmp = new CardAction(Game.players.elementAt(i).deck.play_deck.elementAt(j).getId());
                                tmp.setPosition(ViewConst.discard_x,ViewConst.discard_y);
                                GameScreen.getStage().addActor(tmp);
                                Game.dis.add_in_discarded(tmp);
                                Game.players.elementAt(i).deck.delete_from_deck(Game.players.elementAt(i).deck.play_deck.elementAt(j));
                                GameScreen.group_actor[0].removeActor(Game.players.elementAt(i).deck.play_deck.elementAt(j));

                                flag=true;
                                break;
                            }
                        }
                       if(!flag) {
                            Game.players.elementAt(i).currentHealthPoints--;
                            Game.players.elementAt(i).bullets[Game.players.elementAt(i).
                                    currentHealthPoints].visible = false;
                        }
                        flag = false;
                    }
                }
                break;
            }
            case 15: { // индейцы
                boolean flag = false;
                for (int i = 0; i < Game.currentPlayersNumber; i++) {
                    if (!Game.players.elementAt(i).equals(target)) {
                      for (int j = 0; j < Game.players.elementAt(i).deck.play_deck.size(); j++) {
                            if (Game.players.elementAt(i).deck.play_deck.elementAt(j).getId() == 5) {

                                CardAction tmp = new CardAction(Game.players.elementAt(i).deck.play_deck.elementAt(j).getId());
                                tmp.setPosition(ViewConst.discard_x,ViewConst.discard_y);
                                GameScreen.getStage().addActor(tmp);
                                Game.dis.add_in_discarded(tmp);
                                Game.players.elementAt(i).deck.delete_from_deck(Game.players.elementAt(i).deck.play_deck.elementAt(j));
                                GameScreen.group_actor[0].removeActor(Game.players.elementAt(i).deck.play_deck.elementAt(j));

                                flag=true;
                                break;
                            }
                        }
                        if(!flag) {
                            Game.players.elementAt(i).currentHealthPoints--;
                            Game.players.elementAt(i).bullets[Game.players.elementAt(i).
                                    currentHealthPoints].visible = false;
                        }
                        flag = false;
                    }
                }
                break;
            }
            case 16: // дуэль
                break;
        }
    }
}
