package com.mygdx.game;

public class CardWeapon extends Card {

    Integer distBang = 1;
    Integer bangsInStep = 1;

    /*methods*/
    // создание оружий игрока в зависимости от id
    public CardWeapon(Integer id){
        super(id);
        switch (id){
            case 17: // ремингтон
                distBang=3;
                break;
            case 18: // карабин
                distBang=4;
                break;
            case 19: // винчестер
                distBang=5;
                break;
            case 20: // скофилд
                distBang=2;
                break;
            case 21: // волканик
                bangsInStep = 1000;
                break;
            case 25://базовое оружие
                break;
        }
    }
    //поставить карту оружия
    public void set_card_weapon(Player player){
        player.distBang +=this.distBang;
        player.bangsInStep +=this.bangsInStep;
        if(player.currentBangsInStep!=0){
            player.currentBangsInStep=player.bangsInStep;
        }
        player.weapon=this;
    }
    //убрать карту оружия
    public void anset_weapon(Player player, Game game){
        player.distBang -=this.distBang;
        player.bangsInStep -=this.bangsInStep;
    }
}
