package com.ourhfuu.mobilehfuu.app;

import android.app.Activity;
import android.support.v7.app.ActionBar;
import android.widget.SpinnerAdapter;

/**
 * Created by sam on 14-3-31.
 */
public abstract class BaseTypeFragment extends BaseFragment {

    private OnFragmentInteractionListener mListener;
    private SpinnerAdapter mSpinnerAdapter;
    private ActionBar.OnNavigationListener mOnNavigationlistener;



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public OnFragmentInteractionListener getOnFragmentInteractionListener() {
        return mListener;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction( SpinnerAdapter adapter, ActionBar.OnNavigationListener listener);
        public void onPagerSelect(int position);
    }
}
