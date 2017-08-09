package eu.matzreal.projectbusnavi;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity implements BusStopsDialogFragment.BusStopsDialogListener {

    private static Logger LOGGER = Logger.getLogger("eu.matzreal.projectbusnavi.MainActivity");

    private ArrayList<BusStop> busStopsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        SimpleFragmentPagerAdapter adapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        // Set the tab icons
        tabLayout.getTabAt(SimpleFragmentPagerAdapter.ROUTE_SEEARCH_FRAGMENT).setIcon(R.drawable.ic_directions);
        tabLayout.getTabAt(SimpleFragmentPagerAdapter.NAVI_MENU_FRAGMENT).setIcon(R.drawable.ic_bus);

        // Remove the action bar shadow effect on devices with Android 5.0+
        getSupportActionBar().setElevation(0);

        // Setup the bus stops list
        setupBusStopList();


    }

    private void setupBusStopList() {
        // Get data from source
        String[] busStopsNames = getResources().getStringArray(R.array.test_array_data);
        busStopsList = new ArrayList<>();

        int id = 0;
        for (String busStopName : busStopsNames)
            busStopsList.add(new BusStop(id++, busStopName));
    }

    public ArrayList<BusStop> getBusStopsList() {
        return busStopsList;
    }

    @Override
    public void onItemSelected(BusStop busStop, RouteSearchFragment.Direction direction) {
        System.out.println("[ " + busStop.getId() + " : " + busStop.getName() + " ]");    // debug

        RouteSearchFragment fragment = (RouteSearchFragment) getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.view_pager + ":" + SimpleFragmentPagerAdapter.ROUTE_SEEARCH_FRAGMENT);
        if (fragment != null) {
            TextView directionTextView;
            if (direction == RouteSearchFragment.Direction.FROM)
                directionTextView = fragment.getView().findViewById(R.id.tv_direction_from);
            else
                directionTextView = fragment.getView().findViewById(R.id.tv_direction_to);

            if (directionTextView != null)
                directionTextView.setText(busStop.getName());
        }
    }

}
