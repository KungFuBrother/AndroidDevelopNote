package com.smartown.note.mvc.wechat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.smartown.library.base.BaseFragment;
import com.smartown.library.common.adapter.AdapterHelper;
import com.smartown.library.common.adapter.BasicAdapter;
import com.smartown.library.common.fragment.WebFragment;
import com.smartown.library.common.tool.Tool;
import com.smartown.note.mvc.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * 作者：Tiger
 * <p/>
 * 时间：2016-08-04 16:09
 * <p/>
 * 描述：Controller
 */
public class MainFragment extends BaseFragment implements AdapterHelper<NewsViewHolder> {

    private RecyclerView recyclerView;
    private List<WeChatNews> newses;
    private WeChatOperator operator;
    private Gson gson;

    private BasicAdapter adapter;

    @Override
    protected void init() {
        newses = new ArrayList<>();
        operator = new WeChatOperator();
        gson = new Gson();
        adapter = new BasicAdapter(this);
        findViews(R.layout.common_recycler_list);
    }

    @Override
    protected void findViews() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }

    @Override
    protected void initViews() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
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
                if (!TextUtils.isEmpty(result)) {
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        if (jsonObject.optInt("code") == 200) {
                            JSONArray jsonArray = jsonObject.optJSONArray("newslist");
                            if (jsonArray != null) {
                                int length = jsonArray.length();
                                for (int i = 0; i < length; i++) {
                                    newses.add(gson.fromJson(jsonArray.optString(i), WeChatNews.class));
                                }
                                adapter.notifyDataSetChanged();
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public NewsViewHolder createViewHolder() {
        return new NewsViewHolder(layoutInflater.inflate(R.layout.item_news, null));
    }

    @Override
    public void bindViewHolder(NewsViewHolder holder, int position) {
        final WeChatNews news = newses.get(position);
        holder.getTitleTextView().setText(news.getTitle());
        Glide.with(this).load(news.getPicUrl()).asBitmap().into(holder.getImageView());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString(WebFragment.WEB_URL, news.getUrl());
                Tool.jump(getActivity(), news.getTitle(), WebFragment.class, bundle);
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

}
