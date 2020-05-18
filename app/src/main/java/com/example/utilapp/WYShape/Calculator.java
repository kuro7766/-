package com.example.utilapp.WYShape;



public class Calculator {
    public static float distance(int x1,int y1,int x2,int y2){
        return (float) Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
    }
    public static float distance(float x1,float y1,float x2,float y2){
        return (float) Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
    }
    public static float distance(float dx,float dy){
        return (float) Math.sqrt(dx*dx+dy*dy);
    }

    public static float unitX(float originX,float originY,float destX,float destY){
        return unitX(destX-originX,destY-originY);
    }

    public static float unitY(float originX,float originY,float destX,float destY){
        return unitY(destX-originX,destY-originY);
    }

    /**
     * @return for triangle 3,4,5 , if I want to get the length of
     * unit length of x , which is also the value of cos
     */
    public static float unitX(float dx,float dy){
        return dx/distance(dx,dy);
    }

    /**
     * @return for triangle 3,4,5 , if I want to get the length of
     *      * unit length of x , which is also the value of sin
     */
    public static float unitY(float dx,float dy){
        return dy/distance(dx,dy);
    }
}
