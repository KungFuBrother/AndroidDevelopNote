package com.smartown.note.mvc.controller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.google.gson.Gson;
import com.smartown.library.base.BaseFragment;
import com.smartown.library.common.adapter.CommonAdapter;
import com.smartown.library.common.adapter.OnItemClickListener;
import com.smartown.library.common.adapter.ValueGetter;
import com.smartown.note.mvc.R;
import com.smartown.note.mvc.entity.EntityWeChatNews;
import com.smartown.note.mvc.operator.WeChatOperator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * 作者：Tiger
 * <p>
 * 时间：2016-08-04 16:09
 * <p>
 * 描述：
 */
public class MainFragment extends BaseFragment implements OnItemClickListener {

    private RecyclerView recyclerView;
    private List<EntityWeChatNews> newses;
    private WeChatOperator operator;
    private Gson gson;
    private CommonAdapter adapter;
    private ValueGetter valueGetter;

    @Override
    protected void init() {
        newses = new ArrayList<>();
        operator = new WeChatOperator();
        gson = new Gson();
        adapter = new CommonAdapter(getActivity(), newses);
        valueGetter = new ValueGetter() {
            @Override
            public String getValue(int position) {
                return newses.get(position).getTitle();
            }
        };
        adapter.setValueGetter(valueGetter);
        adapter.setOnItemClickListener(this);
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
                                    newses.add(gson.fromJson(jsonArray.optString(i), EntityWeChatNews.class));
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
    public void onItemClick(int position) {

    }
}
