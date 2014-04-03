package com.ourhfuu.mobilehfuu.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.android.volley.Response;
import com.android.volley.VolleyError;
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
    public static final int TYPE_HOT = 2;
    public static final int TYPE_SCHOOL_NEW = 1;
    public static final int TYPE_NOTICE = 6;
    public static final int TYPE_HEFEI_NEW = 4;

    public static final String ARTICLE_TYPE = "article_type";

    private ListView mListView;
    private SwipeRefreshLayout mRefreshLayout;

    public static ArticleListFragment newInstance(int type) {
        ArticleListFragment fragment = new ArticleListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARTICLE_TYPE, type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void setFragmentName() {
        mFragmentName = "ArticleListFragment";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRefreshLayout == null) {
            mService = new ArticleService(getActivity());
            mRefreshLayout = (SwipeRefreshLayout) inflater.inflate(R.layout.article_list, null);
            mListView = (ListView) mRefreshLayout.findViewById(R.id.main_content);
            mAdapter = new ArticleAdapter(getActivity(), new ArrayList<Article>());
            mListView.setAdapter(mAdapter);
            mRefreshLayout.setOnRefreshListener(mOnRefreshListener);
            mRefreshLayout.setColorScheme(android.R.color.holo_blue_bright,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light);
            mListView.setOnItemClickListener(this);
            //mAdapter.enableLoadMore();
            mAdapter.setLoadMoreListener(new BucketListAdapter.LoadMoreListener() {
                @Override
                public void onLoadMore() {
                    loadData();
                }
            });
        }
        if (mRefreshLayout.getParent() != null) {
            ((ViewGroup) mRefreshLayout.getParent()).removeAllViews();
        }
        return mRefreshLayout;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCid = getArguments().getInt(ARTICLE_TYPE, TYPE_HOT);
        }
        mParser = new ArticleParser();
    }


    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            mSinceId = 0;
            loadData();
        }
    };

    private synchronized void  loadData() {
        if (mSinceId != -100) {
            mService.getArticleList(mCid, mSinceId, mListener, mErrorListenr);
            mSinceId = -100;
        }
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
            mSinceId = 0;
            try {
                CLog.i(response);

                List<Article> list = mParser.parseList(response);
                if(list != null && list.size() > 0) {
                    mSinceId = list.get(list.size() - 1).getAid();
                    if (mRefreshLayout.isRefreshing()) {
                        mAdapter.clear();
                    }
                    mAdapter.addAll(list);
                    mAdapter.enableLoadMore();
                    CLog.i(list);
                } else {
                    mAdapter.disableLoadMore();
                }
            } catch (ParserException e) {
                mAdapter.disableLoadMore();
                CToast.showToast(getActivity(), e.getMessage());
            }
            if (mRefreshLayout.isRefreshing()) {
                mRefreshLayout.setRefreshing(false);
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
        int pos = position - mListView.getHeaderViewsCount();
        Intent intent = new Intent(getActivity(), ArticleDetailActivity.class);
        intent.putExtra(ArticleDetailActivity.ARTICLE_INTENT, mAdapter.getElement(pos));
        startActivity(intent);
    }
}
