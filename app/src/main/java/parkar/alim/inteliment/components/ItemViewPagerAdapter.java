package parkar.alim.inteliment.components;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.ArrayList;
import java.util.List;
import parkar.alim.inteliment.models.PagerFragmentModel;

/**
 * Custom pager Adapter that handles addition and removal of fragments from the pager.
 */
public class ItemViewPagerAdapter extends FragmentPagerAdapter {

    private List<PagerFragmentModel> itemList;
    private Context context;

    public ItemViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override public int getCount() {
        return itemList == null ? 0 : itemList.size();
    }

    @Override public Fragment getItem(int position) {
        return Fragment.instantiate(context, itemList.get(position).getFragmentName());
    }

    public boolean addItem(PagerFragmentModel item) {
        if (itemList == null) {
            itemList = new ArrayList<>();
        }

        if (itemList.contains(item)) {
            return false;
        }
        return itemList.add(item);
    }

    public boolean removeItem(int id) {
        return itemList.remove(new PagerFragmentModel(id));
    }
}
