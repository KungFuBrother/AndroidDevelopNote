package com.smartown.note.app;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.smartown.library.base.BaseFragment;
import com.smartown.library.common.adapter.OnItemClickListener;
import com.smartown.library.common.adapter.SimpleTextAdapter;
import com.smartown.library.common.tool.Tool;

import java.util.List;

/**
 * 作者：Tiger
 * <p>
 * 时间：2016-07-28 17:58
 * <p>
 * 描述：
 */
public class RunningAppFragment extends BaseFragment {

    private RecyclerView recyclerView;

    @Override
    public void onResume() {
        super.onResume();
        showRunningAppProcessInfo();
    }

    @Override
    protected void init() {
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

    private void showRunningAppProcessInfo() {
        final ActivityManager activityManager = (ActivityManager) getActivity().getSystemService(Context.ACTIVITY_SERVICE);
        final List<ActivityManager.RunningAppProcessInfo> processes = activityManager.getRunningAppProcesses();
        SimpleTextAdapter adapter = new SimpleTextAdapter(getActivity());
        adapter.setValueGetter(new SimpleTextAdapter.ValueGetter() {

            @Override
            public String getValue(int position) {
                return processes.get(position).processName;
            }

            @Override
            public int getColor(int position) {
                return Color.BLACK;
            }

            @Override
            public int getSize() {
                return processes.size();
            }

        });
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                ActivityManager.RunningAppProcessInfo processInfo = processes.get(position);
                Tool.startApp(getActivity(), processInfo.processName);
            }
        });
        recyclerView.setAdapter(adapter.getBasicAdapter());
    }

}
