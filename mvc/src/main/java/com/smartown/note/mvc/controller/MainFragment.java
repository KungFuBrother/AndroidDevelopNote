package com.smartown.note.mvc.controller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.smartown.library.base.BaseFragment;
import com.smartown.note.mvc.R;
import com.smartown.note.mvc.operator.WeChatOperator;

import rx.Subscriber;

/**
 * 作者：Tiger
 * <p/>
 * 时间：2016-08-04 16:09
 * <p/>
 * 描述：
 */
public class MainFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private WeChatOperator operator;

    @Override
    protected void init() {
        operator = new WeChatOperator();
        findViews(R.layout.common_recycler_list);
    }

    @Override
    protected void findViews() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }

    @Override
    protected void initViews() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    protected void registerViews() {

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        operator.request(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(String result) {
                System.out.println(result);
            }
        });
    }
}
