package parkar.alim.inteliment.components;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.ArrayList;
import java.util.List;
import parkar.alim.inteliment.R;
import parkar.alim.inteliment.fragments.NavigateFragment;
import parkar.alim.inteliment.fragments.ViewFragment;
import parkar.alim.inteliment.models.DashboardFragmentModel;

/**
 * Pager adapter for the dashboard. It Contains 2 Fragments, The Views section and the Navigate Section.
 */
public class DashboardFragmentAdapter extends FragmentPagerAdapter {

    private static final int ID_VIEWS = 1;
    private static final int ID_NAVIGATION = 2;

    private List<DashboardFragmentModel> fragmentList;
    private Context context;

    public DashboardFragmentAdapter(FragmentManager fm, Context context) {
        super(fm);

        this.context = context;
        fragmentList = new ArrayList<>(2);
        fragmentList.add(
            new DashboardFragmentModel(ID_VIEWS, context.getString(R.string.title_tab_views),
                ViewFragment.class.getName()));
        fragmentList.add(new DashboardFragmentModel(ID_NAVIGATION,
            context.getString(R.string.title_tab_navigate), NavigateFragment.class.getName()));
    }

    @Override public Fragment getItem(int position) {
        return Fragment.instantiate(context, fragmentList.get(position).getFragmentName());
    }

    @Override public int getCount() {
        return fragmentList == null ? 0 : fragmentList.size();
    }

    @Override public CharSequence getPageTitle(int position) {
        return fragmentList.get(position).getTabTitle();
    }
}
