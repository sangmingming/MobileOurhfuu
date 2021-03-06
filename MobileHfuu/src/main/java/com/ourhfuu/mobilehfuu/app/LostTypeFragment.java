package com.ourhfuu.mobilehfuu.app;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import com.ourhfuu.mobilehfuu.entity.LostThing;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link LostTypeFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class LostTypeFragment extends BaseTypeFragment implements ViewPager.OnPageChangeListener {

    private ViewPager mViewPager;
    private SpinnerAdapter mSpinnerAdapter;
    private ActionBar.OnNavigationListener mOnNavigationListener;
    private LostPagerAdapter mAdapter;
    private View mView;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment LostTypeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LostTypeFragment newInstance() {
        LostTypeFragment fragment = new LostTypeFragment();
        return fragment;
    }
    public LostTypeFragment() {
        // Required empty public constructor
    }

    @Override
    protected void setFragmentName() {
        mFragmentName = "LostType";
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
            mAdapter = new LostPagerAdapter();
        }
        if (mSpinnerAdapter == null) {
            mSpinnerAdapter = new ArrayAdapter<String>(activity,
                  R.layout.support_simple_spinner_dropdown_item  , mAdapter.TITLE);
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

    private class LostPagerAdapter extends FragmentStatePagerAdapter {

        protected static final int ITEM_COUNT = 2;
        protected final String[] TITLE = new String[] {"失物信息", "招领信息"};
        private Map<Integer, Fragment> mList;
        protected final int[] types = new int[]{LostThing.LOST, LostThing.FOUND};

        public LostPagerAdapter() {
            super(getFragmentManager());
            mList = new HashMap<Integer, Fragment>();
        }

        @Override
        public Fragment getItem(int position) {
            Fragment f = mList.get(position);
            if (f == null) {
                f = LostListFragment.newInstance(types[position]);
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
