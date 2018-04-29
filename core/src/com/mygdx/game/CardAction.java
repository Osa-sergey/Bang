package com.mygdx.game;

public class CardAction extends Card {

    public CardAction(Integer id) {
        super(id);
    }
    // выполнение действия карты типа Action
    public void play_card_action(Player target){
        switch (super.getId()){
            // бэнг
            case 5: {
                /*
                проверка наличия карты мимо у цели и скидывания этой карты в случае нахождения
                 */
                for (int i = 0; i <target.deck.play_deck.size() ; i++) {
                    if(target.deck.play_deck.elementAt(i).getId()==6){

                        //скидывание в сброс карты мимо
                        CardAction tmp = new CardAction(target.deck.play_deck.elementAt(i).getId());
                        tmp.setPosition(ViewConst.discard_x,ViewConst.discard_y);
                        GameScreen.getStage().addActor(tmp);
                        Game.dis.add_in_discarded(tmp);
                        target.deck.delete_from_deck(target.deck.play_deck.elementAt(i));
                        GameScreen.group_actor[0].removeActor(target.deck.play_deck.elementAt(i));

                        //уменьшение кол-ва оставшихся выстрелов в этот ход
                        Game.players.elementAt(Game.currentPlayer).currentBangsInStep--;
                        return;
                    }
                }
                /*
                //уменьшаем здоровье цели
                //отображаем уменьшение
                //уменьшение кол-ва оставшихся выстрелов в этот ход
                 */
                target.currentHealthPoints--;
                target.bullets[target.currentHealthPoints].visible = false;
                Game.players.elementAt(Game.currentPlayer).currentBangsInStep--;
                break;
            }
            // мимо
                //данная карта не игрвется
            case 6:
                break;
            case 7: { // пиво
                //отображаем увеличение хп
                target.bullets[target.currentHealthPoints].visible = true;
                target.currentHealthPoints++;
                break;
            }
            case 8: // салун
                /*
                увеличиваем кол-во хп если их кол-во у игрока не максимально
                 */
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
                /*
                уменьшаем кол-во хп если нет карты мимо
                 */
                boolean flag = false;
                for (int i = 0; i < Game.currentPlayersNumber; i++) {
                    if (!Game.players.elementAt(i).equals(target)) {
                        for (int j = 0; j < Game.players.elementAt(i).deck.play_deck.size(); j++) {
                            if (Game.players.elementAt(i).deck.play_deck.elementAt(j).getId() == 6) {

                                //сбрасываем карту мимо
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
                        // если карты мимо у игрока нет, то уменьшаем его хп
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
                /*
                аналогично гатлингу, только скидывается карта бэнг
                 */
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
