package com.example.mydemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HorizontalScrollItemAdapter extends RecyclerView.Adapter<HorizontalScrollItemAdapter.HorizontalItemHolder> {

    private List<ItemBean> itemBeans;

    private Context context;

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public HorizontalScrollItemAdapter(Context context, List<ItemBean> itemBeans) {
        this.context = context;
        this.itemBeans = itemBeans;
    }

    @NonNull
    @Override
    public HorizontalItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, viewGroup, false);

        return new HorizontalItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalItemHolder viewHolder, int position) {
        ItemBean itemBean = itemBeans.get(position);
        viewHolder.setItem(itemBean);

        viewHolder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //程序执行到此，会去执行具体实现的onItemClick()方法
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position, itemBean);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemBeans.size();
    }

    public interface OnItemClickListener {
        //参数（父组件，当前单击的View,单击的View的位置，数据）
        void onItemClick(int position, ItemBean data);
    }

    public static class HorizontalItemHolder extends RecyclerView.ViewHolder {
        TextView verticalText;

        LinearLayout itemLayout;

        public HorizontalItemHolder(@NonNull View itemView) {
            super(itemView);
            verticalText = itemView.findViewById(R.id.verticalText);
            itemLayout = itemView.findViewById(R.id.ll_item);
        }

        public void setItem(ItemBean item) {
            verticalText.setText(item.title);
        }
    }
}
