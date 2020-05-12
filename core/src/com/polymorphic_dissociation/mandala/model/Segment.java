package com.polymorphic_dissociation.mandala.model;

import com.badlogic.gdx.math.Vector2;

public class Segment {

    private Vector2 v;
    private float rotSpeed;//degrees per second

    public Segment(float length, float rotSpeed){
        this.v = new Vector2(0f, length);
        this.rotSpeed = rotSpeed;
    }

    public void rotate(float delta){
        v.rotate(rotSpeed*delta);
    }

    public Vector2 getVector(){
        return v;
    }

}
