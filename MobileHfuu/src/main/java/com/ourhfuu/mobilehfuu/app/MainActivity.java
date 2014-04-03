package com.ourhfuu.mobilehfuu.app;

import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;
import android.widget.SpinnerAdapter;


public class MainActivity extends BaseActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks,
        BaseTypeFragment.OnFragmentInteractionListener {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private BaseTypeFragment mLostFragment, mArticleFragment;


    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = getFragment(Config.leftCategory.get(position).id);
        if(fragment != null) {
            fragmentManager.beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
        }
    }

    private BaseTypeFragment getFragment(int type) {
        switch (type) {
            case Config.NEWS_ITEM:
                if (mArticleFragment == null) {
                    mArticleFragment = ArticleTypeFragment.newInstance();
                }
                return mArticleFragment;
            case Config.LOST_ITEM:
                if (mLostFragment == null) {
                    mLostFragment = LostTypeFragment.newInstance();
                }
                return mLostFragment;
        }
        return null;
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void setPageRecord() {
        mActivityName = "MainActivity";
        mIsPageRecord = false;
    }

    @Override
    public void onFragmentInteraction(SpinnerAdapter adapter, ActionBar.OnNavigationListener listener) {
        if (adapter != null && listener!= null)
        getSupportActionBar().setListNavigationCallbacks(adapter, listener);
    }

    @Override
    public void onPagerSelect(int position) {
        if (position > 0 && position <= getSupportActionBar().getNavigationItemCount())
            getSupportActionBar().setSelectedNavigationItem(position);

    }

}
