package com.example.utilapp.WYShape;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.NonNull;

public interface IdentifiedClockGeometry extends ClockGeometry,Comparable<IdentifiedClockGeometry>{

    /**
     * must contains at least one id for key to later sort
     */
    public int compareTo(IdentifiedClockGeometry identifiedClockGeometry);
    public int getId();
    public IdentifiedClockGeometry setId(int id);
    @Override
    public void draw(Canvas canvas, Paint paint, int deltaTime);
}
