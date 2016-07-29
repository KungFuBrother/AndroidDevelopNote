package com.smartown.note.app;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.smartown.library.base.BaseFragment;
import com.smartown.library.common.adapter.CommonAdapter;
import com.smartown.library.common.adapter.OnItemClickListener;
import com.smartown.library.common.adapter.ValueGetter;
import com.smartown.note.app.model.ModelMenuItem;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Tiger
 * <p/>
 * 时间：2016-07-28 17:58
 * <p/>
 * 描述：
 */
public class MainFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private List<ModelMenuItem> menuItems;
    private CommonAdapter<ModelMenuItem> adapter;

    @Override
    protected void init() {
        menuItems = new ArrayList<>();
        menuItems.add(new ModelMenuItem("MVC", Fragment.class));
        menuItems.add(new ModelMenuItem("MVP", Fragment.class));
        menuItems.add(new ModelMenuItem("MVVM", Fragment.class));
        adapter = new CommonAdapter<>(getActivity(), menuItems);
        adapter.setValueGetter(new ValueGetter<ModelMenuItem>() {
            @Override
            public String getValue(ModelMenuItem modelMenuItem) {
                return modelMenuItem.getLable();
            }
        });
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
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

}
