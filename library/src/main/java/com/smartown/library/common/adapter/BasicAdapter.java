package com.smartown.library.common.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by Tiger on 2016-07-07.
 */
public final class BasicAdapter extends RecyclerView.Adapter {

    private AdapterHelper adapterHelper;

    public BasicAdapter(AdapterHelper adapterHelper) {
        this.adapterHelper = adapterHelper;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return adapterHelper.createViewHolder();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        adapterHelper.bindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return adapterHelper.getItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        return adapterHelper.getItemViewType(position);
    }

}
