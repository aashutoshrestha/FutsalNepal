package app.blackspring.com.futsalnepal.presentation.fragments;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import app.blackspring.com.futsalnepal.R;
import app.blackspring.com.futsalnepal.model.futsal.FutsalData;
import app.blackspring.com.futsalnepal.model.futsal.FutsalListItem;

@SuppressLint("ValidFragment")

public class Map extends Fragment implements OnMapReadyCallback {

    MapView mMapView;
    private GoogleMap googleMap;
    private FutsalData futsalData;
    private String latitude, longitude;
    private MarkerOptions options = new MarkerOptions();
    private List<Marker> markers = new ArrayList<>();

    public Map() {
    }

    public Map(FutsalData futsalData, String latitude, String longitude) {
        this.futsalData = futsalData;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMapView = view.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        for(FutsalListItem item : futsalData.getFutsalList()){
            LatLng position = new LatLng(Double.parseDouble(item.getLattitude()), Double.parseDouble(item.getLongitude()));
            String open = "Open: " + item.getOpeningTime() + " - " + "Close: " + item.getClosingTime();

            Marker marker = googleMap.addMarker(new MarkerOptions()
                    .position(position)
                    .title(item.getName())
                    .snippet(open)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker)));
            marker.setTag(futsalData.getFutsalList().indexOf(item));
            markers.add(marker);
        }

/*        FutsalListItem item = futsalData.getFutsalList().get(0);

        LatLng position = new LatLng(Double.parseDouble(item.getLattitude()), Double.parseDouble(item.getLongitude()));
        String open = "Open: " + item.getOpeningTime() + " - " + "Close: " + item.getClosingTime();
        googleMap.addMarker(new MarkerOptions()
                .position(position)
                .title(item.getName())
                .snippet(open)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker)));*/

        LatLng position = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
        googleMap.addMarker(new MarkerOptions()
                .position(position)
                .title("You are here")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker)));

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude)), 12.0f));


        googleMap.setOnMarkerClickListener(marker -> {
            showDialog((int)marker.getTag());
            return false;
        });

    }

    private void showDialog(int position){
        Dialog dialog = new Dialog(getActivity());
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_map);
        TextView name = dialog.findViewById(R.id.tv_futsal_name);
        name.setText(futsalData.getFutsalList().get(position).getName());
        dialog.show();
    }


    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}
