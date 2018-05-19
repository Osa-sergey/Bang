package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.Vector;

public class GameBuilder {
    static Vector<Player> players = null; // массив игроков
    static Integer playersNumber = 4;// изначальное кол-во игроков
    static Integer currentPlayersNumber=4; //количество оставшихся игроков
    static Integer currentPlayer = 0; // тот, кто ходит сейчас
    static Pack pack = null; // колода
    static Discarded dis = null; // сброс
    static ClickListener play_card = null;
    static Player target = null;

    GameBuilder buildPlayers (Vector<Player> players){
        this.players = players;
        return this;
    }

    GameBuilder buildPack (Pack pack){
        this.pack = pack;
        return this;
    }

    GameBuilder buildDiscard (Discarded discarded){
        this.dis = discarded;
        return this;
    }

    Game build(){
        Game game = new Game();
        game.setDis(dis);
        game.setPack(pack);
        game.setPlayers(players);
        for (int i = 0; i <playersNumber; i++) {
            if(players.get(i).role.getRole() == CardRole.Roles.Sceriffo)
                currentPlayer=i;
        }
        Player tmp;
        for (int i = 0; i <playersNumber ; i++) {
            tmp = players.get(i);
            if(currentPlayer+i>=playersNumber) currentPlayer = 0;
            players.set(i,players.get(currentPlayer+i));
            players.set(currentPlayer+i,tmp);
        }
        return game;
    }
}