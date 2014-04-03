package com.ourhfuu.mobilehfuu.app;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import com.ourhfuu.mobilehfuu.entity.LostThing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link com.ourhfuu.mobilehfuu.app.ArticleTypeFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class ArticleTypeFragment extends BaseTypeFragment implements ViewPager.OnPageChangeListener {

    private ViewPager mViewPager;
    private PagerTabStrip mTabStrip;
    private SpinnerAdapter mSpinnerAdapter;
    private ActionBar.OnNavigationListener mOnNavigationListener;
    private ArticlePagerAdapter mAdapter;
    private View mView;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment LostTypeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ArticleTypeFragment newInstance() {
        ArticleTypeFragment fragment = new ArticleTypeFragment();
        return fragment;
    }
    public ArticleTypeFragment() {

    }

    @Override
    protected void setFragmentName() {
        mFragmentName = "ArticleType";
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_lost_type, null);
            mViewPager = (ViewPager) mView.findViewById(R.id.pager);
            mViewPager.setAdapter(mAdapter);
            mViewPager.setOnPageChangeListener(this);
        }

        if (mView.getParent() != null) {
            ((ViewGroup) mView.getParent()).removeAllViews();
        }
        return mView;
    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (mAdapter == null) {
            mAdapter = new ArticlePagerAdapter();
        }
        if (mSpinnerAdapter == null) {
            mSpinnerAdapter = new ArrayAdapter<String>(activity,
                    R.layout.support_simple_spinner_dropdown_item, mAdapter.TITLE);
            mOnNavigationListener = new ActionBar.OnNavigationListener() {
                @Override
                public boolean onNavigationItemSelected(int i, long l) {
                    mViewPager.setCurrentItem(i);
                    return true;
                }
            };
        }
        if (getOnFragmentInteractionListener() != null) {
            getOnFragmentInteractionListener().onFragmentInteraction(mSpinnerAdapter, mOnNavigationListener);
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (getOnFragmentInteractionListener() != null) {
            getOnFragmentInteractionListener().onPagerSelect(position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private class ArticlePagerAdapter extends FragmentStatePagerAdapter {

        protected static final int ITEM_COUNT = 4;
        protected final String[] TITLE = new String[] {"热点关注", "通知公告", "校园动态", "大事件"};
        protected final int[] types = new int[]{ArticleListFragment.TYPE_HOT, ArticleListFragment.TYPE_SCHOOL_NEW,
                ArticleListFragment.TYPE_NOTICE, ArticleListFragment.TYPE_HEFEI_NEW};
        private Map<Integer, Fragment> mList;


        public ArticlePagerAdapter() {
            super(getFragmentManager());
            mList = new HashMap<Integer, Fragment>();
        }

        @Override
        public Fragment getItem(int position) {
            Fragment f = mList.get(position);
            if (f == null) {
                f = ArticleListFragment.newInstance(types[position]);
                mList.put(position, f);
            }
            return f;
        }

        @Override
        public int getCount() {
            return ITEM_COUNT;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLE[position];
        }
    }
}
