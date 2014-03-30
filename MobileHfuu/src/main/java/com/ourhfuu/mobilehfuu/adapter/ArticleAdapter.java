package com.ourhfuu.mobilehfuu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.ourhfuu.mobilehfuu.app.R;
import com.ourhfuu.mobilehfuu.entity.Article;
import com.ourhfuu.mobilehfuu.util.StringUtil;

import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: sam
 * Date: 12/1/13
 * Time: 4:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class ArticleAdapter extends BucketListAdapter<Article> {

    public ArticleAdapter(Context ctx, List<Article> elements) {
        super(ctx, elements);
    }

    public ArticleAdapter(Context ctx, List<Article> elements, int bucketSize) {
        super(ctx, elements, bucketSize);
    }

    protected void bindView(View view, int position, final Article element) {
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.titleView.setText(element.getTitle());
        holder.timeView.setText(StringUtil.friendlyTime(element.getDateline()));
        holder.authorView.setText(element.getUsername());
    }

    @Override
    public long getItemId(int position) {
        return getElement(position).getAid();
    }

    @Override
    protected View newView(int position, Article element) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.article_list_item, null);
        ViewHolder holder = new ViewHolder();
        holder.titleView = (TextView) view.findViewById(R.id.a_name);
        holder.authorView = (TextView) view.findViewById(R.id.a_author);
        holder.timeView = (TextView) view.findViewById(R.id.a_time);
        view.setTag(holder);
        return view;
    }

    private static class ViewHolder {
        TextView titleView;
        TextView authorView;
        TextView timeView;
    }
}
