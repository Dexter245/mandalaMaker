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

public class MandalaMaker extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture img;
    private OrthographicCamera camera;
    private ScalingViewport viewport;

    @Override
    public void create() {
        batch = new SpriteBatch();
        img = new Texture("checkerboard.jpg");
        int w = Gdx.graphics.getWidth();
        int h = Gdx.graphics.getHeight();
        viewport = new ScalingViewport(Scaling.fit, 960, 720);
//        viewport = new ScalingViewport(Scaling.fit, 1920, 1080);

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        int w = Gdx.graphics.getWidth();
        int h = Gdx.graphics.getHeight();

        viewport.apply();
        batch.setColor(Color.BLUE);
        batch.begin();
        batch.draw(img, 0, (720-679) / 2f, 480, 679);
//        batch.draw(img, 0, 0, 764, 1080);
        batch.end();

        batch.setColor(Color.GREEN);
        batch.begin();
        batch.draw(img, 480, 0, 480, 720);
//        batch.draw(img, 960, 0, 960, 1080);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        viewport.update(width, height);
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }
}
