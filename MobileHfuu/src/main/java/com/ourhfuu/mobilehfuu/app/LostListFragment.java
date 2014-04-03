package com.ourhfuu.mobilehfuu.app;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.ourhfuu.mobilehfuu.adapter.BucketListAdapter;
import com.ourhfuu.mobilehfuu.adapter.LostAdapter;
import com.ourhfuu.mobilehfuu.entity.LostThing;
import com.ourhfuu.mobilehfuu.util.CToast;
import com.ourhfuu.mobilehfuu.webservice.LostThingService;
import com.ourhfuu.mobilehfuu.webservice.parser.LostThingParser;
import com.ourhfuu.mobilehfuu.webservice.parser.ParserException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sam on 14-3-30.
 */
public class LostListFragment extends BaseFragment implements AdapterView.OnItemClickListener{
    public final static String LOST_TYPE = "lost_type";

    private int mType;
    private ListView mListView;
    private SwipeRefreshLayout mRefreshLayout;
    private LostThingService mService;
    private LostThingParser mParser;
    private LostAdapter mAdapter;
    private int mSinceId = 0;


    public static LostListFragment newInstance(int type) {
        LostListFragment fragment = new LostListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(LOST_TYPE, type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRefreshLayout == null) {
            mRefreshLayout = (SwipeRefreshLayout) inflater.inflate(R.layout.lost_list, null);
            mListView = (ListView) mRefreshLayout.findViewById(R.id.lost_list);
            mRefreshLayout.setOnRefreshListener(mOnRefreshListener);
            mRefreshLayout.setColorScheme(android.R.color.holo_blue_bright,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light);
            mAdapter = new LostAdapter(getActivity(), new ArrayList<LostThing>());
            mService = new LostThingService(getActivity());
            mListView.setAdapter(mAdapter);
            mAdapter.enableLoadMore();
            mAdapter.setLoadMoreListener(mLoadMoreListener);
            mListView.setOnItemClickListener(this);
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
            mType = getArguments().getInt(LOST_TYPE, LostThing.LOST);
        }
        mParser = new LostThingParser();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mService.stop();
    }

    private void loadData() {
        if (mSinceId != -100) {
            mService.getLostThingList(mType, mSinceId, mListener, mErrorListener);
            mSinceId = -100;
        }
    }

    @Override
    protected void setFragmentName() {
        mFragmentName = "lost_list";
    }

    private Response.Listener<String> mListener = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            mSinceId = 0;
            try {
                List<LostThing> list = mParser.parseList(response);
                if (mRefreshLayout.isRefreshing()) {
                    mAdapter.clear();
                }
                mAdapter.addAll(list);
                mSinceId = list.get(list.size() - 1).getId();
                mAdapter.enableLoadMore();
            } catch (ParserException e) {
                CToast.showToast(getActivity(), e.getMessage());
                mAdapter.disableLoadMore();
            }
            if (mRefreshLayout.isRefreshing()) {
                mRefreshLayout.setRefreshing(false);
            }
        }
    };

    private Response.ErrorListener mErrorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            CToast.showToast(getActivity(), error.getMessage());
            mAdapter.disableLoadMore();
            if (mRefreshLayout.isRefreshing()) {
                mRefreshLayout.setRefreshing(false);
            }
        }
    };

    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            mSinceId = 0;
            loadData();
        }
    };

    private BucketListAdapter.LoadMoreListener mLoadMoreListener = new BucketListAdapter.LoadMoreListener() {
        @Override
        public void onLoadMore() {
            loadData();
        }
    };

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int pos = position - mListView.getHeaderViewsCount();
        LostDetailActivity.openLostDetail(getActivity(), mAdapter.getElement(pos));
    }
}
