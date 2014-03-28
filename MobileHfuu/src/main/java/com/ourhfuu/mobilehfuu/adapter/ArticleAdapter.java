package com.ourhfuu.mobilehfuu.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.ourhfuu.mobilehfuu.app.ArticleDetailActivity;
import com.ourhfuu.mobilehfuu.app.R;
import com.ourhfuu.mobilehfuu.entity.Article;

import java.util.ArrayList;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: sam
 * Date: 12/1/13
 * Time: 4:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class ArticleAdapter extends BaseAdapter {
    private Context mContext;
    private List<Article> mArticleList;
    public ArticleAdapter(Context ctx, List<Article> elements) {
        mArticleList = elements;
        mContext = ctx;
    }



    protected View newView(int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.article_list_item, null);
        ViewHolder holder = new ViewHolder();
        holder.titleView = (TextView) view.findViewById(R.id.a_name);
        holder.authorView = (TextView) view.findViewById(R.id.a_author);
        holder.timeView = (TextView) view.findViewById(R.id.a_time);
        view.setTag(holder);
        return view;
    }

    protected void bindView(View view, int position, final Article element) {
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.titleView.setText(element.getTitle());
        holder.timeView.setText(DateUtils.formatElapsedTime(element.getDateline()));
        holder.authorView.setText(element.getUsername());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ArticleDetailActivity.class);
                intent.putExtra(ArticleDetailActivity.ARTICLE_INTENT, element);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getCount() {
        return mArticleList.size();
    }

    @Override
    public Object getItem(int position) {
        return mArticleList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mArticleList.get(position).getAid();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = newView(position);
        }
        Article article = mArticleList.get(position);
        bindView(convertView, position, article);
        return convertView;
    }

    public void addAll(List<Article> list) {
        if (mArticleList == null) {
            mArticleList = new ArrayList<Article>();
        }
        mArticleList.addAll(list);
        notifyDataSetChanged();
    }

    public void add(Article list) {
        if (mArticleList == null) {
            mArticleList = new ArrayList<Article>();
        }
        mArticleList.add(list);
        notifyDataSetChanged();
    }

    public void clear() {
        if (mArticleList == null) {
            mArticleList = new ArrayList<Article>();
        }
        mArticleList.clear();
    }

    private static class ViewHolder {
        TextView titleView;
        TextView authorView;
        TextView timeView;
    }
}
