package com.mygdx.game;

import com.badlogic.gdx.Gdx;

public class CardAction extends Card {

    public CardAction(Integer id) {
        super(id);
    }

    public void play_card_action(Player target){
        switch (super.getId()){
            case 5: { // бэнг
                target.currentHealthPoints--;
                break;
            }
            case 6: // мимо
                break;
            case 7: { // пиво
                target.currentHealthPoints++;
                break;
            }
            case 8: // салун
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
            case 14: // гатлинг
                break;
            case 15: // индейцы
                break;
            case 16: // дуэль
                break;
        }
     //   Game.dis.add_in_discarded(Game.players.elementAt(Game.currentPlayer).deck.play_deck.elementAt(Game.players.elementAt(Game.currentPlayer).deck.play_deck.indexOf(this)));
    }//уже скидывает карту в с
}
