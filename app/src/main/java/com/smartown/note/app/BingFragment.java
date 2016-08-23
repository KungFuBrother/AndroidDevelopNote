package com.smartown.note.app;

import android.graphics.Color;

import com.smartown.library.base.BaseFragment;
import com.smartown.library.widget.BingView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Tiger
 * <p>
 * 时间：2016-07-28 17:58
 * <p>
 * 描述：
 */
public class BingFragment extends BaseFragment {

    private BingView bingView;
    private List<Data> datas;

    @Override
    protected void init() {
        datas = new ArrayList<>();
        datas.add(new Data(100, Color.RED));
        datas.add(new Data(50, Color.BLACK));
        datas.add(new Data(40, Color.BLUE));
        datas.add(new Data(30, Color.GREEN));
        datas.add(new Data(10, Color.GRAY));
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
