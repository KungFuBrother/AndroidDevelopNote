package com.smartown.library.common.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.smartown.note.library.R;

import java.util.List;

/**
 * 作者：Tiger
 * <p>
 * 时间：2016-08-05 13:09
 * <p>
 * 描述：
 */
public class SimpleTextAdapter implements AdapterHelper<CommonViewHolder> {

    private BasicAdapter basicAdapter;
    private Context context;
    private List dataTList;
    private ValueGetter valueGetter;
    private OnItemClickListener onItemClickListener;

    public SimpleTextAdapter(Context context, List dataTList) {
        this.context = context;
        this.dataTList = dataTList;
        basicAdapter = new BasicAdapter(this);
    }

    @Override
    public CommonViewHolder createViewHolder() {
        return new CommonViewHolder(LayoutInflater.from(context).inflate(R.layout.common_list_item, null));
    }

    @Override
    public void bindViewHolder(CommonViewHolder holder, final int position) {
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

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    public void setValueGetter(ValueGetter valueGetter) {
        this.valueGetter = valueGetter;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public BasicAdapter getBasicAdapter() {
        return basicAdapter;
    }

}
