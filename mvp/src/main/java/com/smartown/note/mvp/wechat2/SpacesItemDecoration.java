package com.smartown.note.mvp.wechat2;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

    private SpaceGetter spaceGetter;

    public SpacesItemDecoration(SpaceGetter spaceGetter) {
        this.spaceGetter = spaceGetter;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.top = spaceGetter.getTop(parent.getChildLayoutPosition(view));
        outRect.left = spaceGetter.getLeft(parent.getChildLayoutPosition(view));
        outRect.right = spaceGetter.getRight(parent.getChildLayoutPosition(view));
        outRect.bottom = spaceGetter.getBottom(parent.getChildLayoutPosition(view));
    }

    public static class SpaceGetter {

        public int getTop(int position) {
            return 4;
        }

        public int getBottom(int position) {
            return 4;
        }

        public int getLeft(int position) {
            return 4;
        }

        public int getRight(int position) {
            return 4;
        }

    }

}