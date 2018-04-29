package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;

public class CardRole extends Card {
    private Roles role;
    enum Roles{
        Sceriffo, Fuorilegge, Rinnegato
    }
    //конструктор
    public CardRole(Integer id){
        super(id);
        switch (id){
            case 22:
                role=Roles.Sceriffo;
                break;
            case 23:
                role=Roles.Rinnegato;
                break;
            case 24:
                role=Roles.Fuorilegge;
                break;
        }
    }
    // посмотреть роль игрока
    public Roles getRole() {
        return role;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(role== Roles.Sceriffo) this.open = true;
        super.draw(batch, parentAlpha);
    }
    public void setRole(Roles role) {
        this.role = role;
    }
}
