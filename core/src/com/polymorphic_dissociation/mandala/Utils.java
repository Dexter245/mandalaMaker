package com.polymorphic_dissociation.mandala;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

public class Utils {

    public static final String TAG = "MandalaMaker";

    public static Color getColorForIndex(int index){
        Color c = new Color();
        c.fromHsv((360f / 10)*index, 1f, 1f);
        c.a = 1f;
        return c;
    }

    public static void log(String msg){
        Gdx.app.log(TAG, msg);
    }

}
