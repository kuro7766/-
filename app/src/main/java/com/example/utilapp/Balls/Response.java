package com.example.utilapp.Balls;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

import com.example.utilapp.WYShape.IdentifiedClockGeometry;

import java.util.List;

public class Response {
    @Override
    public String toString() {
        return "Response{" +
                "msg='" + msg + '\'' +
                ", addFixedBalls=" + addFixedBalls +
                ", fixedBalls=" + fixedBalls +
                ", toDeleteId=" + toDeleteId +
                ", userBalls=" + userBalls +
                '}';
    }

    private String msg;
    private List<Ball> addFixedBalls;
    private List<Ball> fixedBalls;
    private List<Integer> toDeleteId;
    private List<Ball> userBalls;

    public String getMsg() {
        return msg;
    }

    public Response setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public List<Ball> getAddFixedBalls() {
        return addFixedBalls;
    }

    public Response setAddFixedBalls(List<Ball> addFixedBalls) {
        this.addFixedBalls = addFixedBalls;
        return this;
    }

    public List<Ball> getFixedBalls() {
        return fixedBalls;
    }

    public Response setFixedBalls(List<Ball> fixedBalls) {
        this.fixedBalls = fixedBalls;
        return this;
    }

    public List<Integer> getToDeleteId() {
        return toDeleteId;
    }

    public Response setToDeleteId(List<Integer> toDeleteId) {
        this.toDeleteId = toDeleteId;
        return this;
    }

    public List<Ball> getUserBalls() {
        return userBalls;
    }

    public Response setUserBalls(List<Ball> userBalls) {
        this.userBalls = userBalls;
        return this;
    }

    public static class Ball  implements IdentifiedClockGeometry {
        /**
         * color : #ff3280db
         * id : 0
         * r : 10
         * shape : 0
         * speedX : 0
         * speedY : 0
         * x : -1362.1986
         * y : -4194.174
         */

        private String color;
        //TODO test if id can be serialized
        private int ballId;
        private float r;
        private int shape;
        private float speedX;
        private float speedY;
        private float x;
        private float y;

        public int getBallId() {
            return ballId;
        }

        public Ball setBallId(int ballId) {
            this.ballId = ballId;
            return this;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        @Override
        public int compareTo(IdentifiedClockGeometry identifiedClockGeometry) {
            return ballId-identifiedClockGeometry.getId();
        }

        public int getId() {
            return ballId;
        }

        @Override
        public IdentifiedClockGeometry setId(int id) {
            this.ballId=id;
            return this;
        }

        @Override
        public void draw(Canvas canvas, Paint paint, int deltaTime) {
            View view=GameViewManager.viewGame;
            Ball me=GameViewManager.me;
            if(me==null){
                Log.d("ssss", "draw: return");
                return;
            }
            Paint paint1=GameViewManager.paint;
            paint1.setColor(Color.parseColor(getColor()));
            switch (getShape()){
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    break;
            }
//            Log.d("ssss", "draw: "+
//                    ((view.getWidth()/2+x-me.x)-r)
//                    +((view.getHeight()/2+y-me.y)-r)
//                    +((view.getWidth()/2+x-me.x)+r)
//                    +((view.getHeight()/2+y-me.y)+r)
//            );
            canvas.drawOval((view.getWidth()/2+x-me.x)-r
                    ,(view.getHeight()/2+y-me.y)-r
                    ,(view.getWidth()/2+x-me.x)+r
                    ,(view.getHeight()/2+y-me.y)+r
                    ,paint1);
        }

        public float getR() {
            return r;
        }

        public void setR(float r) {
            this.r = r;
        }

        public int getShape() {
            return shape;
        }

        public void setShape(int shape) {
            this.shape = shape;
        }

        public float getSpeedX() {
            return speedX;
        }

        public void setSpeedX(float speedX) {
            this.speedX = speedX;
        }

        public float getSpeedY() {
            return speedY;
        }

        public void setSpeedY(float speedY) {
            this.speedY = speedY;
        }

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
    }

}
