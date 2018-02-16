package app.blackspring.com.futsalnepal.presentation.details;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import app.blackspring.com.futsalnepal.R;
import app.blackspring.com.futsalnepal.presentation.adapter.GroundsRVAdapter;
import app.blackspring.com.futsalnepal.presentation.adapter.ViewPagerAdapter;
import app.blackspring.com.futsalnepal.presentation.fragments.BookingFragment;
import app.blackspring.com.futsalnepal.presentation.navigation.NavigationView;

public class DetailsView extends AppCompatActivity {

    private Toolbar toolbar;
    private FloatingActionButton floatingActionButton;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_view);
        toolbar = findViewById(R.id.toolbar);
        tabLayout = findViewById(R.id.tl_view_ground);
        floatingActionButton = findViewById(R.id.fab);
        recyclerView = findViewById(R.id.rv_grounds);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        floatingActionButton.setOnClickListener(view -> floatingActionButton
                .setImageDrawable(ContextCompat.getDrawable(this,
                        R.drawable.ic_favorite_black_24dp)));

        viewPager = findViewById(R.id.vp_grounds);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        GroundsRVAdapter groundsRVAdapter = new GroundsRVAdapter();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(groundsRVAdapter);
        findViewById(R.id.iv_drop_down).setOnClickListener(view -> {
            if(recyclerView.getVisibility() == View.VISIBLE){
                recyclerView.setVisibility(View.GONE);
            }else {
                recyclerView.setVisibility(View.VISIBLE);

            }
        });

        findViewById(R.id.iv_navigation).setOnClickListener(view -> {
            startActivity(new Intent(this, NavigationView.class));
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        finish();
        return true;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new BookingFragment(), "FRI\nJan 02");
        adapter.addFragment(new BookingFragment(), "SAT\nJan 02");
        adapter.addFragment(new BookingFragment(), "SUN\nJan 03");
        adapter.addFragment(new BookingFragment(), "MON\nJan 04");
        adapter.addFragment(new BookingFragment(), "TUE\nJan 05");
        viewPager.setAdapter(adapter);
    }
}
