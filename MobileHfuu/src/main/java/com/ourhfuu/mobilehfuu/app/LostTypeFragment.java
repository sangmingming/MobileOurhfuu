package com.ourhfuu.mobilehfuu.app;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ourhfuu.mobilehfuu.entity.LostThing;
import com.ourhfuu.mobilehfuu.webservice.LostThingService;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link LostTypeFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class LostTypeFragment extends BaseFragment {

    private ViewPager mViewPager;


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
        View v = inflater.inflate(R.layout.fragment_lost_type, null);
        mViewPager = (ViewPager) v.findViewById(R.id.pager);
        mViewPager.setAdapter(new LostPagerAdapter());
        return v;
    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private class LostPagerAdapter extends FragmentPagerAdapter {

        protected static final int ITEM_COUNT = 2;
        protected final String[] TITLE = new String[] {"失物信息", "招领信息"};
        private LostListFragment mLostFragment = LostListFragment.newInstance(LostThing.LOST);
        private LostListFragment mFindFragment = LostListFragment.newInstance(LostThing.FOUND);


        public LostPagerAdapter() {
            super(getFragmentManager());
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case LostThing.LOST:
                    return mLostFragment;
                case LostThing.FOUND:
                    return mFindFragment;
            }
            return null;
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
