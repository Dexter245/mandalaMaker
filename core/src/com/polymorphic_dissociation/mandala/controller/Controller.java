package com.polymorphic_dissociation.mandala.controller;

import com.badlogic.gdx.Gdx;
import com.polymorphic_dissociation.mandala.Utils;
import com.polymorphic_dissociation.mandala.model.Arm;
import com.polymorphic_dissociation.mandala.model.Canvas;
import com.polymorphic_dissociation.mandala.model.Model;
import com.polymorphic_dissociation.mandala.model.Segment;

public class Controller {

    private static final float SPEED_FACTOR_FF = 200f;
    private static final float MAX_STEP_LENGTH = 0.001f;

    private Model model;
    private Canvas canvas;
    private Arm arm;

    public Controller(Model model) {
        this.model = model;
        this.canvas = model.getCanvas();
        this.arm = model.getArm();

        //todo: debug only, remove later
        this.arm.addSegment(200, 40f);
        this.arm.addSegment(100, 24f);
        this.arm.addSegment(50, -100f);

    }

    public void update(float delta) {

        if (model.getGameState() != Model.GameState.Pause) {

            float speedFactor = model.getSpeed();
            if (model.getGameState() == Model.GameState.FF)
                speedFactor = SPEED_FACTOR_FF;


            float remaining = delta * speedFactor;
            while (remaining > 0f) {
                float step = remaining;
                if (step > MAX_STEP_LENGTH)
                    step = MAX_STEP_LENGTH;

                arm.rotate(step);
                int x = (int) arm.getNeedlePos().x;
                int y = (int) arm.getNeedlePos().y;
                canvas.drawPixel(x, y);

                remaining -= step;
            }

        }

        canvas.update();

    }

    public void onSegmentChange(int index, float length, float rotSpeed){
        if(index >= arm.getSegments().size())
            throw new IllegalArgumentException("index >= size");
        arm.getSegment(index).setLength(length);
        arm.getSegment(index).setRotSpeed(rotSpeed);
        if(model.isAutoClear())
            canvas.clear();
    }

    public void onButtonPlay() {
        model.setGameState(Model.GameState.Play);
    }

    public void onButtonPause() {
        model.setGameState(Model.GameState.Pause);
    }

    public void onButtonFF() {
        model.setGameState(Model.GameState.FF);
    }

    public void onButtonClear() {
        canvas.clear();
    }

    public void onButtonSave() {
        canvas.saveToFile(Gdx.files.external("Desktop/canvas.png"));
    }

    public void onButtonAutoClear(boolean checked) {
        model.setAutoClear(checked);
    }

    public void onSliderSegments(int value) {
        model.setNumSegments(value);
        if(model.isAutoClear())
            canvas.clear();
    }

    public void onSliderSpeed(float value) {
        model.setSpeed(value);
    }

}
