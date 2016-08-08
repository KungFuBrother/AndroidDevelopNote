package com.smartown.note.mvc.wechat;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smartown.library.common.tool.Tool;
import com.smartown.note.mvc.R;

/**
 * 作者：Tiger
 * <p/>
 * 时间：2016-08-05 13:40
 * <p/>
 * 描述：View
 */
public class NewsViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageView;
    private TextView titleTextView;

    public NewsViewHolder(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.news_image);
        titleTextView = (TextView) itemView.findViewById(R.id.news_title);

        LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int) ((float) Tool.getScreenWidth(itemView.getContext()) * 0.6f));
        imageView.setLayoutParams(imageParams);

        LinearLayout.LayoutParams itemParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        itemParams.topMargin = Tool.convertDpToPx(itemView.getContext(), 16);

        itemView.setLayoutParams(itemParams);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public TextView getTitleTextView() {
        return titleTextView;
    }

}
