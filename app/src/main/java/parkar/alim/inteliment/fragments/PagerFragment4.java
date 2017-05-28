package parkar.alim.inteliment.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import parkar.alim.inteliment.R;

/**
 * Created by jarvis on 16/02/17.
 */

public class PagerFragment4 extends Fragment {


    @BindView(R.id.tvText) TextView tvText;

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View parentView = inflater.inflate(R.layout.layout_pager_fragment, container, false);
        ButterKnife.bind(this, parentView);

        tvText.setText(R.string.fragment_four);

        return parentView;
    }

    @OnClick(R.id.tvText) public void onClick() {
        Toast.makeText(getContext(), getString(R.string.clicked_on, tvText.getText()),
            Toast.LENGTH_SHORT).show();
    }
}
