package com.polymorphic_dissociation.mandala.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.polymorphic_dissociation.mandala.Utils;
import com.polymorphic_dissociation.mandala.model.Arm;
import com.polymorphic_dissociation.mandala.model.Canvas;
import com.polymorphic_dissociation.mandala.model.Model;
import com.polymorphic_dissociation.mandala.model.Segment;

import java.util.List;

public class MandalaRenderer {

    private Model model;
    private Canvas canvas;
    private Arm arm;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;

    public MandalaRenderer(Model model) {
        this.model = model;
        this.canvas = model.getCanvas();
        this.arm = model.getArm();
        this.batch = new SpriteBatch(100);
        this.shapeRenderer = new ShapeRenderer();
    }

    public void render() {

        batch.begin();

        int w = Gdx.graphics.getWidth();
        int h = Gdx.graphics.getHeight();
//        batch.draw(canvas.getTexture(), 0, 0, 480, 679);
        batch.draw(canvas.getTexture(), 0, 0, (int) (0.70696*h), h,
                0, 0, 2480, 3508, false, false);

        batch.end();

//        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//        shapeRenderer.setColor(Color.BLUE);
//        shapeRenderer.rect(0, 20, 100, 100);
//        shapeRenderer.end();


        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        Vector2 pos = new Vector2(1240, 1754);
        Vector2 pos2;
        List<Segment> segments = arm.getSegments();
        for (int i = 0; i < segments.size(); i++) {
            Segment s = segments.get(i);
            pos2 = pos.cpy();
            pos2.add(s.getVector());
//            shapeRenderer.setColor(Color.BLUE);
            Color c = Utils.getColorForIndex(i);
            shapeRenderer.setColor(c);
            Vector2 p1 = pos.cpy();
            p1.y = 3508 - p1.y;
//            p1.scl(0.1935f);
            p1.scl(h/3508f);
            Vector2 p2 = pos2.cpy();
            p2.y = 3508 - p2.y;
//            p2.scl(0.1935f);
            p2.scl(h/3508f);
            shapeRenderer.rectLine(p1, p2, 3f);
//            Gdx.app.log(Utils.TAG, "pos: " + pos + ", pos2: " + pos2 + ", p1: " + p1 + ", p2: " + p2);
            pos = pos2.cpy();
        }

        shapeRenderer.end();
    }

    public void dispose() {
        canvas.dispose();
    }
}
