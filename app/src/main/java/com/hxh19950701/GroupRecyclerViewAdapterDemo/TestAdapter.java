package com.hxh19950701.GroupRecyclerViewAdapterDemo;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by 绪浩 on 2016/12/24.
 */

public class TestAdapter extends GroupRecyclerViewAdapter<TestAdapter.GroupHeaderViewHolder, TestAdapter.ContentItemViewHolder> {

    @Override
    public int getGroupHeaderCount() {
        return 100000;
    }

    @Override
    public int getContentItemCount(int groupPosition) {
        return groupPosition + 1;
    }

    @Override
    public GroupHeaderViewHolder onCreateGroupHeaderViewHolder(ViewGroup parent) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new GroupHeaderViewHolder(view);
    }

    @Override
    public ContentItemViewHolder onCreateContentItemViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new ContentItemViewHolder(view);
    }

    @Override
    public void onBindGroupHeaderViewHolder(GroupHeaderViewHolder holder, int groupPosition) {
        holder.bindData(groupPosition + 1);
    }

    @Override
    public void onBindContentItemViewHolder(ContentItemViewHolder holder, int groupPosition, int contentPosition) {
        holder.bindData(contentPosition + 1);
    }

    public static class GroupHeaderViewHolder extends RecyclerView.ViewHolder {

        private TextView tv;

        public GroupHeaderViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }

        public void bindData(int data) {
            tv.setText("这是第" + data + "组");
            tv.setBackgroundColor(Color.parseColor("#AAAAAA"));
        }
    }

    public static class ContentItemViewHolder extends RecyclerView.ViewHolder {

        private TextView tv;

        public ContentItemViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }

        public void bindData(int data) {
            tv.setText("  这是第" + data + "项");
        }
    }
}
