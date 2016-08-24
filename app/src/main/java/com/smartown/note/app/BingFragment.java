package com.smartown.note.app;

import android.graphics.Color;

import com.smartown.library.base.BaseFragment;
import com.smartown.library.widget.BingView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Tiger
 * <p/>
 * 时间：2016-07-28 17:58
 * <p/>
 * 描述：
 */
public class BingFragment extends BaseFragment {

    private BingView bingView;
    private List<Data> datas;

    @Override
    protected void init() {
        datas = new ArrayList<>();
        datas.add(new Data(695, Color.parseColor("#9771c8")));
        datas.add(new Data(474, Color.parseColor("#db4f82")));
        datas.add(new Data(278, Color.parseColor("#b4c667")));
        datas.add(new Data(176, Color.parseColor("#e56373")));
        datas.add(new Data(123, Color.parseColor("#52b279")));
        datas.add(new Data(116, Color.parseColor("#399960")));
        findViews(R.layout.fragment_bing);
    }

    @Override
    protected void findViews() {
        bingView = (BingView) findViewById(R.id.bing);
    }

    @Override
    protected void initViews() {
        bingView.setValueGetter(new BingView.ValueGetter() {
            @Override
            public int getSize() {
                return datas.size();
            }

            @Override
            public int getColor(int position) {
                return datas.get(position).getColor();
            }

            @Override
            public float getCount(int position) {
                return datas.get(position).getCount();
            }
        });
        bingView.invalidate();
    }

    @Override
    protected void registerViews() {

    }

    public static class Data {

        private int count = 0;
        private int color = Color.BLACK;

        public Data(int count, int color) {
            this.count = count;
            this.color = color;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

    }
}
