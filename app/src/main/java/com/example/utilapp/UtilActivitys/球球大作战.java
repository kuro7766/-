package com.example.utilapp.UtilActivitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.utilapp.R;
import com.example.utilapp.Util.QuickRecyclerView;
import com.example.utilapp.Balls.GameManager;
import com.example.utilapp.Balls.RankManager;
import com.example.utilapp.Balls.Response;
import com.example.utilapp.Balls.ViewGame;


public class 球球大作战 extends AppCompatActivity  {
    private static final String TAG = "球球大作战";
    private ViewGame viewGame;
    private GameManager gameManager;
    private RankManager rankManager;
    private QuickRecyclerView<Response.Ball> listView;//rank

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wyview_first_test_layout);
        listView = findViewById(R.id.list_view);
        viewGame = findViewById(R.id.wyview);
        rankManager=new RankManager(listView);
        gameManager=new GameManager(viewGame);
        gameManager.setOnUpdateListener(rankManager);
        gameManager.initGame();
        //view.height is null in onCreate
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        gameManager.onDestroy();
    }

}
