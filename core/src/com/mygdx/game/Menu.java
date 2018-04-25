package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;


public class Menu implements Screen {

    static private MyGdxGame game;
    private Stage stage;
    private Button[] buttons;
    Sprite table;

    static void switchScreen(Screen screen){
        game.setScreen(screen);
    }
    public Menu(MyGdxGame game) {
        this.game = game;
        stage = new Stage(MyGdxGame.getViewport());
        table = new Sprite(Assets.get_texture_region("back"));
        table.setSize(stage.getWidth(),stage.getHeight());
        if(buttons == null) {
            buttons = new Button[4];
            for (int i = 0; i < 4; i++) {
                buttons[i] = new Button(i+1,game,null);
            }
            for (int i = 0; i < 3; i++) {
                buttons[i].setSize(ViewConst.menu_btn_width, ViewConst.menu_btn_height);
                buttons[i].setPosition(1920 - ViewConst.menu_btn_marginRight - ViewConst.menu_btn_width, 1080 - ViewConst.menu_btn_marginTop - ViewConst.menu_btn_height * (i+1));
            }
            buttons[3].setSize(ViewConst.menu_img_width, ViewConst.menu_img_height);
            buttons[3].setPosition(ViewConst.menu_img_x,ViewConst.menu_img_y);
            for (int i = 0; i <4; i++) {
                stage.addActor(buttons[i]);
            }
        }
    }

    public Stage getStage() {
        return stage;
    }


    public Button[] getButtons() {
        return buttons;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
       Gdx.gl20.glCheckFramebufferStatus(GL20.GL_COLOR_BUFFER_BIT);
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
        stage.clear();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
