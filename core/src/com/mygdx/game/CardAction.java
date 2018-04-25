package com.mygdx.game;

public class CardAction extends Card {

    public CardAction(Integer id) {
        super(id);
    }

    public void play_card_action(Game game,Player target){
        switch (super.getId()){
            case 5: // бэнг
                break;
            case 6: // мимо
                break;
            case 7: { // пиво
                target.currentHealthPoints++;
                //todo перерисовать кол-во hp
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
        game.dis.add_in_discarded(game.players.elementAt(game.currentPlayer).deck.play_deck.elementAt(game.players.elementAt(game.currentPlayer).deck.play_deck.indexOf(this)));
    }//уже скидывает карту в с
}
