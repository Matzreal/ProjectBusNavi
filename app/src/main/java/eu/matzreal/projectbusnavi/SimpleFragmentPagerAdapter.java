package eu.matzreal.projectbusnavi;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    public final static int ROUTE_SEEARCH_FRAGMENT = 0;
    public final static int NAVI_MENU_FRAGMENT = 1;

    private static final int PAGE_COUNT = 2;

    public SimpleFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case ROUTE_SEEARCH_FRAGMENT:
                return new RouteSearchFragment();
            case NAVI_MENU_FRAGMENT:
                return new NaviMenuFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return null;
    }
}
