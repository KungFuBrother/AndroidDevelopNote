package com.smartown.library.common.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smartown.library.R;

/**
 * Created by Tiger on 2016-07-07.
 */
public class CommonViewHolder extends RecyclerView.ViewHolder {

    private TextView textView;

    public CommonViewHolder(View itemView) {
        super(itemView);
        itemView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        textView = (TextView) itemView.findViewById(R.id.common_list_item);
    }

    public TextView getTextView() {
        return textView;
    }
}
