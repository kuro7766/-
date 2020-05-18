package com.example.utilapp.WYShape;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;

import com.example.utilapp.WYShape.Ball;
import com.example.utilapp.WYShape.Calculator;
import com.example.utilapp.WYShape.ClockGeometry;
import com.example.utilapp.WYShape.TouchEventCaputre;

public class GamePad implements IdentifiedClockGeometry , TouchEventCaputre {
	private Ball ballIn;
	private Ball ballOut;
	private boolean visibility=false;
	private GamePadActionListener gamePadActionListener;
	public void setGamePadActionListener(GamePadActionListener gamePadActionListener){
		this.gamePadActionListener=gamePadActionListener;
	}
	public interface GamePadActionListener{
		//dx and dy 是单位1的距离
	    void onAction(float dx,float dy);
    }
	public GamePad(float rIn,float rOut){
		ballIn=new Ball(0,0,rIn);
		ballOut=new Ball(0,0,rOut);
	}

    private static final String TAG = "GamePad";
	@Override
	public void caputreTouchEvent(MotionEvent e){
		float x=e.getX();
		float y=e.getY();
		switch (e.getAction()) {
			case MotionEvent.ACTION_DOWN:
				visibility=true;
				ballIn.x=x;
				ballIn.y=y;
				ballOut.x=x;
				ballOut.y=y;
			break;
			case MotionEvent.ACTION_MOVE:
				//if (x,y) is not out of ballOut's range,it will be ballIn's (x,y)
				float restrictDistance=ballOut.r-ballIn.r;
				if(Calculator.distance(x,y,ballOut.x,ballOut.y)<restrictDistance){
					//inner ball is in range
                    Log.d(TAG, "caputreTouchEvent1: "+x+","+y);
					ballIn.x=x;
					ballIn.y=y;
                    if(gamePadActionListener!=null){
                        gamePadActionListener.onAction(x-ballOut.x,y-ballOut.y);
                    }
				}else {
					float dx=x-ballOut.x;
					float dy=y-ballOut.y;
                    Log.d(TAG, "caputreTouchEvent2: "+x+","+y);
					float dividFactor=Calculator.distance(dx,dy);
					dx/=dividFactor;
					dy/=dividFactor;
					if(gamePadActionListener!=null){
						gamePadActionListener.onAction(dx*restrictDistance,dy*restrictDistance);
					}
					ballIn.x=restrictDistance*dx+ballOut.x;
					ballIn.y=restrictDistance*dy+ballOut.y;
				}
			break;
			case MotionEvent.ACTION_UP:
				visibility=false;
			break;
		}
	}

	private int id;
	@Override
	public int compareTo(IdentifiedClockGeometry identifiedClockGeometry) {
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

	public void draw(Canvas canvas, Paint paint, int deltaTime){
		if(visibility){
			paint.setColor(Color.GRAY);
			paint.setAlpha(100);
			ballOut.draw(canvas,paint,deltaTime);
			paint.setAlpha(180);
			ballIn.draw(canvas,paint,deltaTime);
			paint.setColor(Color.BLACK);
			paint.setAlpha(255);
		}else {
			//看不见，不绘制
		}
	}
}