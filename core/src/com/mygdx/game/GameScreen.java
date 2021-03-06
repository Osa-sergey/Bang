package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class GameScreen implements Screen {
/*
класс считывающий из файла данные и перемешивающий их
*/
    class Assistant {
    Assistant() {}

        Integer[] scan(Scanner in, Integer[] input, String src) {
            try {
                in = new Scanner(new File(src));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            while (in.hasNext()) {
                for (int i = 0; i < input.length; i++) {
                    input[i] = in.nextInt();
                }
            }
            Random random = new Random(System.currentTimeMillis());
            for (int i = 0; i < input.length; i++) {
                Integer change = random.nextInt(input.length - 1);
                Integer tmp = input[i];
                input[i] = input[change];
                input[change] = tmp;
            }
            return input;
        }
    }
/*
    поля класса
*/
    static private Stage stage;

    public static MyGdxGame getGame() {
        return game;
    }

    static private MyGdxGame game;
    static boolean isBangActive =false;
    Sprite table;
    Button next;
    Button submit;
    static Button next_card;
    static Button prev_card;
    Discarded discarded;
    Pack pack;
    Integer [] cards;
    Scanner in;
    Assistant assistant;
    Vector<Player> players;
    Integer personId[];
    Integer roleId[];
    Game game_;
    Discarded discard;
    static Group group_actor[];
    Base[] bases;
    static Card tmpCard = null;

    //конструктор
    public GameScreen (MyGdxGame game){
        assistant = new Assistant();
        this.game = game;
        stage = new Stage(MyGdxGame.getViewport());
        table = new Sprite(Assets.get_texture_region("table"));
        table.setSize(stage.getWidth(),stage.getHeight());
        GameBuilder gameBuilder = new GameBuilder();
        /*
        создание кнопок
         */
        next = new Button(5,game,this);
        next.setSize(ViewConst.btnNext_width,ViewConst.btnNext_height);
        next.setPosition(ViewConst.btnNext_x,ViewConst.btnNext_y);
        stage.addActor(next);
        submit = new Button(6,game,this);
        submit.setSize(ViewConst.btnSubmit_width,ViewConst.btnSubmit_height);
        submit.setPosition(ViewConst.btnSubmit_x,ViewConst.btnSubmit_y);
        submit.visible = false;
        stage.addActor(submit);
        next_card = new Button(7,game,this);
        next_card.setSize(ViewConst.btnScrollNext_width,ViewConst.btnScrollNext_height);
        next_card.setPosition(ViewConst.btnScrollNext_x,ViewConst.btnScrollNext_y);
        next_card.visible = false;
        stage.addActor(next_card);
        prev_card = new Button(8,game,this);
        prev_card.setSize(ViewConst.btnScrollPrev_width,ViewConst.btnScrollPrev_height);
        prev_card.setPosition(ViewConst.btnScrollPrev_x,ViewConst.btnScrollPrev_y);
        prev_card.visible = false;
        stage.addActor(prev_card);
        /*
        создание сброса и колоды
         */
        discarded = new Discarded();
        gameBuilder.buildDiscard(discarded);
        cards = new Integer[61];
        cards = assistant.scan(in,cards,"id.txt");

        pack = new Pack(cards);
        for (int i = 0; i <61 ; i++) {
            stage.addActor(pack.pack_arr.elementAt(i));
            pack.pack_arr.elementAt(i).open=false;
        }
        gameBuilder.buildPack(pack);
        /*
        создание игроков
         */
        players = new Vector<Player>(4);

        personId =  new Integer[4];
        personId = assistant.scan(in,personId,"person_id.txt");

        roleId = new Integer[4];
        roleId = assistant.scan(in,roleId,"role_id.txt");
        for (int i = 0; i <4; i++) {
            Bullet[] bullets1= new Bullet[5];
            for (int j = 0; j <5; j++) {

                bullets1[j] = new Bullet();
                bullets1[j].setPosition(ViewConst.bul_line_start_x+ViewConst.bul_line_hor_margin*j,
                        ViewConst.bul_line_start_y);
            }
            CardPerson person = new CardPerson(personId[i]);
            CardRole role = new CardRole(roleId[i]);
            Player player = new Player(role,person,pack,bullets1);
            players.add(player);
        }
        gameBuilder.buildPlayers(players);
        /*
        создание класса игры
         */
        game_ = gameBuilder.build();

        /*
        создание графического отображения игроков
         */
        group_actor = new Group[4];
        bases = new Base[4];
        for (int i = 0; i <4 ; i++) {
            group_actor[i] = new Group();
            bases[i] = new Base(i+1);
            bases[i].setPosition(0,0);
        }
        group_actor[0].setPosition(ViewConst.pr0_x,ViewConst.pr0_y);
        group_actor[1].setPosition(ViewConst.pr1_x,ViewConst.pr1_y);
        group_actor[1].setRotation(ViewConst.pr1_r);
        group_actor[2].setPosition(ViewConst.pr2_x,ViewConst.pr2_y);
        group_actor[2].setRotation(ViewConst.pr2_r);
        group_actor[3].setPosition(ViewConst.pr3_x,ViewConst.pr3_y);
        group_actor[3].setRotation(ViewConst.pr3_r);

        for (int i = 0; i <4; i++) {
            group_actor[i].addActor(bases[i]);
            for (int j = 0; j <5 ; j++) {
                group_actor[i].addActor(players.elementAt(i).bullets[j]);
            }
        }
        for (int i = 0; i <4; i++) {
            for (int j = 0; j < players.elementAt(i).deck.play_deck.capacity(); j++) {
                if(j>ViewConst.deck_cardsVisible_number){
                    players.elementAt(i).deck.play_deck.elementAt(j).setPosition(ViewConst.screen_width+(j-1-ViewConst.
                            deck_cardsVisible_number)*ViewConst.deck_card_hor,ViewConst.deck_card_y);
                }else
                players.elementAt(i).deck.play_deck.elementAt(j).setPosition(ViewConst.deck_card_x_start+j*ViewConst.deck_card_hor,ViewConst.deck_card_y);
                players.elementAt(i).deck.play_deck.elementAt(j).open = true;
                group_actor[i].addActor(players.elementAt(i).deck.play_deck.elementAt(j));
                if(j>ViewConst.deck_cardsVisible_number)
                players.elementAt(i).deck.play_deck.elementAt(j).visible = false;
            }
            //описание текущего игрока
            if(i!=0){
                for (int j = 0; j <players.elementAt(i).deck.play_deck.capacity() ; j++) {
                    players.elementAt(i).deck.play_deck.elementAt(j).visible =false;
                }
            }
            for (int j = 0; j <5 ; j++) {
                if(players.elementAt(i).currentHealthPoints-1<j){
                    players.elementAt(i).bullets[j].visible = false;
                }
            }
            players.elementAt(i).role.setPosition(ViewConst.player_line_left_margin,ViewConst.player_line_y);
            players.elementAt(i).person.setPosition(ViewConst.player_line_left_margin+ViewConst.card_width+ ViewConst.player_line_hor_margin,ViewConst.player_line_y);
            players.elementAt(i).weapon.setPosition(ViewConst.player_line_left_margin+(ViewConst.card_width+ViewConst.player_line_hor_margin)*2,ViewConst.player_line_y);
            group_actor[i].addActor(players.elementAt(i).person);
            group_actor[i].addActor(players.elementAt(i).role);
            if(i!=0){
                players.elementAt(i).role.cover=false;
                players.elementAt(i).role.open=false;
            }
            stage.addActor(group_actor[i]);
        }
        Game.players.elementAt(Game.currentPlayer).deck.add_in_deck(2);
        for (int i =Game.players.elementAt(Game.currentPlayer).deck.play_deck.size()-2 ; i <Game.players.elementAt(Game.currentPlayer).deck.play_deck.size() ; i++) {
            Game.players.elementAt(Game.currentPlayer).deck.play_deck.elementAt(i).open = true;
            Game.players.elementAt(Game.currentPlayer).deck.play_deck.elementAt(i).setPosition(-500,-500);
            GameScreen.group_actor[0].addActor(Game.players.elementAt(Game.currentPlayer).deck.play_deck.elementAt(i));
        }
    }
    public static Stage getStage() {
        return stage;
    }
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }
    @Override
    public void render(float delta) {
        /*
        проверка на показывание стрелок скрола колоды
         */
        if(Game.players.elementAt(Game.currentPlayer).deck.play_deck.size()>ViewConst.deck_cardsVisible_number+1
                && !prev_card.visible
                && !prev_card.inProcess
                && !next_card.inProcess)
            next_card.visible = true;
        if(Game.players.elementAt(Game.currentPlayer).deck.play_deck.size()<=ViewConst.deck_cardsVisible_number+1
                && prev_card.inProcess
                && next_card.inProcess){
            next_card.visible = false;
            prev_card.visible = false;
        }
        /*
        очистка экрана и отрисовка изображения
         */
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl20.glClearColor(0, 0, 0, 0);
        stage.getBatch().begin();
        table.draw(stage.getBatch());
        stage.getBatch().end();
        stage.draw();
    }
    @Override
    public void resize(int width, int height) {
        stage.getViewport().setScreenSize(width,height);
    }
    @Override
    public void pause() {

    }
    @Override
    public void resume() {

    }
    @Override
    public void hide() {

    }
    @Override
    public void dispose() {
        stage.dispose();
    }
}
