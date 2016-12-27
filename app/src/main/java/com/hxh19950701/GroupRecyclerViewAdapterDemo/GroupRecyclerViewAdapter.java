package com.hxh19950701.GroupRecyclerViewAdapterDemo;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.Arrays;

/**
 * 分组的RecyclerViewAdapter
 */

public abstract class GroupRecyclerViewAdapter
                <
                GroupHeaderViewHolder extends RecyclerView.ViewHolder,
                ContentItemViewHolder extends RecyclerView.ViewHolder
                >
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int ITEM_TYPE_GROUP_HEADER = 0xFFFFFF00;
    public static final int ITEM_TYPE_CONTENT = 0xFFFFFF01;

    public abstract int getGroupHeaderCount();

    public abstract int getContentItemCount(int groupPosition);

    public abstract GroupHeaderViewHolder onCreateGroupHeaderViewHolder(ViewGroup parent);

    public abstract ContentItemViewHolder onCreateContentItemViewHolder(ViewGroup parent, int viewType);

    public abstract void onBindGroupHeaderViewHolder(GroupHeaderViewHolder holder, int groupPosition);

    public abstract void onBindContentItemViewHolder(ContentItemViewHolder holder, int groupPosition, int contentPosition);

    private int[] groupHeaderAbsPosition = null;
    private int itemCount = 0;

    public GroupRecyclerViewAdapter() {
        initPosition();
    }

    private void initPosition() {
        groupHeaderAbsPosition = new int[getGroupHeaderCount()];
        for (int groupPosition = 0; groupPosition != getGroupHeaderCount(); ++groupPosition) {
            groupHeaderAbsPosition[groupPosition] = itemCount;
            itemCount += getContentItemCount(groupPosition) + 1;
        }
    }

    @Override
    public int getItemViewType(int absPosition) {
        return Arrays.binarySearch(groupHeaderAbsPosition, absPosition) < 0 ? ITEM_TYPE_CONTENT : ITEM_TYPE_GROUP_HEADER;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ITEM_TYPE_GROUP_HEADER:
                return onCreateGroupHeaderViewHolder(parent);
            default:
                return onCreateContentItemViewHolder(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int absPosition) {
        final int position = Arrays.binarySearch(groupHeaderAbsPosition, absPosition);
        if (position < 0) {
            int groupPosition = -(position + 2);
            int contentPosition = absPosition - groupHeaderAbsPosition[groupPosition] - 1;
            ContentItemViewHolder contentItemViewHolder = (ContentItemViewHolder) holder;
            onBindContentItemViewHolder(contentItemViewHolder, groupPosition, contentPosition);
        } else {
            GroupHeaderViewHolder groupHeaderViewHolder = (GroupHeaderViewHolder) holder;
            onBindGroupHeaderViewHolder(groupHeaderViewHolder, position);
        }
    }

    @Override
    public int getItemCount() {
        return itemCount;
    }

    public final void notifyItemPositionChanged() {
        initPosition();
    }
}