package com.example.utilapp.Balls;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.example.utilapp.Util.HTMLCrawler;
import com.example.utilapp.WYShape.GamePad;

import java.util.List;

public class GameHttpHelper {
    private static final String TAG = "GameHttpHelper";
    private static final String BASE_URL = Constants.game.base_url;
    public static int deviceId;
    public static float gamePadControlX;
    public static float gamePadControlY;

    public float getGamePadControlX() {
        return gamePadControlX;
    }

    public float getGamePadControlY() {
        return gamePadControlY;
    }

    private GamePad gamePad;
    static {
        deviceId=(int)(Math.random()*10000)+100000;
    }
    GameHttpHelper(GamePad gamePad) {
        isRunning = true;
        gamePad.setGamePadActionListener(new GamePad.GamePadActionListener() {
            @Override
            public void onAction(float dx, float dy) {
                gamePadControlX=dx;
                gamePadControlY=dy;
                Log.d(TAG, "onAction: "+gamePadControlX+","+gamePadControlY);
            }
        });
    }

    private boolean isRunning;

    public void setRunning(boolean b) {
        isRunning = b;
    }

    interface OnUpdateListener {
        void onResponse(Response r);
    }

    OnUpdateListener onUpdateListener;

    public void setOnUpdateListener(OnUpdateListener onUpdateListener) {
        this.onUpdateListener = onUpdateListener;
    }

    void startRequest() {
        new Thread() {
            @Override
            public void run() {
                while (isRunning) {
                    long pre = System.currentTimeMillis();
                    new HTMLCrawler()
                            .url(BASE_URL + deviceId + "&x=" + gamePadControlX + "&y=" + gamePadControlY)
                            .doGet(new HTMLCrawler.GetListener() {
                                @Override
                                public void onSuccess(String s, List<String> strings) {
                                    Log.d(TAG, System.currentTimeMillis()+"onSuccess: " + s);
                                    if (onUpdateListener != null) {
                                        Response bean = JSON.parseObject(s, Response.class);
                                        onUpdateListener.onResponse(bean);
                                    }
                                }

                                @Override
                                public void onFail(String s) {
                                    Log.d(TAG, "onFail() returned: " + s);
                                }
                            });
                    Log.d(TAG, "单次请求延时: " + (System.currentTimeMillis() - pre));
                }
            }
        }.start();
    }
}
