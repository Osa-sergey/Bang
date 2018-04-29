package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class CardPerson extends Card{

    private Persons person;//имя персоны
    private Integer hp = 4;//кол-во hp
    private Integer id;
    // список персон
    enum Persons{
        AngelicEyes,
        PoorJane,
        CrazyDog,
        BigSerpent,
        ButchCassidy,
        LuckyLuke,
        Django,
        JesseJames,
        KeithCarson,
        BabyBilly,
        ElusiveJoe,
        SusieLafayette,
        TomKetchum,
        Tuco,
        ColdBloodedRosie,
        ManWithoutName,
        UncleWill,
        JohnnyKish
    }
    // конструктор
    public CardPerson(final Integer id) {
        super(id);
        this.id = id;
        switch (id){
            case 106:
            case 110: hp = 3;
        }
        switch (id){
            case 100:
                person=Persons.AngelicEyes;
                break;
            case 101:
                person=Persons.PoorJane;
                break;
            case 102:
                person=Persons.CrazyDog;
                break;
            case 103:
                person=Persons.BigSerpent;
                break;
            case 104:
                person=Persons.ButchCassidy;
                break;
            case 105:
                person=Persons.LuckyLuke;
                break;
            case 106:
                person=Persons.Django;
                break;
            case 107:
                person=Persons.JesseJames;
                break;
            case 108:
                person=Persons.KeithCarson;
                break;
            case 109:
                person=Persons.BabyBilly;
                break;
            case 110:
                person=Persons.ElusiveJoe;
                break;
            case 111:
                person=Persons.SusieLafayette;
                break;
            case 112:
                person=Persons.TomKetchum;
                break;
            case 113:
                person=Persons.Tuco;
                break;
            case 114:
                person=Persons.ColdBloodedRosie;
                break;
            case 115:
                person=Persons.ManWithoutName;
                break;
            case 116: // дополнительные
                person=Persons.UncleWill;
                break;
            case 117: //дополнительные
                person=Persons.JohnnyKish;
                break;
        }
        // обработчик нажатий
        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // проверка области нажатия
               if(getX()>=ViewConst.player_person_x
                        && getX()<=ViewConst.player_person_x+ViewConst.card_width
                        && getY()>=ViewConst.player_person_y
                        &&getY()<=ViewConst.player_person_y+ViewConst.card_height
                       ){
                   if(!getId().equals(Game.players.elementAt(Game.currentPlayer).person.getId())){
                       for (int i = 0; i <Game.currentPlayersNumber ; i++) {
                           //установка цели
                           if(getId().equals(Game.players.elementAt(i).person.getId())){
                               Game.target = Game.players.elementAt(i);
                               //если мы играем карту бэнг, то вызываем фун-ию сыгрывания
                               if(GameScreen.isBangActive){
                                   Game.players.elementAt(Game.currentPlayer).play_card(GameScreen.tmpCard,Game.target);
                                   GameScreen.isBangActive = false;
                               }
                           }
                       }
                   }
               }
            }
        });
    }

    public Persons getPerson() {
        return person;
    }
    public Integer getHp() {
        return hp;
    }
}