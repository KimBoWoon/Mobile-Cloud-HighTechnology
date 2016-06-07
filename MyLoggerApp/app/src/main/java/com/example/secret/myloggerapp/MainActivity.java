package com.example.secret.myloggerapp;

import android.content.Context;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity implements GoogleMap.InfoWindowAdapter {
    private LocationManager lm;
    private GoogleMap mMap;
    private GPSInfo gps;
    private double latitude;
    private double longitude;
    private LocationListener locationListener;
    private String GPSprovider;
    private Date date;
    SimpleDateFormat dateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpMapIfNeeded();
        MapsInitializer.initialize(getApplicationContext());

        init();
    }

    private void init() {
        date = new Date();
        dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일" + "\n" + "hh시 mm분 ss초");

        if (checkPlayServices()) {
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();

            lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationListener = new MyLocationListener();
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10, locationListener);

            gps = new GPSInfo(MainActivity.this);
            locationListener = new MyLocationListener();

            mMap.setInfoWindowAdapter(this);

            if (gps.isGetLocation()) {
                double latitude = gps.getLatitude();
                double longitude = gps.getLongitude();

                LatLng latLng = new LatLng(latitude, longitude);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
                mMap.addMarker(new MarkerOptions().position(latLng).title("My").snippet(dateFormat.format(date))).showInfoWindow();
            }
        }
    }

    private boolean checkPlayServices() {
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int result = googleAPI.isGooglePlayServicesAvailable(this);
        if(result != ConnectionResult.SUCCESS) {
            if(googleAPI.isUserResolvableError(result)) {
                googleAPI.getErrorDialog(this, result, 1).show();
            }

            return false;
        }

        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        if (mMap == null) {
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
            if (mMap != null) {
                init();
            }
        }
    }

    @Override
    public View getInfoWindow(Marker marker) {
        View root = View.inflate(getApplicationContext(), R.layout.google_marker_snippet, null);
        AQuery aq = new AQuery(root);

        aq.id(R.id.time_snippet).text(marker.getSnippet()).backgroundColor(Color.WHITE);
        aq.id(R.id.time_title).text(marker.getTitle()).textColor(Color.BLACK).backgroundColor(Color.WHITE);

        return root;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }

    private class MyLocationListener implements LocationListener {
        @Override
        public void onLocationChanged(Location location) {
            if (location != null) {
                Criteria criteria = new Criteria();
                criteria.setAccuracy(Criteria.ACCURACY_FINE);       // 정확도
                criteria.setPowerRequirement(Criteria.POWER_LOW);   // 전원 소비량
                criteria.setAltitudeRequired(false);                // 고도, 높이 값을 얻어 올지를 결정
                criteria.setBearingRequired(false);                 // provider 기본 정보(방위, 방향)
                criteria.setSpeedRequired(false);                   // 속도
                criteria.setCostAllowed(true);                      // 위치 정보를 얻어 오는데 들어가는 금전적 비용
                GPSprovider = lm.getBestProvider(criteria, true);

                latitude = location.getLatitude();
                longitude = location.getLongitude();
                LatLng latLng = new LatLng(latitude, longitude);
                mMap.addMarker(new MarkerOptions().position(latLng).title("My").snippet(dateFormat.format(date))).showInfoWindow();
            }
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub
        }
    }
}
