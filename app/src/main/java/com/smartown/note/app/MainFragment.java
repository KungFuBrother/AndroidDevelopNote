package com.smartown.note.app;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.smartown.library.base.BaseFragment;
import com.smartown.library.common.adapter.CommonAdapter;
import com.smartown.library.common.adapter.OnItemClickListener;
import com.smartown.library.common.adapter.ValueGetter;
import com.smartown.library.common.tool.ToastTool;
import com.smartown.library.common.tool.Tool;
import com.smartown.note.app.model.ModelMenuItem;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Tiger
 * <p>
 * 时间：2016-07-28 17:58
 * <p>
 * 描述：
 */
public class MainFragment extends BaseFragment implements OnItemClickListener {

    private RecyclerView recyclerView;
    private List<ModelMenuItem> menuItems;
    private ValueGetter valueGetter;

    @Override
    public void onResume() {
        super.onResume();
        initMenuItems();
    }

    @Override
    protected void init() {
        valueGetter = new ValueGetter() {

            @Override
            public String getValue(int position) {
                return menuItems.get(position).getLable();
            }

            @Override
            public int getColor(int position) {
                String packageName = menuItems.get(position).getPackageName();
                if (!Tool.isAppInstalled(getActivity(), packageName)) {
                    return Color.GRAY;
                }
                return Color.BLACK;
            }

        };
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

    private void initMenuItems() {
        menuItems = new ArrayList<>();
        menuItems.add(new ModelMenuItem("MVC", "com.smartown.note.mvc"));
        menuItems.add(new ModelMenuItem("MVP", "com.smartown.note.mvp"));
        menuItems.add(new ModelMenuItem("MVVM", "com.smartown.note.mvvm"));
        CommonAdapter adapter = new CommonAdapter(getActivity(), menuItems);
        adapter.setValueGetter(valueGetter);
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int position) {
        String packageName = menuItems.get(position).getPackageName();
        if (Tool.isAppInstalled(getActivity(), packageName)) {
            Tool.startApp(getActivity(), packageName);
        } else {
            ToastTool.show("未安装" + packageName);
        }
    }
}
