package com.example.utilapp.Balls;

import android.graphics.Paint;

import com.example.utilapp.Util.TCallBack;
import com.example.utilapp.Util.ToastUtil;
import com.example.utilapp.WYShape.ExcelBackGround;
import com.example.utilapp.WYShape.GamePad;

import java.util.List;

//assumption : my ball is always in the center of the screen ,
//to show some changes , I can make the ball's radius minSize+calculateSize
public class GameViewManager {
    private static final String TAG = "GameViewManager";
    GameDataPool gameDataPool;
    ExcelBackGround excelBackGround;

    public static ViewGame viewGame;
    //used as centerCamera
    public static Response.Ball me;
    public static Paint paint = new Paint();

    public GameViewManager(ViewGame viewGame, GamePad gamePad) {
        GameViewManager.viewGame = viewGame;
        gameDataPool = new GameDataPool();
        excelBackGround = new ExcelBackGround(viewGame);
        viewGame.add(excelBackGround);
        viewGame.add(gamePad);
        viewGame.addPreDrawListeners(new TCallBack<Void>() {
            @Override
            public void call(Void aVoid) {
                if (me != null)
                    excelBackGround.set(-me.getX(), -me.getY());
            }
        });
    }

    void upDateViewForResponse(Response r) {
        //this method is in child thread , not in main thread
        if (r.getFixedBalls() != null) {
            //init a large amount of balls
            gameDataPool.setFixedBallList(r.getFixedBalls());
            viewGame.add(gameDataPool.getFixedBallList(), false);
        }
        if (r.getAddFixedBalls() != null) {
            synchronized (viewGame) {
                gameDataPool.addFixedBallList(r.getAddFixedBalls());
            }
        }

        synchronized (viewGame) {
//            Log.d(TAG, "toDeleteId: " + r.getToDeleteId());
            for (Integer id : r.getToDeleteId()) {
                viewGame.remove(id);
            }
            //can be used for reset null for handler list
            gameDataPool.deleteFixedBallList(r.getToDeleteId());
        }

        //user balls
        //        traverse to search my ball
        List<Response.Ball> userBalls = r.getUserBalls();
        Response.Ball myBall = null;

        synchronized (viewGame) {
            for (Response.Ball ball : userBalls) {
                if (ball.getId() == GameHttpHelper.deviceId) {
                    myBall = ball;
                    break;
                }
            }
        }
        //after A trip of traverse if my ball is still null ,
        //it's most likely eaten by other balls , time to game over

        if (myBall != null) {
            if (me == null) {
                //the first time , no need to interpolate
                //fill me's null ptr
                me = myBall;
//                Log.d(TAG, "me: " + me);
            } else {
                me = myBall;
//                CenterCameraInterpolator
//                        .getInstance()
//                        .setInfo(me,myBall)
//                        .start();
            }
            // me = myBall;
            excelBackGround.set(-me.getX(), -me.getY());
        } else {
            //maybe game is over
            ToastUtil.showShort("GameOver");
        }

        viewGame.setUserBalls(userBalls);
    }

}
