package com.example.mydemo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class HorizontalScrollAdapter extends PagerAdapter {

    private Context context;

    private List<List<ItemBean>> lists;

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public HorizontalScrollAdapter(Context context, List<List<ItemBean>> lists) {
        this.context = context;
        this.lists = lists;
    }

    /**
     * 决定了有多少页
     *
     * @return
     */
    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        RecyclerView recyclerView = new RecyclerView(context);
        GridLayoutManager layoutManager = new GridLayoutManager(context, 4, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        HorizontalScrollItemAdapter itemAdapter = new HorizontalScrollItemAdapter(context, lists.get(position));
        itemAdapter.setOnItemClickListener(new HorizontalScrollItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int itemPosition, ItemBean data) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(itemPosition, position, data);
                }
            }
        });
        recyclerView.setAdapter(itemAdapter);
        container.addView(recyclerView);//将recyclerView作为子视图加入 container即为viewpager
        return recyclerView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    public interface OnItemClickListener {
        //参数（父组件，当前单击的View,单击的View的位置，数据）
        void onItemClick(int position, int currentPage, ItemBean data);
    }
}