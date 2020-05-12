package com.polymorphic_dissociation.mandala.controller;

import com.badlogic.gdx.Gdx;
import com.polymorphic_dissociation.mandala.Utils;
import com.polymorphic_dissociation.mandala.model.Arm;
import com.polymorphic_dissociation.mandala.model.Canvas;

public class Controller {

    private Canvas canvas;
    private Arm arm;

    public Controller(Canvas canvas, Arm arm){
        this.canvas = canvas;
        this.arm = arm;

        //todo: debug only, remove later
        this.arm.addSegment(200, 40f);
        this.arm.addSegment(100, 23f);

    }

    public void update(float delta){

        arm.rotate(delta);
        int x = (int) arm.getNeedlePos().x;
        int y = (int) arm.getNeedlePos().y;
        canvas.drawPixel(x, y);

        canvas.update();
//        canvas.saveToFile(Gdx.files.external("Desktop/canvas.png"));

    }

}
