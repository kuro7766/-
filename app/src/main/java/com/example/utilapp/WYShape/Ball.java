package com.example.utilapp.WYShape;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.util.Log;

public class Ball implements IdentifiedClockGeometry{
    public float x;
    public float y;
    public float r;
    private int id;
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getR() {
        return r;
    }

    public void setR(float r) {
        this.r = r;
    }

    public Ball(int id, float x, float y, float r) {
        this.id=id;
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public Ball(float x, float y, float r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    private static final String TAG = "Ball";

    @Override
    public int compareTo(@NonNull IdentifiedClockGeometry identifiedClockGeometry) {
        return id-identifiedClockGeometry.getId();
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public IdentifiedClockGeometry setId(int id) {
        this.id=id;
        return this;
    }

    @Override
    public void draw(Canvas canvas, Paint paint, int deltaTime) {

        canvas.drawOval(x-r,y-r,x+r,y+r,paint);

    }
}
