package com.mygdx.game;

public class CardEffect extends Card {
    public CardEffect(Integer id) {
        super(id);
    }
    public void set_anset_card_effect(Player player,boolean flag){
        switch (this.getId()){
            case 0: // мустанг
                if (flag) {
                    player.dist++;
                } else {
                    player.dist--;
                }
                break;
            case 1: // прицел
                if (flag) {
                    player.distBang++;
                } else {
                    player.distBang--;
                }
                break;
        }
        if(flag){
            player.effects.add(this);
        }
        else{
            player.effects.removeElementAt(player.effects.indexOf(this));
            player.effects.trimToSize();
        }
    }
    public Card discard_card_effect(Player player){
        set_anset_card_effect(player,false);
        //todo перерисовывать список эффектов
        return this;
    }//удаляет карту из списка эффектов и возвращает её
}
