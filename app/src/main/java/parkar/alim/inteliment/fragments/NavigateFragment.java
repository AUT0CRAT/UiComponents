package parkar.alim.inteliment.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import parkar.alim.inteliment.R;
import parkar.alim.inteliment.components.LocationSpinnerAdapter;
import parkar.alim.inteliment.models.LocationInfo;
import parkar.alim.inteliment.tasks.FetchDataTask;
import parkar.alim.inteliment.utils.NetworkUtils;

import static android.view.View.GONE;

/**
 * Created by jarvis on 16/02/17.
 */
public class NavigateFragment extends Fragment
        implements FetchDataTask.CompletionListener, AdapterView.OnItemSelectedListener {

    @BindView(R.id.tvTrain)
    TextView tvTrain;
    @BindView(R.id.tvCar)
    TextView tvCar;
    @BindView(R.id.spinnerLocation)
    Spinner spinnerLocation;

    @BindView(R.id.rlNoInternet)
    RelativeLayout rlNoInternet;

    @BindView(R.id.llContent)
    LinearLayout llContent;

    private SpinnerAdapter adapter;
    private FetchDataTask fetchDataTask;

    private List<LocationInfo> locationInfoList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View parentView = inflater.inflate(R.layout.fragment_navigation, container, false);
        ButterKnife.bind(this, parentView);

        return parentView;
    }

    @Override
    public void onResume() {
        super.onResume();

        setContent();
    }

    @OnClick(R.id.rlNoInternet)
    public void onNoInternetViewClicked() {
        setContent();
    }


    private void setContent() {
        if (locationInfoList == null) {

            //check for internet connectivity
            if (!NetworkUtils.isNetworkAvailable(getContext())) {
                llContent.setVisibility(View.GONE);
                rlNoInternet.setVisibility(View.VISIBLE);

                return;
            }

            fetchDataTask = new FetchDataTask(this);
            fetchDataTask.execute();

            return;
        }

        llContent.setVisibility(View.VISIBLE);
        rlNoInternet.setVisibility(View.GONE);

        adapter = new LocationSpinnerAdapter(getContext(), locationInfoList);
        spinnerLocation.setAdapter(adapter);
        spinnerLocation.setOnItemSelectedListener(this);
    }


    @Override
    public void onComplete(List<LocationInfo> locationInfoList) {
        this.locationInfoList = locationInfoList;
        adapter = new LocationSpinnerAdapter(getContext(), locationInfoList);
        spinnerLocation.setAdapter(adapter);
        spinnerLocation.setOnItemSelectedListener(this);

        llContent.setVisibility(View.VISIBLE);
        rlNoInternet.setVisibility(View.GONE);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        LocationInfo locationInfo = (LocationInfo) parent.getItemAtPosition(position);
        showSelectedData(locationInfo);
    }

    /**
     * Resfresh the screen to display the proper data as per the location selected
     *
     * @param locationInfo The {@link LocationInfo} of the selected location.
     */
    private void showSelectedData(LocationInfo locationInfo) {
        if (locationInfo == null) {
            return;
        }
        String car = locationInfo.getFromCentral().getCar();
        String train = locationInfo.getFromCentral().getTrain();

        if (car != null && !car.isEmpty()) {
            tvCar.setVisibility(View.VISIBLE);
            tvCar.setText(getString(R.string.msg_car, car));
        } else {
            tvCar.setVisibility(GONE);
        }

        if (train != null && !train.isEmpty()) {
            tvTrain.setVisibility(View.VISIBLE);
            tvTrain.setText(getString(R.string.msg_train, train));
        } else {
            tvTrain.setVisibility(GONE);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        LocationInfo locationInfo = (LocationInfo) parent.getItemAtPosition(0);
        showSelectedData(locationInfo);
    }

    /**
     * Launch the maps application on tap of the navigate button for the selected options.
     */
    @OnClick(R.id.btnNavigate)
    public void onNavigateClicked() {

        LocationInfo selectedItem = (LocationInfo) spinnerLocation.getSelectedItem();
        if (selectedItem == null) {
            return;
        }

        String uri = getString(R.string.direction_uri, selectedItem.getLocation().getLatitude(),
                selectedItem.getLocation().getLongitude());

        Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getContext().getPackageManager()) != null) {
            startActivity(mapIntent);
        } else {
            Toast.makeText(getContext(), R.string.error_no_maps, Toast.LENGTH_SHORT).show();
        }
    }
}
