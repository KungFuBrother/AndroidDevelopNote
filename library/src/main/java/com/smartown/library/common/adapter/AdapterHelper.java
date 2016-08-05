package com.smartown.library.common.adapter;

import android.support.v7.widget.RecyclerView;

/**
 * 作者：Tiger
 * <p>
 * 时间：2016-08-05 13:02
 * <p>
 * 描述：
 */
public interface AdapterHelper<T extends RecyclerView.ViewHolder> {

    T createViewHolder();

    void bindViewHolder(T holder, int position);

    int getItemCount();

    int getItemViewType(int position);

}
