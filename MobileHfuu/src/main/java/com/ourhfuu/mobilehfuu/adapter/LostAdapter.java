package com.ourhfuu.mobilehfuu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.ourhfuu.mobilehfuu.app.R;
import com.ourhfuu.mobilehfuu.entity.LostThing;
import com.ourhfuu.mobilehfuu.util.StringUtil;

import java.util.List;

/**
 * Created by sam on 14-3-30.
 */
public class LostAdapter extends BucketListAdapter<LostThing> {

    public LostAdapter(Context ctx, List<LostThing> elements) {
        super(ctx, elements);
    }

    public LostAdapter(Context ctx, List<LostThing> elements, int bucketSize) {
        super(ctx, elements, bucketSize);
    }

    @Override
    protected View newView(int position, LostThing element) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.article_list_item, null);
        ViewHolder holder = new ViewHolder();
        holder.titleView = (TextView) view.findViewById(R.id.a_name);
        holder.authorView = (TextView) view.findViewById(R.id.a_author);
        holder.timeView = (TextView) view.findViewById(R.id.a_time);
        view.setTag(holder);
        return view;
    }

    @Override
    protected void bindView(View view, int position, LostThing element) {
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.titleView.setText(element.getName());
        String type = element.getFlag() == 0 ? "丢失" : "捡到";
        holder.authorView.setText(element.getUsername() + "在" + element.getPlace() + type);
        holder.timeView.setText(StringUtil.friendlyTime(element.getTime()));
    }

    private static class ViewHolder {
        TextView titleView;
        TextView authorView;
        TextView timeView;
    }
}
