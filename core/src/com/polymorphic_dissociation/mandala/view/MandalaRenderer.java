package com.polymorphic_dissociation.mandala.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.polymorphic_dissociation.mandala.Utils;
import com.polymorphic_dissociation.mandala.model.Arm;
import com.polymorphic_dissociation.mandala.model.Canvas;
import com.polymorphic_dissociation.mandala.model.Segment;

public class MandalaRenderer {

    private Canvas canvas;
    private Arm arm;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;

    public MandalaRenderer(Canvas canvas, Arm arm){
        this.canvas = canvas;
        this.arm = arm;
        this.batch = new SpriteBatch(100);
        this.shapeRenderer = new ShapeRenderer();
    }

    public void render(){

        batch.begin();

        batch.draw(canvas.getTexture(), 0, 20, 480, 679);

        batch.end();

//        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//        shapeRenderer.setColor(Color.BLUE);
//        shapeRenderer.rect(0, 20, 100, 100);
//        shapeRenderer.end();


        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        Vector2 pos = new Vector2(1240, 1754);
        Vector2 pos2;
        for(Segment s : arm.getSegments()){
            pos2 = pos.cpy();
            pos2.add(s.getVector());
            shapeRenderer.setColor(Color.BLUE);
            Vector2 p1 = pos.cpy();
            p1.y = 3508 - p1.y;
            p1.scl(0.1935f).add(0, 20);
            Vector2 p2 = pos2.cpy();
            p2.y = 3508 - p2.y;
            p2.scl(0.1935f).add(0, 20);
            shapeRenderer.line(p1, p2);
            Gdx.app.log(Utils.TAG, "pos: " + pos + ", pos2: " + pos2 + ", p1: " + p1 + ", p2: " + p2);
            pos = pos2.cpy();
        }

        shapeRenderer.end();
    }

    public void dispose(){
        canvas.dispose();
    }
}
