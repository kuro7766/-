package com.example.utilapp.WYShape;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.utilapp.Util.TCallBack;
import com.example.utilapp.Balls.Constants;
import com.example.utilapp.Balls.GameDataPool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WYView extends View {
    private static final String TAG = "WYView";
    protected List<IdentifiedClockGeometry> identifiedClockGeometryList;
    protected List<TouchEventCaputre> touchEventCaptureList;
    protected long pre;
    protected Paint paint;
    protected int idgenerator=Constants.ball.default_minimum_id;
    protected int deltaTime;
    protected List<TCallBack<Void>> preDrawListeners=new ArrayList<>();

    public void addPreDrawListeners(TCallBack<Void> t) {
        preDrawListeners.add(t);
    }

    public int generateId(){
        return idgenerator++;
    }
    public WYView(Context context) {
        super(context);
        init();
    }
    public WYView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    private void init(){
        identifiedClockGeometryList=new ArrayList<>();
        touchEventCaptureList =new ArrayList<>();
        paint=new Paint();
    }

    protected int calDeltaTime(){
        if(pre==0){
            pre=System.currentTimeMillis();
            return 0;
        }else {
            //更新数值并计算差值
            long lastPre=pre;
            pre=System.currentTimeMillis();
            return (int) (pre-lastPre);
        }
    }

    public void add(IdentifiedClockGeometry identifiedClockGeometry){
        add(identifiedClockGeometry,true);
    }

    public void add(List<? extends IdentifiedClockGeometry> identifiedClockGeometries,boolean sortAfter){
        for(IdentifiedClockGeometry identifiedClockGeometry:identifiedClockGeometries){
            add(identifiedClockGeometry,false);
        }
        add((IdentifiedClockGeometry) null,sortAfter);
    }

    public void add(IdentifiedClockGeometry identifiedClockGeometry,boolean sortAfter){
        if(identifiedClockGeometry==null){
            if(sortAfter){
                synchronized (this){
                    Collections.sort(identifiedClockGeometryList);
                }
            }
            return;
        }
        //if you haven't id,attribute one
        if(identifiedClockGeometry.getId()==0){
            int attributeId=generateId();
            identifiedClockGeometry.setId(attributeId);
        }
        synchronized (this){
            identifiedClockGeometryList.add(identifiedClockGeometry);
        }
        if(sortAfter)
            synchronized (this){
                Collections.sort(identifiedClockGeometryList);
            }

        if(identifiedClockGeometry instanceof TouchEventCaputre){
            touchEventCaptureList.add((TouchEventCaputre) identifiedClockGeometry);
        }
    }

    /**
     * run on main thread
     * @param id
     * @return removedObject for future addition
     */
    public IdentifiedClockGeometry remove(int id){
        int index=Collections.binarySearch(identifiedClockGeometryList,GameDataPool.getIdentifiedClockGeometryInstance().setId(id));
        if(index<0){
            return null;
        }else {
            return identifiedClockGeometryList.remove(index);
        }
    }

    public void clear(){
        identifiedClockGeometryList.clear();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        deltaTime=calDeltaTime();
        for(TCallBack<Void> t:preDrawListeners){
            t.call(null);
        }
        long pre=System.currentTimeMillis();
        for(IdentifiedClockGeometry IdentifiedClockGeometry:identifiedClockGeometryList){
            IdentifiedClockGeometry.draw(canvas,paint,deltaTime);
        }
        invalidate();
        Log.d(TAG, "onDraw: "+(System.currentTimeMillis()-pre));
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        for(TouchEventCaputre touchEventCaputre: touchEventCaptureList){
            touchEventCaputre.caputreTouchEvent(e);
        }
//        Log.d(TAG, "onTouchEvent: sss");
        //return true means it will continuously click
        return true;
    }
}
