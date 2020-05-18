package com.example.utilapp.Balls;

import com.example.utilapp.WYShape.GamePad;

public class GameManager {
	GameHttpHelper gameHttpHelper;

	GameViewManager gameViewManager;

	GameHttpHelper.OnUpdateListener onUpdateListener;

	public GameManager setOnUpdateListener(GameHttpHelper.OnUpdateListener onUpdateListener) {
		this.onUpdateListener = onUpdateListener;
		return this;
	}

	public GameManager(ViewGame viewGame){
	    GamePad gamePad=new GamePad(
	            Constants.pad.inner_radius,
                Constants.pad.outter_radius
        );
		gameHttpHelper=new GameHttpHelper(gamePad);
		gameViewManager=new GameViewManager(viewGame,gamePad);
	}

	public void initGame(){
		gameHttpHelper.setOnUpdateListener(new GameHttpHelper.OnUpdateListener() {
            @Override
            public void onResponse(Response r) {
                gameViewManager.upDateViewForResponse(r);
                if(onUpdateListener!=null){
                	onUpdateListener.onResponse(r);
				}
            }
        });
		gameHttpHelper.startRequest();
	}

	public void onDestroy(){
        gameHttpHelper.setRunning(false);
    }
}
