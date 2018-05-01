package com.mygdx.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
    private AssetManager assetManager;
    /*
    класс отвечает за отрисовку кусков изображения из атласа
     */
    private static TextureAtlas atlas;
    public Assets (){
        assetManager = new AssetManager();
        assetManager.load("Texturepack.atlas", TextureAtlas.class);

        assetManager.finishLoading();
        atlas = assetManager.get("Texturepack.atlas");
    }

    public static TextureRegion get_texture_region (String name){
        return atlas.findRegion(name);
    }

}
