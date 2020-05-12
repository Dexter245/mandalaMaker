package com.polymorphic_dissociation.mandala.model;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class Arm {

    private static final float CENTER_X = 1240f;
    private static final float CENTER_Y = 1754f;

    private List<Segment> segments;

    public Arm(){
        this(null);
    }

    public Arm(List<Segment> segments){
        if(segments != null)
            this.segments = segments;
        else
            this.segments = new ArrayList<>();
    }

    public void rotate(float delta){
        for(Segment s : segments){
            s.rotate(delta);
        }
    }

    public void addSegment(float length, float rotSpeed){
        segments.add(new Segment(length, rotSpeed));
    }

    public void removeSegment(){
        if(segments.size() >= 1)
            segments.remove(segments.size()-1);
    }

    public Vector2 getNeedlePos(){
        Vector2 v = new Vector2(CENTER_X, CENTER_Y);
        for(Segment s : segments){
            v.add(s.getVector());
        }
        return v;
    }

    public List<Segment> getSegments(){
        return segments;
    }
}
