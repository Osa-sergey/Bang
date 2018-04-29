package com.mygdx.game;
import java.util.Random;

public class Discarded {
    // поля
    Card[] dis_arr; // массив сброшенных карт
    static Integer counter = 0; // индек первого свободного места в массиве
    // конструктор
    public Discarded(){
        dis_arr = new Card[80];

    }
    private void random_dis(){
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i <dis_arr.length; i++) {
            Integer change=random.nextInt(dis_arr.length-1);
            Card tmp=dis_arr[i];
            dis_arr[i]=dis_arr[change];
            dis_arr[change]=tmp;
        }
    } //перемешиваем карты перед сменой колоды
    public void reset_pack(Pack pack){
        this.random_dis();
        for (int i = 0; i <counter ; i++) {
            dis_arr[i].setPosition(ViewConst.pack_x,ViewConst.pack_y);
            pack.pack_arr.push(dis_arr[i]);
            dis_arr[i]=null;
        }
        counter=0;
    } //сменяем колоду
    public void add_in_discarded(Card card){
        dis_arr[counter]=card;
        card.setRotation(ViewConst.discard_r);
        card.open = false;
        counter++;
    }//добавление карты в сброс
}
