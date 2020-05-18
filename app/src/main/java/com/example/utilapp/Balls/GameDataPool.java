package com.example.utilapp.Balls;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import com.example.utilapp.WYShape.IdentifiedClockGeometry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameDataPool {
    private List<Response.Ball> fixedBallList;
    //for user balls they are dynamic balls , thus just write them in
    //onResponse method


    public List<Response.Ball> getFixedBallList() {
        return fixedBallList;
    }

    public GameDataPool setFixedBallList(List<Response.Ball> fixedBallList) {
        this.fixedBallList = fixedBallList;
        return this;
    }
    public void addFixedBallList(List<Response.Ball> balls){
        if(fixedBallList==null){
            fixedBallList=new ArrayList<>();
        }
        fixedBallList.addAll(balls);
    }

    private static final String TAG = "GameDataPool";
    public void deleteFixedBallList(List<Integer> ids){
        for(Integer id:ids){
            int index=Collections.binarySearch(fixedBallList,getIdentifiedClockGeometryInstance().setId(id));
            if(index>=0){
                Log.d(TAG, "deleteFixedBallList: "+fixedBallList.get(index).getId());
                fixedBallList.remove(index);
            }
        }

    }

    private static IdentifiedClockGeometry identifiedClockGeometryInstance;

    //only usage is to binary search
    public static IdentifiedClockGeometry getIdentifiedClockGeometryInstance() {
        if (identifiedClockGeometryInstance == null) {
            synchronized (GameDataPool.class) {
                if (identifiedClockGeometryInstance == null) {
                    identifiedClockGeometryInstance = new IdentifiedClockGeometry() {
                        private int id;

                        @Override
                        public int compareTo(IdentifiedClockGeometry identifiedClockGeometry) {
                            return getId()-identifiedClockGeometry.getId();
                        }

                        @Override
                        public int getId() {
                            return id;
                        }

                        @Override
                        public IdentifiedClockGeometry setId(int id) {

                            this.id = id;
                            //to simplify code
                            return this;
                        }

                        @Override
                        public void draw(Canvas canvas, Paint paint, int deltaTime) {

                        }
                    };
                }
            }
        }
        return identifiedClockGeometryInstance;
    }
}
