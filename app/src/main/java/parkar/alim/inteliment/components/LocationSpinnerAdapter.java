package parkar.alim.inteliment.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;
import parkar.alim.inteliment.R;
import parkar.alim.inteliment.models.LocationInfo;

/**
 * Created by jarvis on 16/02/17.
 */

public class LocationSpinnerAdapter extends ArrayAdapter<LocationInfo> {

    private List<LocationInfo> locationInfoList;

    public LocationSpinnerAdapter(Context context, List<LocationInfo> data) {
        super(context, android.R.layout.simple_spinner_dropdown_item);
        locationInfoList = data;
    }

    @Override public int getCount() {
        return locationInfoList == null ? 0 : locationInfoList.size();
    }

    @Override public LocationInfo getItem(int position) {
        return locationInfoList.get(position);
    }

    @Override public long getItemId(int position) {
        return locationInfoList.get(position).getId();
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.simple_dropdown_item_1line, parent, false);
        }

        LocationInfo locationInfo = locationInfoList.get(position);
        TextView tvText = ButterKnife.findById(convertView, android.R.id.text1);
        tvText.setText(locationInfo.getName());
        convertView.setTag(locationInfo);

        return convertView;
    }

    @Override public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.simple_spinner_dropdown_item, parent, false);
        }
        ((TextView) convertView).setText(locationInfoList.get(position).getName());
        return convertView;
    }
}
