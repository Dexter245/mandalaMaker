package com.polymorphic_dissociation.mandala;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.polymorphic_dissociation.mandala.controller.Controller;
import com.polymorphic_dissociation.mandala.model.Arm;
import com.polymorphic_dissociation.mandala.model.Canvas;
import com.polymorphic_dissociation.mandala.view.MandalaRenderer;
import com.polymorphic_dissociation.mandala.view.MandalaUI;

public class MandalaMaker extends ApplicationAdapter {

    private static final int CANVAS_WIDTH =  2480;
    private static final int CANVAS_HEIGHT =  3508;

    private Controller controller;
    private MandalaRenderer renderer;
    private MandalaUI mUI;
    private ScalingViewport viewport;

    @Override
    public void create() {
        this.viewport = new ScalingViewport(Scaling.fit, 960, 720);
//        viewport = new ScalingViewport(Scaling.fit, 1920, 1080);
        this.mUI = new MandalaUI();
        Arm arm = new Arm();
        Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        this.controller = new Controller(canvas, arm);
        this.renderer = new MandalaRenderer(canvas, arm);

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

//        //mandala
//        batch.draw(img, 0, (720-679) / 2f, 480, 679);
//        //ui
//        batch.draw(img, 480, 0, 480, 720);

        viewport.apply();

        controller.update(Gdx.graphics.getDeltaTime());
        renderer.render();

        Gdx.app.log(Utils.TAG, "fps: " + Gdx.graphics.getFramesPerSecond());

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        viewport.update(width, height);
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }
}
