package com.smartown.library.common.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smartown.note.library.R;

import java.util.List;

/**
 * Created by Tiger on 2016-07-07.
 */
public class CommonAdapter extends RecyclerView.Adapter<CommonViewHolder> {

    private Context context;
    private List dataTList;
    private ValueGetter valueGetter;
    private OnItemClickListener onItemClickListener;

    public CommonAdapter(Context context, List dataTList) {
        this.context = context;
        this.dataTList = dataTList;
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommonViewHolder(LayoutInflater.from(context).inflate(R.layout.common_list_item, null));
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, final int position) {
        if (valueGetter != null) {
            //通过内容获取工具取值
            holder.getTextView().setText(valueGetter.getValue(position));
            holder.getTextView().setTextColor(valueGetter.getColor(position));
        }
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return dataTList.size();
    }

    /**
     * 设置内容获取工具
     *
     * @param valueGetter
     */
    public void setValueGetter(ValueGetter valueGetter) {
        this.valueGetter = valueGetter;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
