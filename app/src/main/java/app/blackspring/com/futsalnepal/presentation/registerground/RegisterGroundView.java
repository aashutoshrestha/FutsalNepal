package app.blackspring.com.futsalnepal.presentation.registerground;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import app.blackspring.com.futsalnepal.R;
import app.blackspring.com.futsalnepal.presentation.adapter.ViewPagerAdapter;
import app.blackspring.com.futsalnepal.presentation.fragments.ListFragment;
import app.blackspring.com.futsalnepal.presentation.fragments.Map;
import app.blackspring.com.futsalnepal.presentation.fragments.UploadDetails;
import app.blackspring.com.futsalnepal.presentation.fragments.UploadImage;
import me.relex.circleindicator.CircleIndicator;

public class RegisterGroundView extends AppCompatActivity {

    private ViewPager viewPager;
    private CircleIndicator circleIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_ground_view);

        viewPager = findViewById(R.id.vp_register);
        circleIndicator = findViewById(R.id.indicator);

        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new UploadImage(), "LIST");
        adapter.addFragment(new UploadDetails(), "MAP");
        viewPager.setAdapter(adapter);

        circleIndicator.setViewPager(viewPager);
    }
}
