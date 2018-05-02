package com.mygdx.game;

import java.util.Stack;

public class Pack {
    Stack<Card> pack_arr; //массив карт колоды
    //конструктор
    public Pack(Integer[] id){
        pack_arr = new Stack<Card>();
        for (int i = 0; i <id.length; i++) {
            add_in_Pack(id[i]);
        }
    }
    private void add_in_Pack(Integer id){
        switch (id){
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
                CardAction newCardAction = new CardAction(id);
                newCardAction.setPosition(ViewConst.pack_x,ViewConst.pack_y);
                newCardAction.setRotation(ViewConst.pack_r);
                pack_arr.push(newCardAction);
                return;
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
                /*карты эффекты*/
                CardEffect newCardEffect = new CardEffect(id);
                newCardEffect.setPosition(ViewConst.pack_x,ViewConst.pack_y);
                newCardEffect.setRotation(ViewConst.pack_r);
                pack_arr.push(newCardEffect);
                return;
            default:
                /*карты оружие*/
                CardWeapon newCardWeapon = new CardWeapon(id);
                newCardWeapon.setPosition(ViewConst.pack_x,ViewConst.pack_y);
                newCardWeapon.setRotation(ViewConst.pack_r);
                pack_arr.push(newCardWeapon);
        }
    }// добавление в pack новых карт при создании
   /* public Card look_card(Game game){
        if(pack_arr.empty()){
            game.dis.reset_pack(this);
        }
        pack_arr.peek();
        return pack_arr.pop();
    }//удаляет карту из колоды просматривая её
*/}
