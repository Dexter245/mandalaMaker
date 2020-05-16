package com.polymorphic_dissociation.mandala.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisSlider;
import com.kotcrab.vis.ui.widget.VisTable;
import com.polymorphic_dissociation.mandala.Utils;
import com.polymorphic_dissociation.mandala.controller.Controller;
import com.polymorphic_dissociation.mandala.model.Model;
import com.polymorphic_dissociation.mandala.model.Segment;

public class SegmentUI extends VisTable {

    private final int index;
    private Model model;
    private Controller controller;
    private VisLabel labelLength;
    private VisSlider sliderLength;
    private VisLabel labelRotSpeed;
    private VisSlider sliderRotSpeed;
    private Color color;

    public SegmentUI(final int index, Model model, Controller controller) {
        this.index = index;
        this.model = model;
        this.controller = controller;
        this.color = Utils.getColorForIndex(index);
        Utils.log("index: " + index);
        setupUI();
        setupUIListeners();
    }

    private void setupUI() {
//        HorizontalGroup row1 = new HorizontalGroup();
//        HorizontalGroup row2 = new HorizontalGroup();
//        addActor(row1);
//        addActor(row2);

        labelLength = new VisLabel("Length", color);
        add(labelLength).width(120);
        sliderLength = new VisSlider(50f, 700f, 20f, false);
        sliderLength.setRound(true);
//        sliderLength.setFillParent(true);
        add(sliderLength).width(700);

        row();

        labelRotSpeed = new VisLabel("RotSpeed", color);
        add(labelRotSpeed).width(120);
        sliderRotSpeed = new VisSlider(-350f, 350f, 20f, false);
        add(sliderRotSpeed).width(700);

        center();
    }

    private void setupUIListeners() {
        sliderLength.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Segment s = model.getArm().getSegment(index);
                controller.onSegmentChange(index, Math.round(sliderLength.getValue()), s.getRotSpeed());
                labelLength.setText("Length: " + (int) s.getLength());
                Gdx.app.log(Utils.TAG, "sliderLength changed");
            }
        });
        sliderLength.setValue(model.getArm().getSegment(index).getLength());

        sliderRotSpeed.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Segment s = model.getArm().getSegment(index);
                controller.onSegmentChange(index, s.getLength(), sliderRotSpeed.getValue());
                labelRotSpeed.setText("RotSpeed: " + (int) s.getRotSpeed());
            }
        });
        sliderRotSpeed.setValue(model.getArm().getSegment(index).getRotSpeed());


    }


}
