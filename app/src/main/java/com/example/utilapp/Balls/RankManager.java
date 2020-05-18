package com.example.utilapp.Balls;

import android.graphics.Color;
import android.os.Handler;

import com.chad.library.adapter.base.BaseViewHolder;
import com.example.utilapp.R;
import com.example.utilapp.Util.DenyInvokeAtRate;
import com.example.utilapp.Util.QuickRecyclerView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RankManager implements GameHttpHelper.OnUpdateListener {
    QuickRecyclerView<Response.Ball> rv;
    DenyInvokeAtRate denyInvokeAtRate;
    Handler handler;

    private Comparator<Response.Ball> comparator = new Comparator<Response.Ball>() {
        @Override
        public int compare(Response.Ball o1, Response.Ball o2) {
            return (int) ((o1.getR() - o2.getR()) * 1000);
        }
    };

    public RankManager(QuickRecyclerView<Response.Ball> listView) {
        handler = new Handler();
        this.rv = listView;
        denyInvokeAtRate = new DenyInvokeAtRate(1000);
    }

    @Override
    public void onResponse(final Response r) {
        denyInvokeAtRate.invoke(new Runnable() {
            @Override
            public void run() {
                synchronized (GameViewManager.viewGame) {
                    final List<Response.Ball> userBalls = r.getUserBalls();
                    Collections.sort(userBalls, comparator);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            rv.setItemLayout(R.layout.wy_xml_single_text, userBalls)
                                    .describeLayout(new QuickRecyclerView.RvAdapter.DescribeItem<Response.Ball>() {
                                        @Override
                                        public void describeItem(BaseViewHolder helper, Response.Ball item, int position) {
                                            helper.setText(R.id.default_text, ((item.getBallId() == GameViewManager.me.getId()) ? "我" : item.getBallId()) + "|" + item.getR() * Constants.ball.enlarge_socore);
                                            helper.setTextColor(R.id.default_text, Color.parseColor("#ffffff"));
                                        }
                                    });
                        }
                    });

                }
            }
        });
    }
}
