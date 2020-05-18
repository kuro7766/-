package com.example.utilapp.WYShape;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class ExcelBackGround implements IdentifiedClockGeometry {
    public Bitmap mBitmap;
    public float x;
    /**
     * y轴上有问题
     */
    public float y;
    private final static int CACHE_SIZE =100;
    private final static int RECT_SIZE=50;
    public ExcelBackGround(final View parentView) {
        parentView.post(new Runnable() {
            @Override
            public void run() {
                int width=parentView.getWidth();
                int height=parentView.getHeight();
                mBitmap = Bitmap.createBitmap(width+ CACHE_SIZE, height + CACHE_SIZE, Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(mBitmap);
                Paint paint = new Paint();
                paint.setColor(Color.GRAY);
                mBitmap.eraseColor(Color.parseColor("#000000"));
                paint.setAlpha(80);
                for (int i = 0; i < width+ CACHE_SIZE; i+=RECT_SIZE) {
                    canvas.drawLine(i, 0, i,height+ CACHE_SIZE, paint);
                }
                for (int j = 0; j < height+ CACHE_SIZE; j+=RECT_SIZE) {
                    canvas.drawLine(0, j, width+ CACHE_SIZE, j, paint);
                }
            }
        });

    }

    private static final String TAG = "ExcelBackGround";

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

    public void draw(Canvas canvas, Paint paint, int deltaTime) {
        if(mBitmap==null){
            return;
        }
        int drawX=(int) (x % CACHE_SIZE);
        if(drawX>0){
            drawX-= CACHE_SIZE;
        }
        int drawY=(int) (y % CACHE_SIZE);
        if(drawY>0){
            drawY-= CACHE_SIZE;
        }
        canvas.drawBitmap(mBitmap,drawX,drawY, paint);
//        Log.d(TAG, "draw: "+drawX+","+drawY);
//        canvas.drawBitmap(mBitmap, x,y, paint);
    }

    public void move(float dx, float dy) {
        x += dx;
        y += dy;
    }

    public void set(float x,float y){
        this.x=x;
        this.y=y;
    }
}
