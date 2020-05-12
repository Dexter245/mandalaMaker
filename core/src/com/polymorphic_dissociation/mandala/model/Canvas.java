package com.polymorphic_dissociation.mandala.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.Texture;
import com.polymorphic_dissociation.mandala.Utils;

public class Canvas {

    private static final int STROKE_WIDTH = 10;
//    private static final float SCALE = 5.166667f;
    private static final float SCALE = 2.58333f;

    private Pixmap pixmap;
    private Pixmap pixmapSmall;
    private Texture texture;

    public Canvas(int width, int height){
        this.pixmap = new Pixmap(width, height, Pixmap.Format.RGB888);
//        this.pixmapSmall = new Pixmap(480, 679, Pixmap.Format.RGB888);
        this.pixmapSmall = new Pixmap(960, 1358, Pixmap.Format.RGB888);
        this.pixmapSmall.setFilter(Pixmap.Filter.NearestNeighbour);
        this.texture = new Texture(this.pixmapSmall);
        clear();
    }

    public void drawPixel(int x, int y){
        pixmap.fillRectangle(x, y, STROKE_WIDTH, STROKE_WIDTH);
        pixmapSmall.fillRectangle((int) (x/SCALE), (int) (y/SCALE), (int) (STROKE_WIDTH/SCALE),
                (int) (STROKE_WIDTH/SCALE));
    }

    public void clear(){
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        pixmap.setColor(Color.BLACK);
        pixmapSmall.setColor(Color.WHITE);
        pixmapSmall.fill();
        pixmapSmall.setColor(Color.BLACK);
    }

    public void update(){
//        pixmapSmall.drawPixmap(pixmap, 0, 0, pixmap.getWidth(), pixmap.getHeight(),
//                0, 0, 480, 679);
        texture.draw(pixmapSmall, 0, 0);
    }

    public void saveToFile(FileHandle file){
        PixmapIO.writePNG(file, pixmap);
    }

    public Texture getTexture(){
        return texture;
    }

    public void dispose(){
        pixmap.dispose();
        pixmapSmall.dispose();
        texture.dispose();
    }

}
