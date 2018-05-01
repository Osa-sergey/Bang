package com.mygdx.game;

import java.util.Vector;

public class Deck {
    Vector<Card> play_deck;//дека игрока

    //конструктор
    public Deck(Integer cpt){
        play_deck= new Vector<Card>(cpt);
    }

    public void add_in_deck(Card input){
        input.setRotation(0);
        play_deck.add(input);
    }//добавление карты без проверки
    public void add_in_deck(Integer quantity){
        for (int i = 0; i <quantity; i++) {
            if(Game.pack.pack_arr.empty()){
                Game.dis.reset_pack(Game.pack);
            }
            add_in_deck(Game.pack.pack_arr.pop());
        }
    }
    public void delete_from_deck(Card card){
        if(play_deck.indexOf(card)!=-1) {
            play_deck.removeElementAt(play_deck.indexOf(card));
            play_deck.trimToSize();
        }
    }
}
