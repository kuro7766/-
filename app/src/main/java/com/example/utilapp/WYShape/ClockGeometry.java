package com.example.utilapp.WYShape;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * 时钟控制的几何图形
 * 所有在{@link WYView}的图形状必须实现这个类
 */
public interface ClockGeometry {
    void draw(Canvas canvas, Paint paint, int deltaTime);
}
