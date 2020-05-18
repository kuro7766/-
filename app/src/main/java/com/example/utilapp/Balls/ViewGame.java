package com.example.utilapp.Balls;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.example.utilapp.WYShape.IdentifiedClockGeometry;
import com.example.utilapp.WYShape.WYView;

import java.util.List;

/**
 * @see super#identifiedClockGeometryList is all the trees in the world ,
 * but in this class , an fixedBallOnScreenId is the
 */
public class ViewGame extends WYView {

    List<? extends IdentifiedClockGeometry> userBalls;
    List<Integer> fixedBallOnScreenId;

    public ViewGame setUserBalls(List<? extends IdentifiedClockGeometry> userBalls) {
        this.userBalls = userBalls;
        return this;
    }

    public ViewGame(Context context) {
        super(context);
    }

    public ViewGame(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //can't use this method if you use super!
//        deltaTime = calDeltaTime();
        synchronized (this) {
            super.onDraw(canvas);
            if (userBalls != null) {
                for (IdentifiedClockGeometry identifiedClockGeometry : userBalls) {
                    identifiedClockGeometry.draw(canvas, paint, deltaTime);
                }
            }
        }
        Response.Ball me=GameViewManager.me;
        if (me != null) {
            float x =me.getX()+ me.getSpeedX() * deltaTime * Constants.ball.speed_enlarge / me.getR();
            float y =me.getY()+ me.getSpeedY() * deltaTime * Constants.ball.speed_enlarge / me.getR();
            me.setX(x);
            me.setY(y);
        }
        postInvalidate();
    }
}
