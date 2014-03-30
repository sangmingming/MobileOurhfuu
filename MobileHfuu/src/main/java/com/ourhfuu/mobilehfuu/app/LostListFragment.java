package com.ourhfuu.mobilehfuu.app;

import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
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
    private PullToRefreshListView mListView;
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
        View view = inflater.inflate(R.layout.lost_list, null);
        mListView = (PullToRefreshListView) view.findViewById(R.id.lost_list);
        mListView.setOnRefreshListener(mOnRefreshListener);
        mAdapter = new LostAdapter(getActivity(), new ArrayList<LostThing>());
        mService = new LostThingService(getActivity());
        mAdapter.enableLoadMore();
        mAdapter.setLoadMoreListener(mLoadMoreListener);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
        return view;
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
        mService.getLostThingList(mType, mSinceId, mListener, mErrorListener);
    }

    @Override
    protected void setFragmentName() {
        mFragmentName = "lost_list";
    }

    private Response.Listener<String> mListener = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            try {
                List<LostThing> list = mParser.parseList(response);
                if (mListView.isRefreshing()) {
                    mAdapter.clear();
                }
                mAdapter.addAll(list);
                mSinceId = list.get(list.size() - 1).getId();
                mAdapter.enableLoadMore();
            } catch (ParserException e) {
                CToast.showToast(getActivity(), e.getMessage());
                mAdapter.disableLoadMore();
            }
            if (mListView.isRefreshing()) {
                mListView.onRefreshComplete();
            }
        }
    };

    private Response.ErrorListener mErrorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            CToast.showToast(getActivity(), error.getMessage());
            mAdapter.disableLoadMore();
            if (mListView.isRefreshing()) {
                mListView.onRefreshComplete();
            }
        }
    };

    private PullToRefreshBase.OnRefreshListener mOnRefreshListener = new PullToRefreshBase.OnRefreshListener() {
        @Override
        public void onRefresh(PullToRefreshBase refreshView) {
            mSinceId = 0;
            String label = DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
                    DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
            refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
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
        int pos = position - mListView.getRefreshableView().getHeaderViewsCount();
        LostDetailActivity.openLostDetail(getActivity(), mAdapter.getElement(pos));
    }
}
