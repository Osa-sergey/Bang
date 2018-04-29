package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.Vector;

public class Game {
   static Vector<Player> players; // массив игроков
   static Integer playersNumber = 4;// изначальное кол-во игроков
   static Integer currentPlayersNumber=4; //количество оставшихся игроков
   static Integer currentPlayer = 0; // тот, кто ходит сейчас
   static Pack pack; // колода
   static Discarded dis; // сброс
   static ClickListener play_card = null;
   static Player target = null;

    //конструктор
    public Game(Vector<Player> play,Pack pack,Discarded discard){
        players=play;
        this.pack=pack;
        dis=discard;
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
        currentPlayer = 0;
    }
    public void next_turn(){
        if(currentPlayer==0){
            currentPlayer=currentPlayersNumber-1;
        }else{
            currentPlayer--;
        }
        players.elementAt(currentPlayer).currentBangsInStep = players.elementAt(currentPlayer).weapon.bangsInStep;
        players.elementAt(currentPlayer).distBang = players.elementAt(currentPlayer).weapon.distBang;
    }
    public boolean is_game_over(){
        boolean Serif = false;
        boolean Rinigat = false;
        boolean Fuoril = false;
        for (int i = 0; i <players.size() ; i++) {
            if(players.elementAt(i).role.getRole()==CardRole.Roles.Sceriffo) Serif=true;
            if(players.elementAt(i).role.getRole()==CardRole.Roles.Rinnegato) Rinigat=true;
            if(players.elementAt(i).role.getRole()==CardRole.Roles.Fuorilegge) Fuoril=true;
        }
        if(Serif==true && Rinigat==false && Fuoril==false){
            //todo выиграл шриф показать его выигрыш
            return true;
        }else if(Serif==false && Fuoril==true){
            //todo выиграли бандиты показать выигрыш
            return true;
        }else if(Serif==false && Fuoril==false && Rinigat==true){
            //todo выиграл ренигат показать выигрыш
            return true;
        }else return false;

    }
}
