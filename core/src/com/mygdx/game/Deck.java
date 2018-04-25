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
        //todo показать карту игроку
        //todo перерисовать список карт
    }//добавление карты без проверки
    public void add_in_deck(Game game,Integer quantity){
        for (int i = 0; i <quantity; i++) {
            if(game.pack.pack_arr.empty()){
                game.dis.reset_pack(game.pack);
            }
            add_in_deck(game.pack.pack_arr.pop());
        }
    }//добавление карт в deck
    public void delete_from_deck(Card card){
        play_deck.removeElementAt(play_deck.indexOf(card));
        play_deck.trimToSize();
    }
}
