package com.smartown.note.app;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.smartown.library.base.BaseFragment;
import com.smartown.library.common.adapter.OnItemClickListener;
import com.smartown.library.common.adapter.SimpleTextAdapter;
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
public class MainFragment extends BaseFragment {

    private RecyclerView recyclerView;

    @Override
    public void onResume() {
        super.onResume();
        showApps();
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

    private void showApps() {
        final List<ModelMenuItem> menuItems = new ArrayList<>();
        menuItems.add(new ModelMenuItem(0, "MVC", "com.smartown.note.mvc"));
        menuItems.add(new ModelMenuItem(0, "MVP", "com.smartown.note.mvp"));
        menuItems.add(new ModelMenuItem(0, "MVVM", "com.smartown.note.mvvm"));
        menuItems.add(new ModelMenuItem(1, "RunningAppFragment", RunningAppFragment.class.getName()));
        menuItems.add(new ModelMenuItem(1, "BingFragment", BingFragment.class.getName()));
        menuItems.add(new ModelMenuItem(1, "LEDFragment", LEDFragment.class.getName()));
        menuItems.add(new ModelMenuItem(1, "InstallmentFragment", InstallmentFragment.class.getName()));
//        CommonAdapter adapter = new CommonAdapter(getActivity(), menuItems);
//        adapter.setValueGetter(valueGetter);
//        adapter.setOnItemClickListener(this);
//        recyclerView.setAdapter(adapter);
        SimpleTextAdapter adapter = new SimpleTextAdapter(getActivity());
        adapter.setValueGetter(new SimpleTextAdapter.ValueGetter() {

            @Override
            public String getValue(int position) {
                return menuItems.get(position).getLabel();
            }

            @Override
            public int getColor(int position) {
                if (menuItems.get(position).getType() == 0) {
                    String packageName = menuItems.get(position).getValue();
                    if (!Tool.isAppInstalled(getActivity(), packageName)) {
                        return Color.GRAY;
                    }
                }
                return Color.BLACK;
            }

            @Override
            public int getSize() {
                return menuItems.size();
            }

        });
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                ModelMenuItem item = menuItems.get(position);
                switch (item.getType()) {
                    case 0:
                        //Application
                        String packageName = item.getValue();
                        if (Tool.isAppInstalled(getActivity(), packageName)) {
                            Tool.startApp(getActivity(), packageName);
                        } else {
                            ToastTool.show("app " + packageName + " not installed");
                        }
                        break;
                    case 1:
                        //Fragment
                        try {
                            Tool.jump(getActivity(), item.getLabel(), Class.forName(item.getValue()));
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        break;
                }
            }
        });
        recyclerView.setAdapter(adapter.getBasicAdapter());
    }

}
