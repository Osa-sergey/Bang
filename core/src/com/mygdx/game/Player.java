package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Actor;
import java.util.Vector;

public class Player extends Actor {
    // поля
    CardWeapon weapon; // оружие игрока
    CardPerson person; // персона игрока
    CardRole role; // рольигрока
    Integer maxHealthPoints; //максимальный запас здоровья
    Integer currentHealthPoints; //текущее количество здоровья
    Integer dist = 1; // то, на каком расстоянии находитесь вы для других
    Integer distBang = 0; // то, насколько далеко вы можите стрелять
    Integer bangsInStep = 0; //количество выстрелов за ход
    Integer currentBangsInStep; //количество оставшихся выстрелов
    Deck deck = null;//ваша рука
    Bullet[] bullets;
    Vector<CardEffect> effects = new Vector<CardEffect>(0); //набор карт эффектов
    //конструктор
    public Player(CardRole cardRole, CardPerson cardPerson,Pack pack,Bullet[] bullets){
        weapon = new CardWeapon(25); // создание базового оружия статы 1/1
        distBang = weapon.distBang;
        bangsInStep = weapon.bangsInStep;
        currentBangsInStep = bangsInStep;
        person = cardPerson;
        role = cardRole;
        maxHealthPoints = cardPerson.getHp();
        if(cardRole.getRole() == CardRole.Roles.Sceriffo) maxHealthPoints++;
        currentHealthPoints = maxHealthPoints;
        if(person.getPerson() == CardPerson.Persons.ElusiveJoe) dist=2;
        if(person.getPerson() == CardPerson.Persons.ColdBloodedRosie) distBang=2;
        if(person.getPerson() == CardPerson.Persons.BabyBilly) bangsInStep=1000;// будем считать, что любое количество карт Bang
        currentBangsInStep=bangsInStep;
        deck = new Deck(maxHealthPoints);
        for (int i = 0; i <maxHealthPoints; i++) {
            deck.add_in_deck(pack.pack_arr.pop());
        }
        this.bullets = bullets;

    }
    public void delete_player(Game game, boolean key){
        for (int i = 0; i <this.deck.play_deck.size(); i++) {
            game.dis.add_in_discarded(this.deck.play_deck.elementAt(i));
        }
        if(this.weapon.bangsInStep!=1 && this.weapon.distBang!=1){
            game.dis.add_in_discarded(this.weapon);
        }//удаление не базового оружия
        game.players.removeElementAt(game.players.indexOf(this));//удаляем игрока из списка игроков
        if(key){
            game.next_turn();//передаём ход
        }
        game.players.trimToSize();
        game.currentPlayersNumber--;
    } //удаляет все карты игрока переводит хода по ключу
    //проверка возможности сыграть карту
    private boolean can_play_card(Integer id){
        switch (id){
            case 0:
            case 1:
           // case 2:
           /* case 4:*/{
                for (int i = 0; i <effects.size(); i++) {
                    if(effects.get(i).getId().equals(id))
                        return false;
                }
                return true;
            }
          /*  case 3:{
                for (int i = 0; i <effects.size(); i++) {
                    if(effects.get(i).getId().equals(id))
                        return false;
                }
                if(role.getRole().equals(CardRole.Roles.Sceriffo)) return false;
                return true;
            }
        */  case 5:{
                if(Game.players.elementAt(Game.currentPlayer).currentBangsInStep!=0)
                {
                    return true;
                }
                else return false;
            }
            case 6:{
                return false;
            }
            case 7:{
                if((Game.currentPlayersNumber>2)&&(Game.players.elementAt(Game.currentPlayer).currentHealthPoints<Game.players.elementAt(Game.currentPlayer).maxHealthPoints)){
                    return true;
                }else return false;
            }
            case 8:{
                for (int i = 0; i <Game.currentPlayersNumber ; i++) {
                    if(!Game.players.elementAt(i).maxHealthPoints.equals(Game.players.elementAt(i).currentHealthPoints)) return true;
                }
                return false;
            }
            case 9:{
                if(Game.players.elementAt(Game.currentPlayer).deck.play_deck.size()+2<=16)
                    return true;
                else return false;
            }
            case 10:{
                if(Game.players.elementAt(Game.currentPlayer).deck.play_deck.size()+3<=16)
                    return true;
                else return false;
            }
           /* case 12:{
                int cur = 0;
                for (int i = 0; i <game.currentPlayersNumber ; i++) {
                    if (game.players.elementAt(i).equals(this)) {
                        cur=i;
                        break;
                    }
                }
                int dist=Math.abs(cur-game.currentPlayer);
                if(dist>=game.currentPlayersNumber-1){
                    dist-=game.currentPlayersNumber-2;
                }
                if(dist+this.dist>game.players.elementAt(game.currentPlayer).distBang) return false;
                if((this.weapon.bangsInStep!=1 && this.weapon.distBang!=1)||
                        (this.effects.size()!=0) ||
                        (!this.deck.play_deck.isEmpty())) return true;
                else return false;
            }*/
           /* case 13:{
                if((this.weapon.bangsInStep!=1 && this.weapon.distBang!=1)||
                        (this.effects.size()!=0) ||
                        (!this.deck.play_deck.isEmpty())) return true;
                else return false;
            } */
            default: return true;
        }
    }
    public boolean play_card(Card play,Player target) {
        if (can_play_card(play.getId())) {
            if (play instanceof CardWeapon) {
                System.out.println(this.bangsInStep);
                if (!(this.weapon.distBang == 1 && this.weapon.bangsInStep == 1)) {
                    CardWeapon tmp = new CardWeapon(this.weapon.getId());
                    tmp.setPosition(ViewConst.discard_x,ViewConst.discard_y);
                    GameScreen.getStage().addActor(tmp);
                    tmp.anset_weapon(this);
                    System.out.println(bangsInStep);
                    Game.dis.add_in_discarded(tmp);
                    GameScreen.group_actor[0].removeActor(this.weapon);
                }else {
                    this.weapon.anset_weapon(this);
                    System.out.println(this.bangsInStep);
                }
                CardWeapon tmp = new CardWeapon(play.getId());
                tmp.setPosition(ViewConst.player_line_left_margin+(ViewConst.card_width+ViewConst.player_line_hor_margin)*2,ViewConst.player_line_y);
                GameScreen.group_actor[0].addActor(tmp);
                tmp.set_card_weapon(this);
                this.deck.delete_from_deck(play);
                GameScreen.group_actor[0].removeActor(play);
                System.out.println(this.bangsInStep);

            } else if (play instanceof CardAction) {
                ((CardAction) play).play_card_action(target);
            /*
            блок отвечающий за добавление карты в сброс и удаления с руки
            */
                CardAction tmp = new CardAction(play.getId());
                tmp.setPosition(ViewConst.discard_x,ViewConst.discard_y);
                GameScreen.getStage().addActor(tmp);
                Game.dis.add_in_discarded(tmp);
                this.deck.delete_from_deck(play);
                GameScreen.group_actor[0].removeActor(play);


            } else if (play instanceof CardEffect) {
                ((CardEffect) play).set_anset_card_effect(this, true);
            }
            if(GameScreen.prev_card.visible){
                for (int i = ViewConst.deck_cardsVisible_number+1; i <Game.players.elementAt(Game.currentPlayer).deck.play_deck.size(); i++) {
                    Game.players.elementAt(Game.currentPlayer).deck.play_deck.elementAt(i).visible = true;
                    Game.players.elementAt(Game.currentPlayer).deck.play_deck.elementAt(i).setPosition(ViewConst.deck_card_x_newStart + (i-2-ViewConst.deck_cardsVisible_number)* ViewConst.deck_card_hor,ViewConst.deck_card_y);
                }
                if(Game.players.elementAt(Game.currentPlayer).deck.play_deck.size()<=ViewConst.deck_cardsVisible_number+1){
                    for (int i = 0; i <Game.players.elementAt(Game.currentPlayer).deck.play_deck.size(); i++) {
                        Game.players.elementAt(Game.currentPlayer).deck.play_deck.elementAt(i).visible = true;
                        Game.players.elementAt(Game.currentPlayer).deck.play_deck.elementAt(i).
                                setPosition(ViewConst.deck_card_x_start + i * ViewConst.deck_card_hor, ViewConst.deck_card_y);
                    }
                }
            }
            if(GameScreen.next_card.visible){
                for (int i = 0; i <Game.players.elementAt(Game.currentPlayer).deck.play_deck.size(); i++) {
                    if(i<=ViewConst.deck_cardsVisible_number) {
                        Game.players.elementAt(Game.currentPlayer).deck.play_deck.elementAt(i).visible = true;
                        Game.players.elementAt(Game.currentPlayer).deck.play_deck.elementAt(i).
                                setPosition(ViewConst.deck_card_x_start + i * ViewConst.deck_card_hor, ViewConst.deck_card_y);
                    }else break;
                }
            }
            if(!GameScreen.next_card.visible && !GameScreen.prev_card.visible){
                for (int i = 0; i <Game.players.elementAt(Game.currentPlayer).deck.play_deck.size(); i++) {
                    Game.players.elementAt(Game.currentPlayer).deck.play_deck.elementAt(i).visible = true;
                    Game.players.elementAt(Game.currentPlayer).deck.play_deck.elementAt(i).
                            setPosition(ViewConst.deck_card_x_start + i * ViewConst.deck_card_hor, ViewConst.deck_card_y);
                }
            }
            if(Game.players.elementAt(Game.currentPlayer).deck.play_deck.size()<=ViewConst.deck_cardsVisible_number+1){
                GameScreen.next_card.visible = false;
                GameScreen.prev_card.visible = false;
            }
            for (int i = 0; i <Game.currentPlayersNumber ; i++) {
                if(Game.players.elementAt(i).currentHealthPoints==0){
                    Game.players.elementAt(i).person.setId(99);
                    Game.players.elementAt(i).role.open = true;
                }
            }
            return true;
        } else return false;
    }
}
