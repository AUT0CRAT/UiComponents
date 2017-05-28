package parkar.alim.inteliment.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import parkar.alim.inteliment.R;
import parkar.alim.inteliment.components.ItemViewPagerAdapter;
import parkar.alim.inteliment.models.PagerFragmentModel;

/**
 * Created by jarvis on 16/02/17.
 */
public class ViewFragment extends Fragment implements View.OnClickListener {

    private static final int ID_FRAGMENT_1 = 1;
    private static final int ID_FRAGMENT_2 = 2;
    private static final int ID_FRAGMENT_3 = 3;
    private static final int ID_FRAGMENT_4 = 4;
    private static final int ID_FRAGMENT_5 = 5;
    private static final String SAVED_SELECTED_TEXT = "SELECTED_TEXT";
    private static final String SAVED_COLOR = "SELECTED_COLOR";
    /**
     * Container containing the RED, BlUE, GREEN buttons
     */
    @BindView(R.id.llButtonContainer)
    LinearLayout llButtonContainer;
    /**
     * Container containing the clickable items.
     */
    @BindView(R.id.llHorizontalViewContainer)
    LinearLayout llHorizontalViewContainer;
    /**
     * ViewPager containing the fragments.
     */
    @BindView(R.id.vpFragments)
    ViewPager vpFragments;
    /**
     * The View that displays the text of the item clicked in the {@link llHorizontalViewContainer}
     */
    @BindView(R.id.tvSelectedItemText)
    TextView tvSelectedItemText;
    private int selectedColor = -1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View parentView = inflater.inflate(R.layout.fragment_views, container, false);
        ButterKnife.bind(this, parentView);

        setContent();
        if (savedInstanceState != null) {
            tvSelectedItemText.setText(savedInstanceState.getString(SAVED_SELECTED_TEXT, getString(R.string.msg_no_selected_item)));

            selectedColor = savedInstanceState.getInt(SAVED_COLOR, -1);
            if (selectedColor != -1) {
                llButtonContainer.setBackgroundColor(selectedColor);
            }
        }

        return parentView;
    }


    /**
     * Setup the views with the appropriate data
     */
    private void setContent() {
        setupHorizontalScrollView();
        setupPagerView();
    }

    /**
     * Setup the horizontal scroll view containing 5 clickable elements.
     */
    private void setupHorizontalScrollView() {
        llHorizontalViewContainer.removeAllViews();
        for (int i = 1; i <= 5; i++) {
            View itemView = LayoutInflater.from(getContext())
                    .inflate(R.layout.layout_item, llHorizontalViewContainer, false);
            TextView textView = ButterKnife.findById(itemView, R.id.tvItemText);
            String label = getString(R.string.label_item, i);
            textView.setText(label);
            itemView.setTag(label);
            itemView.setOnClickListener(this);
            llHorizontalViewContainer.addView(itemView);
        }
    }

    /**
     * Setup the view pager with the fragments.
     */
    private void setupPagerView() {
        ItemViewPagerAdapter adapter = new ItemViewPagerAdapter(getFragmentManager(), getContext());
        adapter.addItem(new PagerFragmentModel(ID_FRAGMENT_1, PagerFragment1.class.getName()));
        adapter.addItem(new PagerFragmentModel(ID_FRAGMENT_2, PagerFragment2.class.getName()));
        adapter.addItem(new PagerFragmentModel(ID_FRAGMENT_3, PagerFragment3.class.getName()));
        adapter.addItem(new PagerFragmentModel(ID_FRAGMENT_4, PagerFragment4.class.getName()));
        adapter.addItem(new PagerFragmentModel(ID_FRAGMENT_5, PagerFragment5.class.getName()));
        vpFragments.setAdapter(adapter);
    }

    /**
     * Called when the button with ID {@link R.id.btnRed} is clicked
     */
    @OnClick(R.id.btnRed)
    public void onRedClicked() {
        llButtonContainer.setBackgroundColor(Color.RED);
        selectedColor = Color.RED;
    }

    /**
     * Called when the button with ID {@link R.id.btnBlue} is clicked
     */
    @OnClick(R.id.btnBlue)
    public void onBlueClicked() {
        llButtonContainer.setBackgroundColor(Color.BLUE);
        selectedColor = Color.BLUE;
    }


    /**
     * Called when the button with ID @{@link R.id.btnGreen} is clicked
     */
    @OnClick(R.id.btnGreen)
    public void onGreenClicked() {
        llButtonContainer.setBackgroundColor(Color.GREEN);
        selectedColor = Color.GREEN;
    }

    /**
     * Generic onClick for the current fragment. The method handles the click on the items present in the horizontal scroll view
     *
     * @param v the view clicked
     */
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.llItem) {
            tvSelectedItemText.setText(getString(R.string.msg_selected_item, v.getTag()));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SAVED_SELECTED_TEXT, tvSelectedItemText.getText().toString());
        outState.putInt(SAVED_COLOR, selectedColor);
    }
}
