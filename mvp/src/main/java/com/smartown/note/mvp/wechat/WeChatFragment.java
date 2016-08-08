package com.smartown.note.mvp.wechat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.smartown.library.base.BaseFragment;
import com.smartown.note.mvp.R;

import java.util.List;

/**
 * 作者：Tiger
 * <p/>
 * 时间：2016-08-08 14:16
 * <p/>
 * 描述：
 */
public class WeChatFragment extends BaseFragment implements WeChatView {

    private RecyclerView recyclerView;

    private WeChatAdapter adapter;
    private WeChatPresenter presenter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.request();
    }

    @Override
    protected void init() {
        adapter = new WeChatAdapter(this);
        presenter = new WeChatPresenter(this);
        findViews(R.layout.common_recycler_list);
    }

    @Override
    protected void findViews() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }

    @Override
    protected void initViews() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter.getBasicAdapter());
    }

    @Override
    protected void registerViews() {

    }

    @Override
    public void showData(List<WeChatNews> newses) {
        adapter.setNewses(newses);
    }

}
