package com.ourhfuu.mobilehfuu.app;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.ourhfuu.mobilehfuu.adapter.ArticleAdapter;
import com.ourhfuu.mobilehfuu.adapter.BucketListAdapter;
import com.ourhfuu.mobilehfuu.entity.Article;
import com.ourhfuu.mobilehfuu.util.CLog;
import com.ourhfuu.mobilehfuu.util.CToast;
import com.ourhfuu.mobilehfuu.webservice.ArticleService;
import com.ourhfuu.mobilehfuu.webservice.parser.ArticleParser;
import com.ourhfuu.mobilehfuu.webservice.parser.ParserException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sam on 14-3-30.
 */
public class ArticleListFragment extends BaseFragment implements AdapterView.OnItemClickListener{
    private int mSinceId = 0;
    private int mCid = 6;
    private ArticleService mService;
    private ArticleAdapter mAdapter;
    private ArticleParser mParser;

    private PullToRefreshListView mListView;

    public static ArticleListFragment newInstance() {
        return new ArticleListFragment();
    }

    @Override
    protected void setFragmentName() {
        mFragmentName = "ArticleListFragment";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mService = new ArticleService(getActivity());
        View view = inflater.inflate(R.layout.article_list, null);
        mListView = (PullToRefreshListView) view.findViewById(R.id.main_content);
        mAdapter = new ArticleAdapter(getActivity(), new ArrayList< Article >());
        mParser = new ArticleParser();
        mListView.setAdapter(mAdapter);
        mListView.setOnRefreshListener(mOnRefreshListener);
        mListView.setOnItemClickListener(this);
        mAdapter.enableLoadMore();
        mAdapter.setLoadMoreListener(new BucketListAdapter.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                loadData();
            }
        });
        return view;
    }

    private PullToRefreshBase.OnRefreshListener mOnRefreshListener = new PullToRefreshBase.OnRefreshListener<ListView>() {
        @Override
        public void onRefresh(PullToRefreshBase<ListView> refreshView) {
            mSinceId = 0;
            String label = DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
                    DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
            refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
            loadData();
        }
    };

    private void loadData() {
        mService.getArticleList(mCid, mSinceId, mListener, mErrorListenr);
    }

    private Response.ErrorListener mErrorListenr = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            CToast.showToast(getActivity(), error.getMessage());
        }
    };

    private Response.Listener<String> mListener = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            if (mListView.isRefreshing()) {
                mListView.onRefreshComplete();
            }
            try {
                CLog.i(response);
                List<Article> list = mParser.parseList(response);
                if(list != null && list.size() > 0) {
                    if (mListView.isRefreshing()) {
                        mAdapter.clear();
                    }
                    mAdapter.addAll(list);
                    mAdapter.enableLoadMore();
                    mSinceId = list.get(list.size() - 1).getAid();
                    CLog.i(list);
                } else {
                    mAdapter.disableLoadMore();
                }
            } catch (ParserException e) {
                mAdapter.disableLoadMore();
                CToast.showToast(getActivity(), e.getMessage());
            }
        }
    };

    @Override
    public void onDetach() {
        super.onDetach();
        mService.stop();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int pos = position - mListView.getRefreshableView().getHeaderViewsCount();
        Intent intent = new Intent(getActivity(), ArticleDetailActivity.class);
        intent.putExtra(ArticleDetailActivity.ARTICLE_INTENT, mAdapter.getElement(pos));
        startActivity(intent);
    }
}
