package com.example.utilapp.UtilActivitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.utilapp.R;
import com.example.utilapp.Util.ActivityManager;
import com.example.utilapp.Util.QuickRecyclerView;

import java.util.ArrayList;

public class AMainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivitys";
    private QuickRecyclerView<Class> quickRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wy_xml_linearlayout);
        quickRecyclerView = findViewById(R.id.quick);
        quickRecyclerView.setItemLayout(R.layout.wy_xml_single_text, new ArrayList<Class>() {
            ArrayList a(Class o) {
                add(o);
                return this;
            }
            {
                a(球球大作战.class);
            }
        }).describeLayout(new QuickRecyclerView.RvAdapter.DescribeItem<Class>() {
            @Override
            public void describeItem(BaseViewHolder helper, Class item, int position) {
                helper.setText(R.id.default_text, "\n" + item + "\n");
            }
        }).setOnItemClickListener(new QuickRecyclerView.OnItemClickListener<Class>() {
            @Override
            public void onItemClick(Class item, int position, View view, BaseQuickAdapter adapter) {
                ActivityManager.startActivity(item);
            }
        });
    }
}
