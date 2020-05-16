package com.polymorphic_dissociation.mandala.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.*;
import com.polymorphic_dissociation.mandala.Utils;
import com.polymorphic_dissociation.mandala.controller.Controller;
import com.polymorphic_dissociation.mandala.model.Model;

import java.util.ArrayList;
import java.util.List;

public class MandalaUI {

    private Model model;
    private Controller controller;
    private Stage stage;
    private List<SegmentUI> segmentUIs = new ArrayList<>();
    private VisTable root;
    private VisTextButton buttonPlay;
    private VisTextButton buttonPause;
    private VisTextButton buttonFF;
    private VisTextButton buttonClear;
    private VisTextButton buttonSave;
    private VisTextButton buttonAutoClear;
    private VisLabel labelSegments;
    private VisSlider sliderSegments;
    private VisLabel labelSpeed;
    private VisSlider sliderSpeed;

    public MandalaUI(Model model, Controller controller){
        this.model = model;
        this.controller = controller;
        VisUI.load();
        int w = Gdx.graphics.getWidth();
        int h = Gdx.graphics.getHeight();
//        Viewport v = new ScalingViewport(Scaling.fit, 480, 720);
        Viewport v = new ScalingViewport(Scaling.fit, w/2f, h);
        stage = new Stage(v);
        Gdx.input.setInputProcessor(stage);
        root = new VisTable();
        root.setFillParent(true);
        root.setPosition(0, 0);
//        root.setSize(w/2f, h);
        root.align(Align.topLeft);
        stage.addActor(root);

        setupUI();
        setupUIListeners();
        stage.setDebugAll(true);
    }

    private void setupUI(){
        VisTable table1 = new VisTable();
        table1.align(Align.topLeft);
        root.add(table1).row();

        buttonPlay = new VisTextButton("Play", "toggle");
        buttonPlay.setChecked(true);
        table1.add(buttonPlay);

        buttonPause = new VisTextButton("Pause", "toggle");
        table1.add(buttonPause);

        buttonFF = new VisTextButton("FF", "toggle");
        table1.add(buttonFF);

        buttonClear = new VisTextButton("Clear");
        table1.add(buttonClear);

        buttonAutoClear = new VisTextButton("AutoClear", "toggle");
        table1.add(buttonAutoClear);

        buttonSave = new VisTextButton("Save");
        table1.add(buttonSave);

        VisTable table2 = new VisTable();
        root.add(table2).row();

        labelSegments = new VisLabel("Segments: 2");
        table2.add(labelSegments).width(100f);
        sliderSegments = new VisSlider(1, 10, 1, false);
        sliderSegments.setValue(model.getNumSegments());
        table2.add(sliderSegments);

        labelSpeed = new VisLabel("Speed: 1");
        table2.add(labelSpeed).width(100f);
        sliderSpeed = new VisSlider(1.0f, 50f, 1.0f, false);
        sliderSpeed.setValue(model.getSpeed());
        table2.add(sliderSpeed);


        onSegmentAmountChange();

    }

    private void setupUIListeners(){
        buttonPlay.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(buttonPlay.isChecked()){
                    if(buttonPause.isChecked())
                        buttonPause.setChecked(false);
                    if(buttonFF.isChecked())
                        buttonFF.setChecked(false);
                    controller.onButtonPlay();
                }
            }
        });
        buttonPause.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(buttonPause.isChecked()){
                    if(buttonPlay.isChecked())
                        buttonPlay.setChecked(false);
                    if(buttonFF.isChecked())
                        buttonFF.setChecked(false);
                    controller.onButtonPause();
                }
            }
        });
        buttonFF.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(buttonFF.isChecked()){
                    if(buttonPlay.isChecked())
                        buttonPlay.setChecked(false);
                    if(buttonPause.isChecked())
                        buttonPause.setChecked(false);
                    controller.onButtonFF();
                }
            }
        });
        buttonClear.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                controller.onButtonClear();
            }
        });
        buttonSave.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                controller.onButtonSave();
            }
        });
        buttonAutoClear.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                controller.onButtonAutoClear(buttonAutoClear.isChecked());
            }
        });
        sliderSegments.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                labelSegments.setText("Segments: " + (int) sliderSegments.getValue());
                controller.onSliderSegments((int) sliderSegments.getValue());
                onSegmentAmountChange();
            }
        });
        sliderSpeed.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                labelSpeed.setText("Speed: " + (int) sliderSpeed.getValue());
                controller.onSliderSpeed(sliderSpeed.getValue());
            }
        });
    }

    private void onSegmentAmountChange(){
        for(int i = segmentUIs.size()-1; i >= 0; i--){
//            root.removeActor(segmentUIs.get(i));
            segmentUIs.get(i).remove();
            segmentUIs.remove(i);
        }
        for(int i = 0; i < model.getNumSegments(); i++){
            SegmentUI sUI = new SegmentUI(i, model, controller);
//            root.addActor(sUI);
            root.add(sUI).row();
            segmentUIs.add(sUI);
        }
    }

    public void render(){
        int w = Gdx.graphics.getWidth();
        int h = Gdx.graphics.getHeight();
        stage.getViewport().update(w/2, h);
        stage.getViewport().setScreenPosition(w/2 + 1, 0);
        stage.getViewport().apply();
        stage.act();
        stage.draw();
    }

    public void dispose(){
        stage.dispose();
    }

}
