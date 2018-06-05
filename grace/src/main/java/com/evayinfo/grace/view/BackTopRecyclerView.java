package com.evayinfo.grace.view;

import android.app.ActionBar;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by DEVIN on 2017/9/22.
 */

public class BackTopRecyclerView extends RecyclerView implements View.OnClickListener {

    boolean isFabShow = false;
    boolean isScorllToTop = false;

    private OnRecyclerViewScollToTopListener mScrollToListener;

    public void setOnScrollToTopListener(OnRecyclerViewScollToTopListener listener) {
        this.mScrollToListener = listener;
    }


    public BackTopRecyclerView(Context context) {
        this(context, null);
    }

    public BackTopRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BackTopRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        addOnScrollListener(new BackTopScrollListener());
    }

    @Override
    public void onClick(View view) {
        scrollToPosition(0);
    }

    private class BackTopScrollListener extends OnScrollListener {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);

            final LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof LinearLayoutManager) {
                final int firstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();

                if (firstVisibleItemPosition == 0 && mScrollToListener != null) {
                    isScorllToTop = false;
                    mScrollToListener.onScrollIdle();
                }
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (dy < 0) { //向上滑并且返回顶部按钮没有显示
                if (!isScorllToTop && mScrollToListener != null) {
                    isScorllToTop = true;
                    mScrollToListener.onScrollToTop();
                }

            } else if (dy > 0) { //向下滑,并且返回顶部按钮隐藏

                if (isScorllToTop && mScrollToListener != null) {
                    isScorllToTop = false;
                    mScrollToListener.onScrollIdle();
                }
            }

        }
    }


    public interface OnRecyclerViewScollToTopListener {
        /**
         * 向上滑动
         */
        void onScrollToTop();

        /**
         * 滑动停止
         */
        void onScrollIdle();
    }


}
