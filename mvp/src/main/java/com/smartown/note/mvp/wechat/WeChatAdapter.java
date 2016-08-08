package com.smartown.note.mvp.wechat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;

import com.bumptech.glide.Glide;
import com.smartown.library.common.adapter.AdapterHelper;
import com.smartown.library.common.adapter.BasicAdapter;
import com.smartown.library.common.fragment.WebFragment;
import com.smartown.library.common.tool.Tool;
import com.smartown.note.mvp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Tiger
 * <p/>
 * 时间：2016-08-08 14:22
 * <p/>
 * 描述：
 */
public class WeChatAdapter implements AdapterHelper<NewsViewHolder> {

    private BasicAdapter basicAdapter;
    private Fragment fragment;
    private List<WeChatNews> newses;

    public WeChatAdapter(Fragment fragment) {
        this.fragment = fragment;
        newses = new ArrayList<>();
        basicAdapter = new BasicAdapter(this);
    }

    @Override
    public NewsViewHolder createViewHolder() {
        return new NewsViewHolder(LayoutInflater.from(fragment.getActivity()).inflate(R.layout.item_news, null));
    }

    @Override
    public void bindViewHolder(NewsViewHolder holder, final int position) {
        final WeChatNews news = newses.get(position);
        holder.getTitleTextView().setText(news.getTitle());
        Glide.with(fragment).load(news.getPicUrl()).asBitmap().into(holder.getImageView());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString(WebFragment.WEB_URL, news.getUrl());
                Tool.jump(fragment.getActivity(), news.getTitle(), WebFragment.class, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newses.size();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    public void setNewses(List<WeChatNews> newses) {
        this.newses = newses;
        basicAdapter.notifyDataSetChanged();
    }

    public BasicAdapter getBasicAdapter() {
        return basicAdapter;
    }

}
