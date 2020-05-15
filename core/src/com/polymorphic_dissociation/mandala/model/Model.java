package com.polymorphic_dissociation.mandala.model;

public class Model {

    public enum GameState{
        Play,
        Pause,
        FF
    }

    private Arm arm;
    private Canvas canvas;
    private float speed = 1f;
    private boolean autoClear = false;
    private GameState gameState = GameState.Play;

    public Model(Canvas canvas){
        this.canvas = canvas;
        arm = new Arm();
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public Arm getArm() {
        return arm;
    }

    public void setArm(Arm arm) {
        this.arm = arm;
    }

    public int getNumSegments() {
        return arm.getNumSegments();
    }

    public void setNumSegments(int numSegments) {
        while(getNumSegments() > numSegments){
            arm.removeSegment();
        }
        while(getNumSegments() < numSegments){
            arm.addSegment((float) (Math.random()*200), (float) (Math.random()*30));
        }
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public boolean isAutoClear() {
        return autoClear;
    }

    public void setAutoClear(boolean autoClear) {
        this.autoClear = autoClear;
    }
}
