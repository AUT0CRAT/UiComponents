package parkar.alim.inteliment.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import butterknife.BindView;
import butterknife.ButterKnife;
import parkar.alim.inteliment.R;
import parkar.alim.inteliment.components.DashboardFragmentAdapter;

public class DashboardActivity extends AppCompatActivity {

    @BindView(R.id.tabLayout) TabLayout tabLayout;
    @BindView(R.id.toolbar) Toolbar toolbar;

    @BindView(R.id.vpTabs) ViewPager vpTabs;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);


        vpTabs.setAdapter(new DashboardFragmentAdapter(getSupportFragmentManager(), this));
        int px = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics()));
        vpTabs.setPageMargin(px);
        vpTabs.setPageMarginDrawable(R.color.black);

        tabLayout.setupWithViewPager(vpTabs);
    }
}
