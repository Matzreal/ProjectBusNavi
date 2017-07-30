package eu.matzreal.projectbusnavi;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new SimpleFragmentPagerAdapter(getSupportFragmentManager()));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        // Set the tab icons
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_directions);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_bus);

        // Remove the action bar shadow effect on devices with Android 5.0+
        getSupportActionBar().setElevation(0);

    }

}
